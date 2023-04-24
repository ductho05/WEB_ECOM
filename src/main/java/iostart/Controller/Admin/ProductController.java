package iostart.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostart.Entyti.Product;
import iostart.Services.IProductServices;
import iostart.Services.Impl.ProductServicesImpl;



@MultipartConfig

@WebServlet(urlPatterns = {"/admin-product","/admin-product/filter-active","/admin-product/filter-prohibit","/admin-product/update"})
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductServices productservices = new ProductServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("filter-active"))
		{
			FilterActive(req, resp);
			req.getRequestDispatcher("/views/admin/product/list-product.jsp").forward(req, resp);
		}
		else if (url.contains("filter-prohibit"))
		{
			FilterProhibit(req, resp);
			req.getRequestDispatcher("/views/admin/product/list-product.jsp").forward(req, resp);
		}
		else if (url.contains("update"))
		{
			findbyId(req, resp);
			req.getRequestDispatcher("/views/admin/product/update-product.jsp").forward(req, resp);
		}
		else
		{
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/product/list-product.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("filter-active"))
		{
			FilterActive(req, resp);		
		}
		else if (url.contains("filter-prohibit"))
		{
			FilterProhibit(req, resp);	
		}
		else if (url.contains("update"))
		{
			update(req, resp);
			resp.sendRedirect("/WEB_ECOM/admin-product?index=0");
		}
		else
		{
			findAll(req, resp);
		}
	}
	
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = productservices.count();
		int sizepage = count/6;
		if (count % 6 !=0 )
		{
			sizepage ++;
		}
		String index = req.getParameter("index");
		List<Product> list = productservices.findAll(Integer.parseInt(index), 6);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	protected void FilterActive(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = productservices.count();
		int sizepage = count/6;
		if (count % 6 !=0 )
		{
			sizepage ++;
		}
		String index = req.getParameter("index");
		List<Product> list = productservices.findByStatus(Integer.parseInt(index), 6,true);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	protected void FilterProhibit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = productservices.count();
		int sizepage = count/6;
		if (count % 6 !=0 )
		{
			sizepage ++;
		}
		String index = req.getParameter("index");
		List<Product> list = productservices.findByStatus(Integer.parseInt(index), 6,false);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			req.setCharacterEncoding("UTF-8");

			resp.setCharacterEncoding("UTF-8");

			String productid = req.getParameter("productId");
			
			Product product = productservices.findById(Integer.parseInt(productid));
			String sta = req.getParameter("status");
			Boolean status = true;
			if (sta.equals("0"))
			{
				status = false;
			}
			product.setStatus(status);
			productservices.update(product);
			req.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findbyId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			req.setCharacterEncoding("UTF-8");

			resp.setCharacterEncoding("UTF-8");
			
			String productid = req.getParameter("pid");

			Product product = productservices.findById(Integer.parseInt(productid));
			
			req.setAttribute("product", product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void  main(String[] args) throws Exception{
		
		IProductServices pr = new ProductServicesImpl();
		List<Object[]> list = pr.StatisPbyC();
		for (Object[] item : list) {
			
		}
		
	}
}
