package iostart.Controller.User;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import iostart.Entyti.Category;
import iostart.Entyti.Order_Item;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;
import iostart.Entyti.Users;
import iostart.Services.ICartItemServices;
import iostart.Services.ICategoryServices;
import iostart.Services.IOrderServices;
import iostart.Services.IOrder_ItemServices;
import iostart.Services.IProductServices;
import iostart.Services.IUsersServices;
import iostart.Services.Impl.CartItemServicesImpl;
import iostart.Services.Impl.CategoryServicesImpl;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.Order_ItemServicesImpl;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.Services.Impl.UsersServicesImpl;
import iostart.util.Constant;
import iostart.util.SessionUtil;
import iostart.util.UploadUtils;

@MultipartConfig
@WebServlet(urlPatterns = { "/home", "/dang-nhap", "/dang-xuat", "/dang-ky", "/quen-mat-khau", "/home/product-detail",
		"/search-product", "/user-profile", "/user-order-detail" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IUsersServices userservices = new UsersServicesImpl();
	IProductServices productservices = new ProductServicesImpl();
	ICategoryServices categoryservices = new CategoryServicesImpl();
	ICartItemServices cartitemservices = new CartItemServicesImpl();
	IOrderServices orderservices = new OrderServicesImpl();
	IOrder_ItemServices order_item = new Order_ItemServicesImpl();
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String url = req.getRequestURL().toString();
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rq = req.getRequestDispatcher("/views/login.jsp");
			rq.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "Users");
			resp.sendRedirect(req.getContextPath() + "/home?index=0&filter=tat-ca&cid=0");
		} else if (action != null && action.equals("register")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rq = req.getRequestDispatcher("/views/register.jsp");
			rq.forward(req, resp);
		} else if (action != null && action.equals("forgot_password")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			SessionUtil.getInstance().removeValue(req, "Users");
			RequestDispatcher rq = req.getRequestDispatcher("/views/forgot_password.jsp");
			rq.forward(req, resp);
		} else if (url.contains("product-detail")) {
			findbyId(req, resp);
			req.getRequestDispatcher("/views/user/product-detail.jsp").forward(req, resp);
		} else if (url.contains("search-product")) {
			findbyName(req, resp);
			List<Category> list_cate = categoryservices.findAll();
			req.setAttribute("list_cate", list_cate);
			RequestDispatcher rq = req.getRequestDispatcher("/views/user/home.jsp");
			rq.forward(req, resp);
		} else if (url.contains("home")) {
			findAll(req, resp);
			List<Category> list_cate = categoryservices.findAll();
			req.setAttribute("list_cate", list_cate);
			RequestDispatcher rq = req.getRequestDispatcher("/views/user/home.jsp");
			rq.forward(req, resp);
		} else if (url.contains("user-profile")) {
			findUser(req, resp);
			findOrder(req, resp);
			req.getRequestDispatcher("/views/user/user-profile/profile.jsp").forward(req, resp);
		} else if (url.contains("user-order-detail")) {
			findByOrder(req, resp);
			req.getRequestDispatcher("/views/user/user-profile/order-detail.jsp").forward(req, resp);
		}
		Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");
		if (user != null) {
			int num_item = cartitemservices.countByUser(user.getUserid());
			req.setAttribute("num_item", num_item);
			req.getRequestDispatcher("/commons/user/hearder.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		String action = req.getParameter("action");
		Users user1 = (Users) SessionUtil.getInstance().getValue(req, "Users");
		if (url.contains("user-profile")) {
			updateUser(req, resp);
			resp.sendRedirect(req.getContextPath() + "/user-profile?uid=" + user1.getUserid());
		}
		if (action != null && action.equals("login")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			Users user = userservices.findByUsernamePassRole(username, password);
			if (user != null) {
				SessionUtil.getInstance().putValue(req, "Users", user);
				if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Admin"))) {
					resp.sendRedirect(req.getContextPath() + "/admin-home?flag=0");
				} else if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Customer"))
						|| user.getRoleid() == Integer.parseInt(resourceBundle.getString("Seller"))) {
					resp.sendRedirect(req.getContextPath() + "/home?index=0&filter=tat-ca&cid=0");
				}
			}

			else if (user == null) {
				resp.sendRedirect(req.getContextPath()
						+ "/dang-nhap?action=login&message=username_password_isvalid&alert=danger");
			}
		} else if (action != null && action.equals("register")) {
			String user_name = req.getParameter("username");
			String email = req.getParameter("email");
			String fullname = req.getParameter("fullname");
			String pass1 = req.getParameter("password1");
			String pass2 = req.getParameter("password2");

			Users users = userservices.findByUsernameEmail(user_name, email);
			if (user_name.trim().length() == 0 || email.trim().length() == 0 || fullname.trim().length() == 0
					|| pass1.trim().length() == 0 || pass2.trim().length() == 0) {
				resp.sendRedirect(req.getContextPath() + "/dang-ky?action=register&message=text_none&alert=danger");
			} else if (users != null) {
				resp.sendRedirect(req.getContextPath()
						+ "/dang-ky?action=register&message=username_email-already_exist&alert=danger");
			}

			else if (pass1.equals(pass2)) {
				Date d = Calendar.getInstance().getTime();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date = df.format(d);
				Users user = new Users();
				user.setUsername(user_name);
				user.setEmail(email);
				user.setFullname(fullname);
				user.setPassword(pass1);
				user.setRoleid(Integer.parseInt(resourceBundle.getString("Customer")));
				user.setStatus(true);
				user.setCreated(date);

				userservices.insert(user);

				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");

			} else if (!pass1.equals(pass2)) {
				resp.sendRedirect(
						req.getContextPath() + "/dang-ky?action=register&message=password_not_confilm&alert=danger");
			}
		} else if (action != null && action.equals("forgot_password")) {
			String fg_email = req.getParameter("email");
			String fg_pass1 = req.getParameter("password1");
			String fg_pass2 = req.getParameter("password2");

			Users u = userservices.findByEmail(fg_email);
			if (fg_email.trim().length() == 0 || fg_pass1.trim().length() == 0 || fg_pass2.trim().length() == 0) {
				resp.sendRedirect(
						req.getContextPath() + "/quen-mat-khau?action=forgot_password&message=text_none&alert=danger");
			} else if (u != null) {
				if (fg_pass1.equals(fg_pass2)) {

					u.setPassword(fg_pass1);
					userservices.update(u);
					resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");

				} else if (!fg_pass1.equals(fg_pass2)) {
					resp.sendRedirect(req.getContextPath()
							+ "/quen-mat-khau?action=forgot_password&message=password_not_confilm&alert=danger");
				}
			}

			else {
				resp.sendRedirect(req.getContextPath()
						+ "/quen-mat-khau?action=forgot_password&message=email_not_exist&alert=danger");
			}

		}
	}

	protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Users user1 = (Users) SessionUtil.getInstance().getValue(req, "Users");

		try {

			Users user = new Users();
			BeanUtils.populate(user, req.getParameterMap());

			user.setUserid(user1.getUserid());
			user.setPassword(user1.getPassword());
			user.setStatus(true);
			user.setRoleid(user1.getRoleid());
			user.setEmail(user1.getEmail());
			user.setCreated(user1.getCreated());

			Users u = userservices.findById(user.getUserid());
			if (req.getPart("images").getSize() == 0) {
				user.setImages(u.getImages());
			} else {
				if (u.getImages() != null) {
					String fileName = u.getImages();
					File file = new File(Constant.DIR + "\\users\\" + fileName);

					if (file.delete()) {
						System.out.println("Xóa thành công");
					} else {
						System.out.println(Constant.DIR + "\\users\\" + fileName);
					}
				}
				String fileName = String.valueOf(user.getUserid()) + System.currentTimeMillis();
				user.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\users\\", fileName));
			}

			userservices.update(user);
			req.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	protected void findByOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		List<Order_Item> list_orderItem = new ArrayList<>();
		List<Orders> list_Orders = new ArrayList<>();
		List<Object[]> list = order_item.findByOrder(order_id);

		for (Object[] item : list) {
			Order_Item order_item = (Order_Item) item[0];
			Orders orders = (Orders) item[1];
			list_orderItem.add(order_item);
			list_Orders.add(orders);

		}

		Set<Order_Item> set_orderitem = new LinkedHashSet<Order_Item>(list_orderItem);
		List<Order_Item> list_odi = new ArrayList<>(set_orderitem);
		Set<Orders> set_order = new LinkedHashSet<Orders>(list_Orders);
		List<Orders> list_o = new ArrayList<>(set_order);

		req.setAttribute("list_odi", list_odi);
		req.setAttribute("list_o", list_o);
	}

	protected void findOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user1 = (Users) SessionUtil.getInstance().getValue(req, "Users");
		int id = user1.getUserid();
		List<Orders> list = orderservices.findByUser(id);
		req.setAttribute("list", list);
	}

	protected void findUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user1 = (Users) SessionUtil.getInstance().getValue(req, "Users");
		int id = user1.getUserid();
		Users user = userservices.findById(id);
		req.setAttribute("user", user);
	}

	protected void findbyName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		int count = productservices.countByName(name);
		int sizepage = count / 8;
		if (count % 8 != 0) {
			sizepage++;
		}
		int index = Integer.parseInt(req.getParameter("index"));
		List<Product> list = productservices.findByName(name, index, 8);

		req.setAttribute("list", list);
		req.setAttribute("index", index);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
	}

	protected void findbyId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int pid = Integer.parseInt(req.getParameter("pid"));
		Product product = productservices.findById(pid);
		req.setAttribute("product", product);

	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ind = req.getParameter("index");
		int index = 0;
		if (ind != null) {
			index = Integer.parseInt(ind);
		}
		int count = 0;
		List<Product> list = new ArrayList<Product>();
		String filter = req.getParameter("filter");
		int cid = 0;
		String c = req.getParameter("cid");
		if (c != null) {
			cid = Integer.parseInt(c);
		}
		if (filter != null) {
			if (filter.equals("tat-ca")) {

				count = productservices.countByStatus(true);
				list = productservices.findByStatus(index, 8, true);
			} else if (filter.equals("categories")) {

				count = productservices.countByCategoryId(cid);
				list = productservices.findByCategoryId(cid, index, 8);

			}
		}
		int sizepage = count / 8;
		if (count % 8 != 0) {
			sizepage++;
		}

		req.setAttribute("list", list);
		req.setAttribute("index", index);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("filter", filter);
		req.setAttribute("cid", c);
	}

	public static void main(String[] args) throws Exception {

		IProductServices p = new ProductServicesImpl();
		// List<Product> list = p.findByName("áo phông", 0, 8);
		// int count = p.countByName("áo phông");
		// for (Product product : list) {
		// System.out.println(product.getProductName());
		// }
		// System.out.println(count);
		IOrderServices orderservices = new OrderServicesImpl();
		IUsersServices userservices = new UsersServicesImpl();
		Users user = userservices.findById(10);
		user.setFullname("Ninh Duc Tho 2002");
		userservices.update(user);
	}

}