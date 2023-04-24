package iostart.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import iostart.Config.JpaConfig;
import iostart.DAO.IOrderDAO;
import iostart.Entyti.Order_Item;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;

public class OrderImpl implements IOrderDAO{
	
	@Override
	public void insert(Orders order)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order);
			trans.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			
			throw e;
		}
		finally {
			enma.close();
		}
	}

	@Override
	public List<Orders> findAll(int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		TypedQuery<Orders> query = enma.createNamedQuery("Orders.findAll", Orders.class);
		
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		
		return query.getResultList();
	}
	
	@Override
	public int count()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(o) FROM Orders o";
		
		Query query = enma.createQuery(jpql);
		
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public List<Orders> findByStatus(int page, int sizepage, String status)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "SELECT o FROM Orders o WHERE o.status = :status Order by o.created DESC";
		
		TypedQuery<Orders> query = enma.createQuery(jpql, Orders.class);
		
		query.setParameter("status", status);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		
		return query.getResultList();
	}
	
	@Override
	public void update(Orders order)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			
			trans.begin();
			enma.merge(order);
			trans.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
	}
	
	@Override
	public int countByStatus(String status)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(o) FROM Orders o WHERE o.status = :status";
		
		Query query = enma.createQuery(jpql);
		query.setParameter("status",status);
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public Orders findById(int order_id)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		Orders order = enma.find(Orders.class, order_id);
		
		return order;
	}
	
	@Override
	public float Sum_Price()
	{
		float result = 0;
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			String jpql = "SELECT Sum(o.total_price) FROM orders o WHERE o.status = :status";
			Query query = enma.createNativeQuery(jpql);
			query.setParameter("status","Đã hoàn thành");
			result = ((Double)query.getSingleResult()).floatValue();
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	@Override
	public List<Object[]> findTop10Customer(String startDay, String endDay)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jbql = "SELECT o.name, SUM(oi.quantity) as tong  FROM Orders o join Order_Item oi On o.id = oi.order_id  where o.created >= :startDay AND o.created <= :endDay group by o.name order by tong Desc";
		TypedQuery<Object[]> query = enma.createQuery(jbql, Object[].class);
		query.setParameter("startDay", startDay);
		query.setParameter("endDay", endDay);
		
		return query.setMaxResults(10).getResultList();
	}
	@Override
	public List<Object[]> findTop10CustomerAll()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jbql = "SELECT o.name, SUM(oi.quantity) as tong  FROM Orders o join Order_Item oi On o.id = oi.order_id group by o.name order by tong Desc";
		TypedQuery<Object[]> query = enma.createQuery(jbql, Object[].class);
		
		return query.setMaxResults(10).getResultList();
	}
	
	@Override
	public List<Object[]> findBySeller(int sellerid)
	{
		EntityManager em = JpaConfig.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Order_Item> order_itemRoot = criteriaQuery.from(Order_Item.class);
		Root<Orders> ordersRoot = criteriaQuery.from(Orders.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		
		criteriaQuery.multiselect(order_itemRoot,ordersRoot,productRoot);
		order_itemRoot.join("orders");
		order_itemRoot.join("product");
		criteriaQuery.where(builder.equal(productRoot.get("sellerId"),sellerid));
		TypedQuery<Object[]> query =  em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	@Override
	public int countBySeller(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(o) FROM Orders o WHERE o.user_id = :sellerid";
		Query query = enma.createQuery(jpql);
		query.setParameter("sellerid", sellerid);		
		
		return ((Long)query.getSingleResult()).intValue();
	}
	@Override
	public List<Object[]> findTop10CustomerBySeller(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jbql = "select o.name, Sum(oi.quantity) as Quantity from Orders o join Order_Item oi  ON o.id = oi.order_id join Product p On oi.product_id = p.productId \r\n"
				+ "where p.sellerId = :sellerid Group By o.name order by Quantity DESC ";
		TypedQuery<Object[]> query = enma.createQuery(jbql, Object[].class);
		query.setParameter("sellerid", sellerid);
		return query.setMaxResults(10).getResultList();
	}
	@Override
	public List<Object[]> findTop10ProductBySeller(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jbql = "select p.productName, Sum(p.amount) as Quantity from Orders o join Order_Item oi  ON o.id = oi.order_id join Product p On oi.product_id = p.productId \r\n"
				+ "where p.sellerId = :sellerid Group By p.productName order by Quantity DESC";
		TypedQuery<Object[]> query = enma.createQuery(jbql, Object[].class);
		query.setParameter("sellerid", sellerid);
		return query.setMaxResults(10).getResultList();
	}
	
	@Override
	public List<Orders> findByUser(int userid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "SELECT o FROM Orders o WHERE o.user_id = :userid Order by o.created DESC";
		
		TypedQuery<Orders> query = enma.createQuery(jpql, Orders.class);
		query.setParameter("userid", userid);
		
		return query.getResultList();
	}
}
