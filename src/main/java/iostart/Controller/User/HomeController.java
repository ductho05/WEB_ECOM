package iostart.Controller.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostart.Entyti.Users;
import iostart.Services.IProductServices;
import iostart.Services.IUsersServices;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.Services.Impl.UsersServicesImpl;
import iostart.util.SessionUtil;

@WebServlet(urlPatterns = {"/home","/dang-nhap","/dang-xuat","/dang-ky","/quen-mat-khau"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	IUsersServices userservices = new UsersServicesImpl();
	
	ResourceBundle resourceBundle =  ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		if (action != null && action.equals("login"))
		{
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null)
			{
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rq = req.getRequestDispatcher("/views/login.jsp");
			rq.forward(req, resp);
		}
		else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "Users");
			resp.sendRedirect(req.getContextPath()+"/home");
		}
		else if (action != null && action.equals("register"))
		{
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null)
			{
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rq = req.getRequestDispatcher("/views/register.jsp");
			rq.forward(req, resp);
		}
		else if (action != null && action.equals("forgot_password"))
		{
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null)
			{
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rq = req.getRequestDispatcher("/views/forgot_password.jsp");
			rq.forward(req, resp);
		}
		else
		{
			RequestDispatcher rq = req.getRequestDispatcher("/views/user/home.jsp");
			rq.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		if (action != null && action.equals("login"))
		{
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			Users user = userservices.findByUsernamePassRole(username, password);
			if (user != null)
			{
				SessionUtil.getInstance().putValue(req, "Users", user);
				if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Admin")))
				{
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
				else if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Seller")))
				{
					//	tạm thời chưa có seller, nên chuyển hướng sang customer
					resp.sendRedirect(req.getContextPath() + "/home");
				}
				else if (user.getRoleid() == Integer.parseInt(resourceBundle.getString("Customer")))
				{
					resp.sendRedirect(req.getContextPath() + "/home");
				}
			}
			
			else if (user == null){
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=username_password_isvalid&alert=danger");
			}
		}
		else if (action != null && action.equals("register"))
		{
			String user_name = req.getParameter("username");
			String email = req.getParameter("email");
			String fullname = req.getParameter("fullname");
			String pass1 = req.getParameter("password1");
			String pass2 = req.getParameter("password2");
			
			Users users = userservices.findByUsernameEmail(user_name, email);
			if (user_name.trim().length() == 0 || email.trim().length() == 0 || fullname.trim().length() == 0 || pass1.trim().length() == 0 || pass2.trim().length() == 0)
			{
				resp.sendRedirect(req.getContextPath()+"/dang-ky?action=register&message=text_none&alert=danger");
			}
			else if (users != null)
			{
				resp.sendRedirect(req.getContextPath()+"/dang-ky?action=register&message=username_email-already_exist&alert=danger");
			}
			
			else if (pass1.equals(pass2))
			{
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
				
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login");
						
			}else if (!pass1.equals(pass2))
			{
				resp.sendRedirect(req.getContextPath()+"/dang-ky?action=register&message=password_not_confilm&alert=danger");
			}
		}
		else if (action != null && action.equals("forgot_password"))
		{
			String fg_email = req.getParameter("email");
			String fg_pass1 = req.getParameter("password1");
			String fg_pass2 = req.getParameter("password2");
			
			Users u = userservices.findByEmail(fg_email);
			if (fg_email.trim().length() == 0 || fg_pass1.trim().length() == 0 || fg_pass2.trim().length() == 0)
			{
				resp.sendRedirect(req.getContextPath()+"/quen-mat-khau?action=forgot_password&message=text_none&alert=danger");
			}
			else if (u != null)
			{
				if (fg_pass1.equals(fg_pass2))
				{

					u.setPassword(fg_pass1);
					userservices.update(u);
					resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login");
					
				}
				else if (!fg_pass1.equals(fg_pass2))
				{
					resp.sendRedirect(req.getContextPath()+"/quen-mat-khau?action=forgot_password&message=password_not_confilm&alert=danger");
				}
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/quen-mat-khau?action=forgot_password&message=email_not_exist&alert=danger");
			}
			
		}
		
	}
	public static void  main(String[] args) throws Exception{
		
//		IUsersServices userservices = new UsersServicesImpl();
//		userservices.updatePassword("ductho@gmail.com", "2");
		IProductServices p = new ProductServicesImpl();
		
		p.updateStatus(2, false);
	}
	
}