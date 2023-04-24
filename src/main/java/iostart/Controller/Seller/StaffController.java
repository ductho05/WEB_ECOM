package iostart.Controller.Seller;

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

import iostart.Entyti.Seller;
import iostart.Entyti.Staff;
import iostart.Services.ISellerServices;
import iostart.Services.IStaffServices;
import iostart.Services.Impl.SellerServicesImpl;
import iostart.Services.Impl.StaffServicesImpl;
import iostart.util.Constant;
import iostart.util.SessionUtil;
import iostart.util.UploadUtils;

@MultipartConfig

@WebServlet(urlPatterns = {"/seller-store-staff-list","/seller-store-add-staff","/seller-store-update-staff","/seller-store-delete-staff"})
public class StaffController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IStaffServices staffservices = new StaffServicesImpl();
	ISellerServices sellerservices = new SellerServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("seller-store-add-staff"))
		{
			List<Seller> seller = sellerservices.findAll();
			req.setAttribute("seller", seller);
			req.getRequestDispatcher("/views/store/staff/add-staff.jsp").forward(req, resp);
		}
		else if (url.contains("seller-store-update-staff"))
		{
			List<Seller> seller = sellerservices.findAll();			
			int staffid = Integer.parseInt(req.getParameter("staffid"));
			Staff staff = staffservices.findById(staffid);
			req.setAttribute("seller", seller);
			req.setAttribute("staff", staff);
			req.getRequestDispatcher("/views/store/staff/update-staff.jsp").forward(req, resp);
		}
		else if (url.contains("/seller-store-delete-staff"))
		{
			delete(req, resp);
			Staff staff = new Staff();
			req.setAttribute("staff", staff);
			resp.sendRedirect(req.getContextPath()+"/seller-store-staff-list?sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid"));
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/store/staff/staff-list.jsp").forward(req, resp);
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("seller-store-add-staff"))
		{
			insert(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store-staff-list?sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid"));
		}
		else if (url.contains("seller-store-update-staff"))
		{
			update(req, resp);
			resp.sendRedirect(req.getContextPath()+"/seller-store-staff-list?sellerid="+SessionUtil.getInstance().getValue(req, "Sellerid"));
		}
		else if (url.contains("/seller-store-delete-staff"))
		{
			delete(req, resp);
			
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/store/staff/staff-list.jsp").forward(req, resp);
		}	
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int staffid = Integer.parseInt(req.getParameter("staffid"));
			staffservices.delete(staffid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			Staff staff = new Staff();
			BeanUtils.populate(staff, req.getParameterMap());
			
			Staff st = staffservices.findById(staff.getId());
			if (req.getPart("images").getSize() == 0) {
				staff.setImages(st.getImages());
			}
			else
			{
				if (st.getImages() != null)
				{
					String fileName = st.getImages();
					File file = new File(Constant.DIR + "\\satff\\" + fileName);
					if (file.delete()) {
						System.out.print("Đã xóa thành công");
					}
					else {
						System.out.print(Constant.DIR + "\\satff\\" + fileName);
					}
				}
				String fileName = String.valueOf(staff.getId()) + System.currentTimeMillis();
				staff.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\staff\\", fileName));
			}
			staffservices.update(staff);
			req.setAttribute("staff", staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			Staff staff = new Staff();
			
			BeanUtils.populate(staff, req.getParameterMap());
			String fileName = String.valueOf(staff.getId()) + System.currentTimeMillis();			
			staff.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\staff\\", fileName));
			
			staffservices.insert(staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		List<Staff> list = staffservices.findBYSellerid(sellerid);
		req.setAttribute("list",list);
	}
	public static void main(String[] args) throws Exception {
		IStaffServices staffservices = new StaffServicesImpl();
		staffservices.delete(3);
	}
}
