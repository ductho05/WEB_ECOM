package iostart.Controller.User;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iostart.Entyti.Users;
import iostart.util.SessionUtil;

import javax.servlet.ServletContext;

@WebServlet("/verifyotp")
public class VerifyOTP extends HttpServlet{
	ResourceBundle resourceBundle =  ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String message = req.getParameter("message");
		String alert = req.getParameter("alert");
		if (message != null && alert != null)
		{
			req.setAttribute("message", resourceBundle.getString(message));
			req.setAttribute("alert", alert);
		}
		RequestDispatcher rq = req.getRequestDispatcher("/views/verifyOtp.jsp");
		rq.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String otpInput = req.getParameter("otp");
		//Kiểm tra mã OTP và đăng nhập
		HttpSession session = req.getSession();
		String otp = (String) session.getAttribute("OTP");
		if (otp.equals(otpInput)) {
		    //Mã OTP đúng, đăng nhập thành công
			Users user = (Users) session.getAttribute("user");
			SessionUtil.getInstance().putValue(req, "Users", user);
			resp.sendRedirect(req.getContextPath() + "/home?index=0&filter=tat-ca&cid=0");
		} else {
		    //Mã OTP sai, đăng nhập thất bại
			resp.sendRedirect(req.getContextPath() + "/verifyotp?message=otp_incorrect&alert=danger");
		}
	}
}
