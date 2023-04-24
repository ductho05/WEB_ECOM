package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.IOrder_ItemDAO;
import iostart.DAO.Impl.Order_ItemImpl;
import iostart.Entyti.Order_Item;
import iostart.Services.IOrder_ItemServices;

public class Order_ItemServicesImpl implements IOrder_ItemServices{

	IOrder_ItemDAO order_itemDAO = new Order_ItemImpl();
	@Override
	public List<Object[]> findByOrder(int order_id) {
		
		return order_itemDAO.findByOrder(order_id);
	}
	@Override
	public List<Object[]> findTop10ProductByTime(String start_day, String end_day) {
		
		return order_itemDAO.findTop10ProductByTime(start_day, end_day);
	}
	@Override
	public void insert(Order_Item order_item) {
		order_itemDAO.insert(order_item);
	}

}
