package iostart.Controller.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostart.Entyti.Product;
import iostart.Entyti.Seller;
import iostart.Entyti.Slides;
import iostart.Services.ISellerServices;
import iostart.Services.ISlidesServices;
import iostart.Services.Impl.SellerServicesImpl;
import iostart.Services.Impl.SlidesServicesImpl;

@WebServlet(urlPatterns = {"/admin-seller","/admin-seller/info-store","/admin-seller/save"})
public class SellerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ISellerServices sellerservices = new SellerServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("info-store"))
		{
			findAllJoinUser(req, resp);
			req.getRequestDispatcher("/views/admin/seller/store-detail.jsp").forward(req, resp);
		}
		else if (url.contains("save"))
		{
			Save(req, resp);
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/seller/seller-list.jsp").forward(req, resp);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("info-store"))
		{
			findAllJoinUser(req, resp);
		}
		else if (url.contains("save"))
		{
			Save(req, resp);
			resp.sendRedirect(req.getContextPath()+"/admin-seller?index=0");
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/seller/seller-list.jsp").forward(req, resp);
		}
	}
	protected void Save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		Seller seller = sellerservices.finById(sellerid);
		Boolean active = Boolean.valueOf(req.getParameter("active"));
		seller.setIsactive(active);
		sellerservices.update(seller);
	}
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filter = req.getParameter("filter");
		int index = Integer.parseInt(req.getParameter("index"));
		Boolean active = true;
		int count = 0;
		List<Seller> list_seller = new ArrayList<Seller>();
		if (filter != null && filter.equals("active"))
		{
			count = sellerservices.countByActive(active);
			list_seller = sellerservices.findAllByActive(index, 6, active);
		}
		else if (filter != null && filter.equals("ban"))
		{
			active = false;
			count = sellerservices.countByActive(active);
			list_seller = sellerservices.findAllByActive(index, 6, active);
		}
		else if (filter == null)
		{
			count = sellerservices.count();
			list_seller = sellerservices.findAll(index, 6);
		}
		int sizepage = count/6;
		if (count % 6 != 0)
		{
			sizepage++;
		}
		req.setAttribute("list_seller", list_seller);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	protected void findAllJoinUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		
		List<Seller> list = new ArrayList<>();
		List<Product> list_p = new ArrayList<Product>();
		List<Slides> list_slide = new ArrayList<Slides>();
		List<Object[]> listObjects = sellerservices.findAllJoinUser(sellerid);
		for (Object[] item : listObjects) {
			Seller seller = (Seller) item[0];
			Product product = (Product) item[2];
			Slides slide = (Slides) item[3];
			list.add(seller);
			list_p.add(product);
			list_slide.add(slide);
		}
		Set<Seller> set_listSeller = new LinkedHashSet<Seller>(list);
		Set<Product> set_listProduct = new LinkedHashSet<Product>(list_p);
		Set<Slides> set_listslide = new LinkedHashSet<Slides>(list_slide);
		
		List<Seller> new_list = new ArrayList<>(set_listSeller);
		List<Product> new_listp = new ArrayList<>(set_listProduct);
		List<Slides> new_listslide = new ArrayList<>(set_listslide);
			
		req.setAttribute("new_list", new_list);
		req.setAttribute("new_listp", new_listp);
		req.setAttribute("listslide", new_listslide);
	}
	public static void  main(String[] args) throws Exception{
		
		ISellerServices sellerservices = new SellerServicesImpl();
		List<Seller> list = new ArrayList<>();
		List<Product> list_p = new ArrayList<Product>();
		List<Slides> list_slide = new ArrayList<Slides>();
		List<Object[]> listObjects = sellerservices.findAllJoinUser(1);
		for (Object[] item : listObjects) {
			Seller seller = (Seller) item[0];
			Product product = (Product) item[2];
			Slides slide = (Slides) item[3];
			list.add(seller);
			list_p.add(product);
			list_slide.add(slide);
		}
		Set<Seller> set_listSeller = new LinkedHashSet<Seller>(list);
		Set<Product> set_listProduct = new LinkedHashSet<Product>(list_p);
		Set<Slides> set_listslide = new LinkedHashSet<Slides>(list_slide);
		List<Slides> new_listslide = new ArrayList<>(set_listslide);
		List<Seller> new_list = new ArrayList<>(set_listSeller);
		List<Product> new_listp = new ArrayList<>(set_listProduct);
		
		for (Slides  itemlist : new_listslide) {
			
			System.out.println(itemlist.getSlideimages());
		}
		
//		ISlidesServices slideservices = new SlidesServicesImpl();
//		List<Slides> list = slideservices.findAll();
//		for (Slides slides : list) {
//			System.out.println(slides.getSlideimages());
//		}
	}
}
