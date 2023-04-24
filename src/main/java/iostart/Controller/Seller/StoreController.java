package iostart.Controller.Seller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import iostart.Entyti.Category;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;
import iostart.Entyti.Seller;
import iostart.Services.ICategoryServices;
import iostart.Services.IOrderServices;
import iostart.Services.IOrder_ItemServices;
import iostart.Services.IProductServices;
import iostart.Services.ISellerServices;
import iostart.Services.Impl.CategoryServicesImpl;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.Order_ItemServicesImpl;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.Services.Impl.SellerServicesImpl;
import iostart.util.Constant;
import iostart.util.FusionCharts;
import iostart.util.SessionUtil;
import iostart.util.UploadUtils;

@MultipartConfig

@WebServlet(urlPatterns = {"/seller-store_home","/seller-store_home/store-profile","/seller-store_home/store-product","/seller-store_home/store-delete-product","/seller-store_home/store-add","/seller-store_home/store-update","/seller-store_home/list-order","/seller-store_home/update-orders","/seller-store_home/top10customer","/seller-store_home/top10product"})
public class StoreController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ISellerServices sellerservices = new SellerServicesImpl(); 
	IProductServices productservices = new ProductServicesImpl();
	ICategoryServices categoryservices = new CategoryServicesImpl();
	IOrderServices orderservices = new OrderServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("store-profile"))
		{
			findStore(req, resp);
			req.getRequestDispatcher("/views/store/profile-store.jsp").forward(req, resp);
		}
		else if (url.contains("store-product"))
		{
			findProduct(req, resp);
			req.getRequestDispatcher("/views/store/product-list.jsp").forward(req, resp);
		}
		else if (url.contains("store-add"))
		{
			List<Category> list_category = categoryservices.findAll();
			req.setAttribute("list_category", list_category);
			req.getRequestDispatcher("/views/store/store-add.jsp").forward(req, resp);
		}
		else if (url.contains("store-update"))
		{
			int pid = Integer.parseInt(req.getParameter("pid"));
		 	Product product =  productservices.findById(pid);
		 	List<Category> list_category = categoryservices.findAll();
			req.setAttribute("list_category", list_category);
			req.setAttribute("product", product);
			req.getRequestDispatcher("/views/store/product-update.jsp").forward(req, resp);
		}
		else if (url.contains("store-delete-product"))
		{
			deleteProduct(req, resp);
			Product product = new Product();
			req.setAttribute("product", product);
			resp.sendRedirect(req.getContextPath()+"/seller-store_home/store-product?index=0&sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid")+"&filter=all");
		}
		else if (url.contains("list-order"))
		{
			findOrderBySeller(req, resp);
			req.getRequestDispatcher("/views/store/list-order.jsp").forward(req, resp);
		}
		else if (url.contains("update-orders"))
		{
			int orderid = Integer.parseInt(req.getParameter("order_id"));
			Orders order = orderservices.findById(orderid);
			req.setAttribute("order", order);
			req.getRequestDispatcher("/views/store/update-orders.jsp").forward(req, resp);
		}
		else if (url.contains("top10customer"))
		{
			req.getRequestDispatcher("/views/store/myFirstChart.jsp").forward(req, resp);
		}
		else if (url.contains("top10product"))
		{
			req.getRequestDispatcher("/views/store/mySecondChart.jsp").forward(req, resp);
		}
		else {
			int sellerid = Integer.parseInt(req.getParameter("sellerid"));
			SessionUtil.getInstance().putValue(req, "Sellerid", sellerid);
			Seller seller = sellerservices.finById(sellerid);
			req.setAttribute("seller", seller);
			req.getRequestDispatcher("/views/store/home.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("store-profile"))
		{
			update(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store_home?sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid"));
		}
		else if (url.contains("store-product"))
		{
			findProduct(req, resp);
		}
		else if (url.contains("store-add"))
		{
			insert(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store_home/store-product?index=0&sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid")+"&filter=all");
		}
		else if (url.contains("store-delete-product"))
		{
			deleteProduct(req, resp);
			
		}
		else if (url.contains("store-update"))
		{
			updateProduct(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store_home/store-product?index=0&sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid")+"&filter=all");
		}
		else if (url.contains("update-orders"))
		{
			updateOrders(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store_home/list-order?sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid"));
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/store/home.jsp").forward(req, resp);
		}
		
	}
	public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		int productId = Integer.parseInt(req.getParameter("pid"));
		productservices.delete(productId);
		
	}
	public String top10Product(int sellerid, String typeChart, String chartid){
		
		Gson gson = new Gson();
		Map<String, String> chartobj = new LinkedHashMap<String, String>();
		chartobj.put("caption", "Biểu đồ top 10 sản phẩm được mua hàng nhiều nhất tại cửa hàng");
		chartobj.put("xAxisName", "Tên sản phẩm");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		
		ArrayList<Map> arrayData = new ArrayList<>();
		List<Object[]> list = orderservices.findTop10ProductBySeller(sellerid);
		for (Object[] objects : list) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			
			arrayData.add(lv);
		}
		
		Map<String, String> dataMap = new LinkedHashMap<>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrayData));
		
		FusionCharts fusionCharts = new FusionCharts(typeChart, "chart2","1000","500", chartid,"json",gson.toJson(dataMap));
		
		return fusionCharts.render();
	}
	public String top10Customer(int sellerid, String typeChart, String chartid){
		
		Gson gson = new Gson();
		Map<String, String> chartobj = new LinkedHashMap<>();
		chartobj.put("caption", "Biểu đồ top 10 khách hàng mua hàng nhiều nhất tại cửa hàng");
		chartobj.put("xAxisName", "Tên khách hàng");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		
		ArrayList<Map> arrayData = new ArrayList<>();
		List<Object[]> list = orderservices.findTop10CustomerBySeller(sellerid);
		for (Object[] objects : list) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			
			arrayData.add(lv);
		}
		
		Map<String, String> dataMap = new LinkedHashMap<>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrayData));
		
		FusionCharts fusionCharts = new FusionCharts(typeChart, "chart1","1000","500", chartid,"json",gson.toJson(dataMap));
		
		return fusionCharts.render();
	}
	protected void updateOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int orderid = Integer.parseInt(req.getParameter("id"));
		Orders order = orderservices.findById(orderid);
		String status = req.getParameter("status");
		order.setStatus(status);
		
		orderservices.update(order);
	}
	protected void findOrderBySeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		List<Object[]> list = orderservices.findBySeller(sellerid);
		List<Orders> list_order = new ArrayList<Orders>();
		for (Object[] objects : list) {
			Orders order = new Orders();
			order = (Orders) objects[1];
			list_order.add(order);
		}
		Set<Orders> set_listorder = new LinkedHashSet<Orders>(list_order);
		List<Orders> new_list = new ArrayList<>(set_listorder);
		
		req.setAttribute("new_list", new_list);	
		
	}
	protected void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try 
		{
			Product product = new Product();
			BeanUtils.populate(product, req.getParameterMap());
			
			Product pro = productservices.findById(product.getProductId());
			if(req.getPart("images").getSize() == 0)
			{
				product.setImages(pro.getImages());
			}
			else
			{
				if (pro.getImages() != null)
				{
					String fileName = pro.getImages();

					File file = new File(Constant.DIR + "\\product\\" + fileName);
					
					if (file.delete())
					{
						System.out.print("Xóa thành công");
					}
					else {
						System.out.print(Constant.DIR + "\\product\\" + fileName);
					}
				}
				String fileName = String.valueOf(product.getProductId()) + System.currentTimeMillis();

				product.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\product\\", fileName));
			}
			
			productservices.update(product);
			
			req.setAttribute("product", product);
		} catch (Exception e) {
			
		}
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try
		{
			Product product = new Product();
			BeanUtils.populate(product, req.getParameterMap());
			
			String fileName = String.valueOf(product.getProductId()) + System.currentTimeMillis();

			product.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\product\\", fileName));
			
			int sellerid = (int) SessionUtil.getInstance().getValue(req, "Sellerid");
			
			product.setSellerId(sellerid);
			Calendar createat = Calendar.getInstance();
			SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");			
			String createDate = String.valueOf(dfm.format(createat.getTime()));	
			product.setCreateDate(createDate);
			product.setStatus(true);
			productservices.insert(product);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		String filter = req.getParameter("filter");
		int index = Integer.parseInt(req.getParameter("index"));
		int count = 0;
		List<Product> list = new ArrayList<Product>();
		if (filter.equals("active"))
		{
			count = productservices.countBySellerFilter(sellerid, true);
			list = productservices.findBySellerFilter(sellerid, true, index, 6);
		}
		else if (filter.equals("ban"))
		{
			count = productservices.countBySellerFilter(sellerid, false);
			list = productservices.findBySellerFilter(sellerid, false, index, 6);
		}
		else if (filter.equals("all")){
			count = productservices.countBySellerid(sellerid);
			list = productservices.findBySellerId(sellerid, index, 6);
		}		
		int sizepage = count/6;
		if (count%6 != 0)
		{
			sizepage++;
		}
			
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Seller> list = sellerservices.findAll(0, 6);
		req.setAttribute("list", list);
	}
	
	protected void findStore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		List<Object[]> objects = sellerservices.findAllJoinUser(sellerid);		
		Seller seller = new Seller();
		for (Object[] object : objects) {			
			seller = (Seller) object[0];
		}
		req.setAttribute("seller", seller);
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
	try {
			Seller seller = new Seller();
			BeanUtils.populate(seller, req.getParameterMap());		
			Seller sel = sellerservices.finById(seller.getSellerid());
			if (req.getPart("images").getSize() == 0)
			{
				seller.setImages(sel.getImages());
			}
			else
			{
				if (sel.getImages() != null)
				{
					String fileName = sel.getImages();
					File file = new File(Constant.DIR +"\\seller\\" + fileName);
					if (file.delete())
					{
						System.out.println("Đã xóa thành công");
					}
					else {
						System.out.println(Constant.DIR +"\\seller\\" + fileName);
					}
				}
				String fileName = String.valueOf(seller.getSellerid()) + System.currentTimeMillis();
				seller.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\seller\\", fileName));
			}
			sellerservices.update(seller);
			
			req.setAttribute("seller", seller);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		IOrderServices orderservices = new OrderServicesImpl();
		List<Object[]> list = orderservices.findTop10ProductBySeller(1);
		for (Object[] objects : list) {
			System.out.println(objects[0] + " "+objects[1]);
		}
		
	}
}
