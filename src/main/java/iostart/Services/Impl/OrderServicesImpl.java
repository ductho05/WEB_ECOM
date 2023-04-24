package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.IOrderDAO;
import iostart.DAO.Impl.OrderImpl;
import iostart.Entyti.Orders;
import iostart.Services.IOrderServices;

public class OrderServicesImpl implements IOrderServices{

	IOrderDAO orderDAO = new OrderImpl();
	@Override
	public void update(Orders order) {
		
		orderDAO.update(order);
	}

	@Override
	public List<Orders> findByStatus(int page, int sizepage, String status) {
		
		return orderDAO.findByStatus(page, sizepage, status);
	}

	@Override
	public int count() {
		
		return orderDAO.count();
	}

	@Override
	public List<Orders> findAll(int page, int sizepage) {
		
		return orderDAO.findAll(page, sizepage);
	}

	@Override
	public int countByStatus(String status) {
		
		return orderDAO.countByStatus(status);
	}

	@Override
	public Orders findById(int order_id) {
		
		return orderDAO.findById(order_id);
	}

	@Override
	public float Sum_Price() {
		
		return orderDAO.Sum_Price();
	}

	@Override
	public List<Object[]> findTop10Customer(String startDay, String endDay) {
		
		return orderDAO.findTop10Customer(startDay, endDay);
	}

	@Override
	public List<Object[]> findTop10CustomerAll() {
		
		return orderDAO.findTop10CustomerAll();
	}

	@Override
	public void insert(Orders order) {
		
		orderDAO.insert(order);
	}

	@Override
	public int countBySeller(int sellerid) {
		
		return orderDAO.countBySeller(sellerid);
	}

	@Override
	public List<Object[]> findBySeller(int sellerid) {
		
		return orderDAO.findBySeller(sellerid);
	}

	@Override
	public List<Object[]> findTop10CustomerBySeller(int sellerid) {
		
		return orderDAO.findTop10CustomerBySeller(sellerid);
	}

	@Override
	public List<Object[]> findTop10ProductBySeller(int sellerid) {
		
		return orderDAO.findTop10ProductBySeller(sellerid);
	}

	@Override
	public List<Orders> findByUser(int userid) {
		
		return orderDAO.findByUser(userid);
	}

}
