package iostart.Controller.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import iostart.Entyti.Users;
import iostart.Services.IOrderServices;
import iostart.Services.IProductServices;
import iostart.Services.IUsersServices;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.Services.Impl.UsersServicesImpl;
import iostart.util.FusionCharts;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin-home","/change_password"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductServices productservices = new ProductServicesImpl();
	IUsersServices userservices = new UsersServicesImpl();
	IOrderServices orderservices = new OrderServicesImpl();
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		if (url.contains("change_password"))
		{
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null)
			{
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert",alert);
			}
			req.getRequestDispatcher("/views/change_password.jsp").forward(req, resp);
		}
		else {
			String num_Users =String.valueOf(userservices.count());
			String num_product = String.valueOf(productservices.SumAmount());
			String incomes = Float.toString(orderservices.Sum_Price());
			req.setAttribute("num_Users", num_Users);
			req.setAttribute("num_product", num_product);
			req.setAttribute("incomes", incomes);
			RequestDispatcher rq = req.getRequestDispatcher("/views/admin/home.jsp");
			rq.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURL().toString();
		if (url.contains("change_password"))
		{
			String email = req.getParameter("email");
			Users user = userservices.findByEmail(email);
			if (user != null)
			{
				String password = req.getParameter("password");
				String new_password = req.getParameter("newpassword");
				String new_password2 = req.getParameter("newpassword2");
				if (!password.equals(user.getPassword()))
				{
					resp.sendRedirect(req.getContextPath()+"/admin-home/change_password?message=incorrect_pass&alert=danger");
				}				
				else if (!new_password.equals(new_password2))
				{
					resp.sendRedirect(req.getContextPath()+"/admin-home/change_password?message=password_not_confilm&alert=danger");
				}
				else {
					user.setPassword(new_password);
					userservices.update(user);
					resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login");
				}				
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/admin-home/change_password?message=email_not_exist&alert=danger");
			}
		}
	}
	
	public String myFirstchart(String flag){
		

		List<Object[]> list_object = new ArrayList<Object[]>();
		if (flag.equals("1"))
		{
			list_object = productservices.StatisPbyAmount();
		} else if (flag.equals("0"))
		{
			list_object = productservices.StatisPbyC();
		}
		
		Gson gson = new Gson();
		Map<String, String> chartobj = new HashMap<String, String>();
		chartobj.put("caption", "Biểu đồ số lượng sản phẩm theo danh mục sản phẩm");
		chartobj.put("xAxisName", "Tên danh mục");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		ArrayList<Map> arrData = new ArrayList<>();
		
		for (Object[] objects : list_object) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			arrData.add(lv);
		}
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrData));
		FusionCharts fusionCharts = new FusionCharts("column3d", "chart1", "500", "500", "chart-column", "json",
				gson.toJson(dataMap));
		return fusionCharts.render();
	}

	public String mySecondchart(String flag) {

		List<Object[]> list_object = new ArrayList<Object[]>();
		if (flag.equals("1"))
		{
			list_object =  productservices.StatisPbyAmount();
		}else if (flag.equals("0"))
		{
			list_object = productservices.StatisPbyC();
		}
		Gson gson = new Gson();
		Map<String, String> chartobj = new HashMap<String, String>();
		chartobj.put("caption", "Biểu đồ tỷ lệ sản phẩm theo danh mục sản phẩm");
		chartobj.put("xAxisName", "Tên danh mục");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		ArrayList<Map> arrData = new ArrayList<>();
		for (Object[] objects : list_object) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			arrData.add(lv);
		}
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrData));
		FusionCharts fusionCharts = new FusionCharts("pie3d", "chart2", "500", "500", "chart-pie", "json",
				gson.toJson(dataMap));
		return fusionCharts.render();
	}
	
}