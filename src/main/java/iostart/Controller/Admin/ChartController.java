package iostart.Controller.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import iostart.Services.IOrderServices;
import iostart.Services.IOrder_ItemServices;
import iostart.Services.IProductServices;
import iostart.Services.ISellerServices;
import iostart.Services.Impl.OrderServicesImpl;
import iostart.Services.Impl.Order_ItemServicesImpl;
import iostart.Services.Impl.ProductServicesImpl;
import iostart.Services.Impl.SellerServicesImpl;
import iostart.util.FusionCharts;

@MultipartConfig

@WebServlet(urlPatterns = {"/admin-chart/top10customer","/admin-chart/top10product","/admin-chart/top10store"})
public class ChartController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	IOrderServices orderservices = new OrderServicesImpl();
	IOrder_ItemServices order_itemservices = new Order_ItemServicesImpl();
	IProductServices productservices = new ProductServicesImpl();
	ISellerServices sellerservice = new SellerServicesImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURL().toString();
		if (url.contains("top10customer"))
		{
      	
			req.getRequestDispatcher("/views/admin/statistic/myFirstChart.jsp").forward(req, resp);
		}
		else if (url.contains("top10product"))
		{			
			req.getRequestDispatcher("/views/admin/statistic/mySecondChart.jsp").forward(req, resp);
		}
		else if (url.contains("top10store"))
		{
			req.getRequestDispatcher("/views/admin/statistic/myThirdChart.jsp").forward(req, resp);
		}
	}
	public String myFirstChart(String start_day,String end_day, String typechart, String chartid,String filter){
				
		String caption = "";
		List<Object[]> list = new ArrayList<Object[]>();
		if (filter.equals("tat-ca"))
		{
			caption = "Biểu đồ Top 10 khách mua hàng nhiều nhất";
			list = orderservices.findTop10CustomerAll();
		}
		else {
			caption = "Biểu đồ Top 10 khách mua hàng nhiều nhất từ " + start_day+" đến "+end_day;
			list = orderservices.findTop10Customer(start_day, end_day);
		}
		Gson gson = new Gson();
		Map<String, String> chartobj = new HashMap<String, String>();
		chartobj.put("caption", caption);
		chartobj.put("xAxisName", "Tên khách hàng");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		
		ArrayList<Map> arrData = new ArrayList<>();
		for (Object[] object : list) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label",object[0].toString());
			lv.put("value", object[1].toString());
			
			arrData.add(lv);
		}
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrData));
		
		FusionCharts fusionCharts = new FusionCharts(typechart, "chart3", "1000", "500", chartid, "json", gson.toJson(dataMap));
		return fusionCharts.render();
	}
	
	public String mySecondChart(String start_day, String end_day, String type_chart, String chart_id, String filter)
	{
		String caption = "";
		List<Object[]> list = new ArrayList<Object[]>();
		Gson gson = new Gson();
		if (filter.equals("tat-ca"))
		{
			list = productservices.findTop10Product();
			caption = "Biểu đồ Top 10 sản phẩm bán chạy nhất";
		}
		else {
			list = order_itemservices.findTop10ProductByTime(start_day, end_day);
			caption = "Biểu đồ top 10 sản phẩm bán chạy nhất từ "+start_day+" đến "+end_day;
		}
		
		Map<String, String> chartobj = new HashMap<String,String>();
		chartobj.put("caption", caption);
		chartobj.put("xAxisName", "Tên sản phẩm");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		
		ArrayList<Map> arrData = new ArrayList<>();
		for (Object[] objects : list) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			
			arrData.add(lv);
		}
		
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrData));
		
		FusionCharts fusionCharts = new FusionCharts(type_chart, "chart4","1000","500",chart_id,"json",gson.toJson(dataMap));
		return fusionCharts.render();
	}
	
	public String myThirdChart(String start_day,String end_day, String type_chart,String chart_id, String filter)
	{
		String caption = "";
		List<Object[]> list = new ArrayList<Object[]>();
		Gson gson = new Gson();
		
		if (filter.equals("tat-ca"))
		{
			list = sellerservice.findTop10Store();
			caption = "Biểu đồ top 10 cửa hàng bán được nhiều sản phẩm nhất";
		}
		else
		{
			list = sellerservice.findTop10StoreByTime(start_day, end_day);
			caption = "Biểu đồ top 10 cửa hàng bán được nhiều sản phẩm nhất từ "+start_day +" đến "+end_day;
		}
		
		Map<String, String> chartobj = new HashMap<String,String>();
		chartobj.put("caption", caption);
		chartobj.put("xAxisName", "Tên cửa hàng");
		chartobj.put("yAxisName", "Số lượng");
		chartobj.put("numberSuffix", " sản phẩm");
		chartobj.put("theme", "fusion");
		
		ArrayList<Map> arrData = new ArrayList<>();
		for (Object[] objects : list) {
			Map<String, String> lv = new HashMap<String, String>();
			lv.put("label", objects[0].toString());
			lv.put("value", objects[1].toString());
			
			arrData.add(lv);
		}
		
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("chart", gson.toJson(chartobj));
		dataMap.put("data", gson.toJson(arrData));
		
		FusionCharts fusionCharts = new FusionCharts(type_chart,"chart5","1000","500",chart_id,"json",gson.toJson(dataMap));
		return fusionCharts.render();
	}
	public static void  main(String[] args) throws Exception{
		
		ISellerServices sellerservice = new SellerServicesImpl();
		List<Object[]> list = sellerservice.findTop10StoreByTime("2022-12-04", "2022-12-12");
		List<Object[]> list2 = sellerservice.findTop10Store();
		for (Object[] objects : list2) {
			System.out.println(objects[0].toString());
			System.out.println(objects[1].toString());
		}
	}
}
