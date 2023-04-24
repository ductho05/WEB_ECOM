package iostart.Services;

import java.util.List;

import iostart.Entyti.Orders;

public interface IOrderServices {

	void update(Orders order);

	List<Orders> findByStatus(int page, int sizepage, String status);

	int count();

	List<Orders> findAll(int page, int sizepage);
	
	int countByStatus(String status);
	
	Orders findById(int order_id);
	
	float Sum_Price();
	
	List<Object[]> findTop10Customer(String startDay, String endDay);
	
	List<Object[]> findTop10CustomerAll();

	void insert(Orders order);
	
	int countBySeller(int sellerid);

	List<Object[]> findBySeller(int sellerid);
	
	List<Object[]> findTop10CustomerBySeller(int sellerid);
	
	List<Object[]> findTop10ProductBySeller(int sellerid);
	
	List<Orders> findByUser(int userid);
}
