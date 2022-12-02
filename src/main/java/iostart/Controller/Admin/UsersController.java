package iostart.Controller.Admin;

import java.io.File;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import iostart.Entyti.Users;
import iostart.Services.IUsersServices;
import iostart.Services.Impl.UsersServicesImpl;
import iostart.util.Constant;
import iostart.util.UploadUtils;

@MultipartConfig

@WebServlet(urlPatterns = {"/admin-user","/admin-user/create","/admin-user/delete","/admin-user/update"})
public class UsersController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IUsersServices userservices = new UsersServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		Users user = new Users();
		if (url.contains("create"))
		{
			req.getRequestDispatcher("/views/admin/users/add-users.jsp").forward(req, resp);
		}
		else if (url.contains("delete"))
		{
			delete(req, resp);
			Users users = new Users();
			req.setAttribute("users", users);
		}
		else if (url.contains("update"))
		{
			findById(req, resp);
			req.getRequestDispatcher("/views/admin/users/update-users.jsp").forward(req, resp);
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/users/list-users.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("create"))
		{
			insert(req, resp);
			resp.sendRedirect("/WEB_ECOM/admin-user?index=0");
		}
		else if (url.contains("delete"))
		{
			delete(req, resp);
			resp.sendRedirect("/WEB_ECOM/admin-user?index=0");
		
		}
		else if (url.contains("update"))
		{
			update(req, resp);
			resp.sendRedirect("/WEB_ECOM/admin-user?index=0");
		}
		else {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/users/list-users.jsp").forward(req, resp);
			
		}
	}
	
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int count = userservices.count();
		int sizepage = count/6;
		if (count % 6 != 0)
		{
			sizepage++;
		}
		String index = req.getParameter("index");
		List<Users> list = userservices.findAll(Integer.parseInt(index), 6);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("sizepage", sizepage);
		req.setAttribute("index", index);
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp)throws HttpRetryException, IOException {
		
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			Users user = new Users();
			
			BeanUtils.populate(user, req.getParameterMap());
			
			String fileName = String.valueOf(user.getUserid()) + System.currentTimeMillis();

			user.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\users\\", fileName));
			
			userservices.insert(user);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp)throws HttpRetryException, IOException {
		
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			String uid = req.getParameter("uid");
			userservices.delete(Integer.parseInt(uid));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void update(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");

			response.setCharacterEncoding("UTF-8");

// lấy dữ liệu từ jsp bằng BeanUtils

			Users user = new Users();

			BeanUtils.populate(user, request.getParameterMap());

// khởi tạo DAO
			
			
			Users u = userservices.findById(user.getUserid());

// xử lý hình ảnhc

			if (request.getPart("images").getSize() == 0) {

				user.setImages(u.getImages());

			} else {

				if (u.getImages() != null) {

// XOA ANH CU DI

					String fileName = u.getImages();

					File file = new File(Constant.DIR + "\\users\\" + fileName);

					if (file.delete()) {

						System.out.println("Đã xóa thành công");

					} else {

						System.out.println(Constant.DIR + "\\users\\" + fileName);

					}

				}

				String fileName = String.valueOf(user.getUserid()) + System.currentTimeMillis();

				user.setImages(
						UploadUtils.processUpload("images", request, Constant.DIR + "\\users\\", fileName));

//category.setImages(UploadUtils.processUploadFolderWeb("images", request, "/uploads", fileName));

			}

// khai báo danh sách và gọi hàm update trong service

			userservices.update(user);

// thông báo

			request.setAttribute("user", user);

			request.setAttribute("message", "Cập nhật thành công!");
			request.setAttribute("u", u);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		try {
			
			String userid = request.getParameter("uid");
			Users user = userservices.findById(Integer.parseInt(userid));
			request.setAttribute("user", user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
