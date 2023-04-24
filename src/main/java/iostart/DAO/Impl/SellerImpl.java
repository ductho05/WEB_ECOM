package iostart.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import iostart.Config.JpaConfig;
import iostart.DAO.ISellerDAO;
import iostart.Entyti.Product;
import iostart.Entyti.Seller;
import iostart.Entyti.Slides;
import iostart.Entyti.Users;

public class SellerImpl implements ISellerDAO{
	
	@Override
	public void insert(Seller seller)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.persist(seller);
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
	public Seller finById(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		Seller seller = enma.find(Seller.class,sellerid);
		
		return seller;
	}
	
	@Override
	public List<Seller> findAll(int page, int pagesize)
	{
		List<Seller> list = new ArrayList<Seller>();
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			TypedQuery<Seller> query = enma.createNamedQuery("Seller.findAll",Seller.class);
			
			query.setFirstResult(page*pagesize);
			
			query.setMaxResults(pagesize);
			list =  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		
		return list;
	}
	@Override
	public List<Seller> findAll()
	{
		List<Seller> list = new ArrayList<Seller>();
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			TypedQuery<Seller> query = enma.createNamedQuery("Seller.findAll",Seller.class);
			list =  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		
		return list;
	}
	@Override
	public int count()
	{
		int count = 0;
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			String jpql = "SELECT count(s.sellerid) FROM Seller s";
			Query query = enma.createQuery(jpql);
			count = ((Long)query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return count;
	}
	
	@Override
	public List<Seller> findAllByActive(int page, int pagesize, Boolean isactive)
	{
		List<Seller> list = new ArrayList<Seller>();
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			
			String jpql = "SELECT s FROM Seller s WHERE s.isactive = :isactive";
			TypedQuery<Seller> query = enma.createQuery(jpql,Seller.class);
			
			query.setParameter("isactive",isactive);
			query.setFirstResult(page*pagesize);
			
			query.setMaxResults(pagesize);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		
		return list;
	}
	
	@Override
	public int countByActive(Boolean isactive)
	{
		int count = 0;
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			String jpql = "SELECT count(s.sellerid) FROM Seller s WHERE s.isactive = :isactive ";
			Query query = enma.createQuery(jpql);
			query.setParameter("isactive", isactive);
			count = ((Long)query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<Object[]> findAllJoinUser(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		CriteriaBuilder builder = enma.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Seller> sellerRoot = criteriaQuery.from(Seller.class);
		Root<Users> userRoot = criteriaQuery.from(Users.class);
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		Root<Slides> slideRoot = criteriaQuery.from(Slides.class);
		
		criteriaQuery.multiselect(sellerRoot,userRoot,productRoot,slideRoot);
		
		sellerRoot.join("user");
		sellerRoot.join("products");
		sellerRoot.join("slides");
		criteriaQuery.where(builder.equal(sellerRoot.get("sellerid"), sellerid), builder.equal(sellerRoot.get("sellerid"),productRoot.get("sellerId")), builder.equal(sellerRoot.get("sellerid"),slideRoot.get("sellerid")));
		TypedQuery<Object[]> query = enma.createQuery(criteriaQuery);
		
		List<Object[]> list = query.getResultList();
		
		return list;
	}
	
	@Override
	public List<Object[]> findAllByUser()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		CriteriaBuilder builder = enma.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<Seller> sellerRoot = criteriaQuery.from(Seller.class);
		Root<Users> userRoot = criteriaQuery.from(Users.class);
		
		sellerRoot.join("user");
		criteriaQuery.where(builder.equal(sellerRoot.get("ownerid"), userRoot.get("userid")));
		TypedQuery<Object[]> query = enma.createQuery(criteriaQuery);
		List<Object[]> list = query.getResultList();
		return list;
	}
	@Override
	public List<Seller> findByUserid(int userid, int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Seller s WHERE s.ownerid = :userid";
		TypedQuery<Seller> query = enma.createQuery(jpql, Seller.class);
		query.setParameter("userid", userid);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		return query.getResultList();
	}
	@Override
	public void update(Seller seller)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.merge(seller);
			trans.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
		
	}
	
	@Override
	public List<Object[]> findTop10Store()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s.sellername as Name, Sum(oi.quantity) as Quantity FROM Seller s join Product p On s.sellerid = p.sellerId join Order_Item oi ON p.productId = oi.product_id join Orders o ON oi.order_id = o.id\r\n"
				+ "Group By s.sellername Order By Quantity DESC";
		TypedQuery<Object[]> query = enma.createQuery(jpql,Object[].class);
		
		return query.setMaxResults(10).getResultList();
	}
	
	@Override
	public List<Object[]> findTop10StoreByTime(String start_day, String end_day)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s.sellername as Name, Sum(oi.quantity) as Quantity FROM Seller s join Product p On s.sellerid = p.sellerId join Order_Item oi ON p.productId = oi.product_id join Orders o ON oi.order_id = o.id\r\n"
				+ "where o.created >= :start_day AND o.created <= :end_day Group By s.sellername Order By Quantity DESC";
		TypedQuery<Object[]> query = enma.createQuery(jpql,Object[].class);
		query.setParameter("start_day", start_day);
		query.setParameter("end_day", end_day);
		return query.setMaxResults(10).getResultList();
	}
}
