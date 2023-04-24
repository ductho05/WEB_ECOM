package iostart.Controller.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostart.Entyti.Order_Item;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;
import iostart.Services.IOrderServices;
import iostart.Services.IOrder_ItemServices;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.Order_ItemServicesImpl;

@MultipartConfig

@WebServlet(urlPatterns = {"/admin-order","/admin-order-detail","/admin-save"})
public class OrderControlller extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IOrderServices orderservices = new OrderServicesImpl();
	IOrder_ItemServices order_item = new Order_ItemServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("admin-order-detail"))
		{
			findByOrder(req, resp);
			req.getRequestDispatcher("/views/admin/order/order-detail.jsp").forward(req, resp);
		}
		else if (url.contains("admin-save"))
		{
			save(req, resp);
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/order/list-order.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("admin-order-detail"))
		{
			findByOrder(req, resp);
		}
		else if (url.contains("admin-save"))
		{
			save(req, resp);
			resp.sendRedirect(req.getContextPath()+"/admin-order?index=0&filter=tat-ca");
		}
		else {
			findAll(req, resp);
			
		}
	}
	
protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String filter = req.getParameter("filter");
		if (filter != null && filter.equals("tat-ca"))
		{
			int count = orderservices.count();
			int sizepage = count/6;
			if (count % 6 != 0)
			{
				sizepage++;
			}
			int index = Integer.parseInt(req.getParameter("index"));
			String flag = "tat-ca";
			List<Orders> list = orderservices.findAll(index, 6);
			req.setAttribute("list", list);
			req.setAttribute("sizepage", sizepage);
			req.setAttribute("index", index);
			req.setAttribute("flag", flag);
			req.setAttribute("count", count);
			
		}
		else if (filter != null && filter.equals("dang-giao-hang"))
		{
			int count = orderservices.countByStatus("Đang giao hàng");
			int sizepage = count/6;
			if (count % 6 != 0)
			{
				sizepage++;
			}
			int index = Integer.parseInt(req.getParameter("index"));
			String flag = "dang-giao-hang";
			List<Orders> list = orderservices.findByStatus(index, sizepage, "Đang giao hàng");
			req.setAttribute("list", list);
			req.setAttribute("sizepage", sizepage);
			req.setAttribute("index", index);
			req.setAttribute("flag", flag);
			req.setAttribute("count", count);
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
	protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
			String status = req.getParameter("status");
			int order_id = Integer.parseInt(req.getParameter("order_id"));
			
			Orders order = orderservices.findById(order_id);
			order.setStatus(status);
			
			orderservices.update(order);
			req.setAttribute("order", order);
	}
	public static void  main(String[] args) throws Exception{
			
			IOrderServices od = new OrderServicesImpl();
			
			System.out.println(od.Sum_Price());
		}
}
