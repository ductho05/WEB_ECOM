package iostart.DAO.Impl;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import iostart.Config.JpaConfig;
import iostart.DAO.IOrder_ItemDAO;
import iostart.Entyti.Order_Item;
import iostart.Entyti.Orders;
import iostart.Entyti.Product;
public class Order_ItemImpl implements IOrder_ItemDAO{
	
	@Override
	public void insert(Order_Item order_item)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order_item);
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
	public List<Object[]> findByOrder(int order_id)
	{
		
		EntityManager em = JpaConfig.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Order_Item> order_itemRoot = criteriaQuery.from(Order_Item.class);
		Root<Orders> ordersRoot = criteriaQuery.from(Orders.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		
		criteriaQuery.multiselect(order_itemRoot,ordersRoot,productRoot);
		
//		criteriaQuery.where(builder.equal(order_itemRoot.get("order_id"), ordersRoot.get("id")));
//		criteriaQuery.where(builder.equal(order_itemRoot.get("product_id"), productRoot.get("productId")));
		
		order_itemRoot.join("orders");
		order_itemRoot.join("product");
		criteriaQuery.where(builder.equal(order_itemRoot.get("order_id"),order_id), builder.equal(ordersRoot.get("id"), order_itemRoot.get("order_id")), builder.equal(productRoot.get("productId"), order_itemRoot.get("product_id")));
//		criteriaQuery.where(builder.equal(ordersRoot.get("id"), order_id));
		
		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		
		List<Object[]> resultList = query.getResultList();
		
		return resultList;
	}
	
	@Override
	public List<Object[]> findTop10ProductByTime(String start_day,String end_day)
	{
		
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p.productName, Sum(oi.quantity) as Quantity FROM Order_Item oi join Orders o On oi.order_id = o.id join Product p ON oi.product_id = p.productId\r\n"
				+ "where o.created >= :start_day AND o.created <= :end_day Group By p.productName Order By Quantity DESC";
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		
		query.setParameter("start_day", start_day);
		query.setParameter("end_day", end_day);
		
		return query.setMaxResults(10).getResultList();
	}
	
}
