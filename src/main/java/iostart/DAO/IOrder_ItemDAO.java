package iostart.DAO;

import java.util.List;

import iostart.Entyti.Order_Item;

public interface IOrder_ItemDAO {

	List<Object[]> findByOrder(int order_id);

	List<Object[]> findTop10ProductByTime(String start_day, String end_day);

	void insert(Order_Item order_item);

}
