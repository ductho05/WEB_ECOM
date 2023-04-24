package iostart.Controller.Admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import iostart.Entyti.Shipper;
import iostart.Services.IShipperServices;
import iostart.Services.Impl.ShipperServicesImpl;
import iostart.util.Constant;
import iostart.util.UploadUtils;

@MultipartConfig

@WebServlet(urlPatterns = {"/admin-shipper","/admin-shipper/insert","/admin-shipper/update","/admin-shipper/delete"})
public class ShipperController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IShipperServices shipperservces = new ShipperServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("insert"))
		{
			insert(req, resp);
			req.getRequestDispatcher("/views/admin/shipper/add-shipper.jsp").forward(req, resp);
		}
		else if (url.contains("update"))
		{
			findById(req, resp);
			req.getRequestDispatcher("/views/admin/shipper/update-shipper.jsp").forward(req, resp);
		}
		else if (url.contains("delete"))
		{
			delete(req, resp);
			Shipper shipper = new Shipper();
			req.setAttribute("shipper", shipper);
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/shipper/list-shipper.jsp").forward(req, resp);
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("insert"))
		{
			insert(req, resp);
			resp.sendRedirect(req.getContextPath() + "/admin-shipper?index=0");
		}
		else if (url.contains("update"))
		{
			update(req, resp);
			resp.sendRedirect(req.getContextPath()+"/admin-shipper?index=0");
		}
		else if (url.contains("delete"))
		{
			delete(req, resp);
			resp.sendRedirect(req.getContextPath()+"/admin-shipper?index=0");
		}
		else {
			findAll(req, resp);
		}		
	}
	
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = shipperservces.count();
		int sizepage = count/6;
		if (count % 6 != 0)
		{
			sizepage++;
		}
		int index = Integer.parseInt(req.getParameter("index"));
		List<Shipper> list_shipper = shipperservces.findAll(index,6);
		
		req.setAttribute("list_shipper", list_shipper);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			Shipper shipper = new Shipper();
			
			BeanUtils.populate(shipper, req.getParameterMap());
			
			String filename = String.valueOf(shipper.getId()) + System.currentTimeMillis();
			
			shipper.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\shipper\\", filename));
			
			shipperservces.insert(shipper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			Shipper shipper = new Shipper();
			BeanUtils.populate(shipper, req.getParameterMap());
			
			Shipper sp = shipperservces.findById(shipper.getId());
			
			if (req.getPart("images").getSize() == 0)
			{
				shipper.setImages(sp.getImages());
			}
			else {
				if (sp.getImages() != null)
				{
					String filename = sp.getImages();
					File file = new File(Constant.DIR + "\\shipper\\" + filename);

					if (file.delete()) {

						System.out.println("Đã xóa thành công");

					} else {

						System.out.println(Constant.DIR + "\\shipper\\" + filename);

					}
				}
				String filename = String.valueOf(shipper.getId()) + System.currentTimeMillis();			
				shipper.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\shipper\\", filename));
			}
					
			shipperservces.update(shipper);
			
			req.setAttribute("shipper", shipper);
			req.setAttribute("sp", sp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		Shipper shipper = shipperservces.findById(id);
		
		req.setAttribute("shipper", shipper);
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		shipperservces.delete(id);
	}
	public static void  main(String[] args) throws Exception{
		IShipperServices shipperservces = new ShipperServicesImpl();
		List<Shipper> list = shipperservces.findAll(0, 6);
		for (Shipper shipper : list) {
			System.out.println(shipper.getName());
		}
	}
}
