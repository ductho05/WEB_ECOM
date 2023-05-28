package iostart.Controller.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import iostart.Entyti.Cart;
import iostart.Entyti.CartItem;
import iostart.Entyti.Order_Item;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;
import iostart.Entyti.Users;
import iostart.Services.ICartItemServices;
import iostart.Services.ICartServices;
import iostart.Services.IOrderServices;
import iostart.Services.IOrder_ItemServices;
import iostart.Services.IProductServices;
import iostart.Services.Impl.CartItemServicesImpl;
import iostart.Services.Impl.CartServicesImpl;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.Order_ItemServicesImpl;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.util.SessionUtil;

@MultipartConfig

@WebServlet(urlPatterns = { "/home/cart-addtocart", "/home/cart-detail", "/home/cart-update-quantity",
		"/home/cart-delete-cartitem", "/home/cart-checkout" })
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductServices productservices = new ProductServicesImpl();
	IOrderServices orderservices = new OrderServicesImpl();
	IOrder_ItemServices order_itemservices = new Order_ItemServicesImpl();
	ICartItemServices cartitemservices = new CartItemServicesImpl();
	ICartServices cartservices = new CartServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURL().toString();
		Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");

		if (url.contains("cart-detail")) {
			List<CartItem> list_cartitem = new ArrayList<>();
			List<Object[]> listObj = cartitemservices.findByUser(user.getUserid());
			for (Object[] objects : listObj) {
				CartItem cart_item = (CartItem) objects[0];
				list_cartitem.add(cart_item);
			}
			Set<CartItem> setlist_cartitem = new LinkedHashSet<CartItem>(list_cartitem);
			List<CartItem> new_list = new ArrayList<>(setlist_cartitem);
			float total_price = 0;
			for (CartItem cartItem : new_list) {
				total_price += cartItem.getUnitprice();
			}
			req.setAttribute("list_order_item", new_list);
			req.setAttribute("total_price", total_price);
			req.getRequestDispatcher("/views/user/cartdetail.jsp").forward(req, resp);
		} else if (url.contains("cart-checkout")) {

			List<CartItem> list_cartitem = new ArrayList<>();
			List<Object[]> listObj = cartitemservices.findByUser(user.getUserid());
			for (Object[] objects : listObj) {
				CartItem cart_item = (CartItem) objects[0];
				list_cartitem.add(cart_item);
			}
			Set<CartItem> setlist_cartitem = new LinkedHashSet<CartItem>(list_cartitem);
			List<CartItem> new_list = new ArrayList<>(setlist_cartitem);
			float total_price = 0;
			if (new_list != null) {
				for (CartItem cartItem : new_list) {
					total_price += cartItem.getUnitprice();
				}
			}
			req.setAttribute("total_price", total_price);
			req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
		} else if (url.contains("cart-delete-cartitem")) {
			DeleteCartItem(req, resp);
			CartItem cartitem = new CartItem();
			req.setAttribute("cartitem", cartitem);
			resp.sendRedirect(req.getContextPath() + "/home/cart-detail");
		}
		if (url.contains("cart-detail")) {
			int num_item = cartitemservices.countByUser(user.getUserid());
			req.setAttribute("num_item", num_item);
			req.getRequestDispatcher("/commons/user/hearder.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("cart-addtocart")) {
			addToCart(req, resp);
			resp.sendRedirect(req.getContextPath() + "/home?index=0&filter=tat-ca&cid=0");
		} else if (url.contains("cart-update-quantity")) {
			updateQuantity(req, resp);
			resp.sendRedirect(req.getContextPath() + "/home/cart-detail");
		} else if (url.contains("cart-checkout")) {
			checkOut(req, resp);
			resp.sendRedirect(req.getContextPath() + "/home?index=0&filter=tat-ca&cid=0");
		} else if (url.contains("cart-delete-cartitem")) {
			DeleteCartItem(req, resp);

		}
	}

	protected void DeleteCartItem(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int cartitemid = Integer.parseInt(req.getParameter("cartitemid"));
		cartitemservices.delete(cartitemid);

	}

	protected void checkOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			Orders order = new Orders();
			BeanUtils.populate(order, req.getParameterMap());
			if (order.getTotal_price() >= 0) {
				orderservices.insert(order);

				Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");
				List<CartItem> list_cartitem = new ArrayList<>();
				List<Object[]> listObj = cartitemservices.findByUser(user.getUserid());
				for (Object[] objects : listObj) {
					CartItem cart_item = (CartItem) objects[0];
					list_cartitem.add(cart_item);
				}
				Set<CartItem> setlist_cartitem = new LinkedHashSet<CartItem>(list_cartitem);
				List<CartItem> new_list = new ArrayList<>(setlist_cartitem);
				for (CartItem cartitem : new_list) {
					Order_Item order_item = new Order_Item();
					order_item.setOrder_id(order.getId());
					order_item.setProduct_id(cartitem.getProduct().getProductId());
					order_item.setQuantity(cartitem.getQuantity());
					order_item.setTotal(cartitem.getUnitprice());

					Product product = cartitem.getProduct();
					product.setAmount(product.getAmount() + cartitem.getQuantity());
					order_itemservices.insert(order_item);
					productservices.update(product);

					cartitemservices.delete(cartitem.getId());
				}
				Cart cart = cartservices.findByUser(user.getUserid());
				cartservices.delete(cart.getId());
			}
			else {
				req.getRequestDispatcher("/error_page.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int cartitemid = Integer.parseInt(req.getParameter("cartitemid"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		CartItem cartItem = cartitemservices.findById(cartitemid);
		cartItem.setQuantity(quantity);
		cartItem.setUnitprice(cartItem.getProduct().getPrice() * quantity);
		cartitemservices.update(cartItem);
	}

	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");
		if (user != null) {
			int quantity = 1;
			int pid = Integer.parseInt(req.getParameter("pid"));
			Product product = productservices.findById(pid);
			if (product != null) {
				quantity = Integer.parseInt(req.getParameter("quantity"));
				List<Cart> list_cart = cartservices.findAllByUser(user.getUserid());
				if (list_cart.size() == 0) {
					Cart cart = new Cart();
					cart.setUserid(user.getUserid());
					Calendar createat = Calendar.getInstance();
					SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");

					String createDate = String.valueOf(dfm.format(createat.getTime()));
					cart.setBuyDate(createDate);
					cart.setStatus(1);

					cartservices.insert(cart);

					CartItem cartitem = new CartItem();
					cartitem.setCartid(cart.getId());
					cartitem.setProductid(product.getProductId());
					cartitem.setQuantity(quantity);
					cartitem.setUnitprice(product.getPrice() * quantity);

					cartitemservices.insert(cartitem);
				} else {
					Cart cart = cartservices.findByUser(user.getUserid());
					CartItem cartitem = new CartItem();
					cartitem.setCartid(cart.getId());
					cartitem.setProductid(product.getProductId());
					cartitem.setQuantity(quantity);
					cartitem.setUnitprice(product.getPrice() * quantity);

					cartitemservices.insert(cartitem);
				}
			}
		}
	}

	public static void main(String[] args) {
		ICartServices cartservices = new CartServicesImpl();
		ICartItemServices cartitemservices = new CartItemServicesImpl();
		List<CartItem> list_cartitem = new ArrayList<>();
		List<Object[]> listObj = cartitemservices.findByUser(10);
		for (Object[] objects : listObj) {
			CartItem cart_item = (CartItem) objects[0];
			list_cartitem.add(cart_item);
		}
		Set<CartItem> setlist_cartitem = new LinkedHashSet<CartItem>(list_cartitem);
		List<CartItem> new_list = new ArrayList<>(setlist_cartitem);
		for (CartItem cartItem : new_list) {
			System.out.println(cartItem.getQuantity());
		}
	}
}
