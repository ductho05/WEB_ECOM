package iostart.Controller.Seller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import iostart.Entyti.Seller;
import iostart.Entyti.Users;
import iostart.Services.ISellerServices;
import iostart.Services.Impl.SellerServicesImpl;
import iostart.util.Constant;
import iostart.util.SessionUtil;
import iostart.util.UploadUtils;

@MultipartConfig

@WebServlet(urlPatterns = {"/seller-home","/seller-home/insert-seller","/seller-home/list-seller"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	ISellerServices sellerservices = new SellerServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("insert-seller"))
		{
			insert(req, resp);
			req.getRequestDispatcher("/views/seller/insert-seller.jsp").forward(req, resp);
		}
		else if (url.contains("list-seller"))
		{
			findAll(req, resp);
			req.getRequestDispatcher("/views/seller/home.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("insert-seller"))
		{
			insert(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-home/list-seller?index=0&filter=tat-ca");
		}
		else if (url.contains("list-seller"))
		{
			findAll(req, resp);			
		}
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try {
			Seller seller = new Seller();
			
			BeanUtils.populate(seller, req.getParameterMap());
			String fileName = String.valueOf(seller.getSellerid()) + System.currentTimeMillis();
			seller.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\seller\\", fileName));
			
			Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");
			int ownerid = user.getUserid();
			seller.setOwnerid(ownerid);
			seller.setIsopen(true);
			sellerservices.insert(seller);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = 0;
		List<Seller> list = new ArrayList<Seller>();
		String filter = req.getParameter("filter");
		int index = Integer.parseInt(req.getParameter("index"));
		if (filter.equals("cap-phep"))
		{
			count = sellerservices.countByActive(true);
			list = sellerservices.findAllByActive(index,6, true);
		}
		else if (filter.equals("cam"))
		{
			count = sellerservices.countByActive(false);
			list = sellerservices.findAllByActive(index, 6, false);
		}else if (filter.equals("tat-ca"))
		{
			count = sellerservices.count();
			Users user = (Users) SessionUtil.getInstance().getValue(req, "Users");
			int ownerid = user.getUserid();
			list = sellerservices.findByUserid(ownerid, index, 6);
		}
		int sizepage = count / 6;
		if (count%6 !=0)
		{
			sizepage++;			
		}
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	public static void  main(String[] args) throws Exception{
		ISellerServices sellerservices = new SellerServicesImpl();
		List<Seller> list_seller = sellerservices.findByUserid(24,0,6);
		for (Seller seller : list_seller) {
			System.out.println(seller.getSellername());
		}
	}
}
