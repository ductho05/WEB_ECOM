package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import iostart.Config.JpaConfig;
import iostart.DAO.IProductDAO;
import iostart.Entyti.Product;

public class ProductImpl implements IProductDAO{

	@Override
	public void insert(Product product)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.persist(product);
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
	public void update(Product product)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.merge(product);
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
	public void delete(int productid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			
			Product product = enma.find(Product.class, productid);
			
			if (product != null)
			{
				enma.remove(product);
			}
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
	public List<Product> findAll(int page, int pagesize)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll",Product.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		
		return query.getResultList();
	}
	
	@Override
	public Product findById(int productid) {
		EntityManager enma = JpaConfig.getEntityManager();
		
		Product product = enma.find(Product.class, productid);
		
		return product;
	}
	
	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p";
		Query query = enma.createQuery(jpql);
		
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public List<Product> findByStatus(int page, int pagesize, Boolean status)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "SELECT p FROM Product p WHERE p.status = :status";
		
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter("status", status);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		
		return query.getResultList();
	}
	@Override
	public int countByStatus(Boolean status) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p WHERE p.status = :status";
		Query query = enma.createQuery(jpql);
		query.setParameter("status",status);
		return ((Long)query.getSingleResult()).intValue();
	}
	@Override
	public void updateStatus(int productId, Boolean status)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "UPDATE Product p SET p.status = :status WHERE p.productId = :productId";
		Query query = enma.createQuery(jpql);
		query.setParameter("status",status).executeUpdate();
		query.setParameter("productId",productId).executeUpdate();
	}
	
	@Override
	public List<Object[]> StatisPbyC()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "SELECT c.categoryname as name, count(p.productId) as count FROM Category c join Product p ON c.categoryId = p.categoryId Group by c.categoryname";
		
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		
		return query.getResultList();
	}
	@Override
	public List<Object[]> StatisPbyAmount()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "SELECT c.categoryname as name, count(p.productId) as count FROM Category c join Product p ON c.categoryId = p.categoryId Where p.amount != 0 Group by c.categoryname";
		
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		
		return query.getResultList();
	}
	
	@Override
	public int SumAmount()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT Sum(p.amount) FROM Product p";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public List<Object[]> findTop10Product()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p.productName, Sum(p.amount) as Amount FROM Product p Group by p.productName Order by Amount DESC";
		TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
		
		return query.setMaxResults(10).getResultList();
	}
	
	@Override
	public List<Product> findBySellerId(int sellerid, int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.sellerId = :sellerid";
		TypedQuery<Product> query = enma.createQuery(jpql,Product.class);
		query.setParameter("sellerid", sellerid);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		return query.getResultList();
	}
	
	@Override
	public int countBySellerid(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p WHERE p.sellerId = :sellerid";
		Query query = enma.createQuery(jpql);
		query.setParameter("sellerid", sellerid);
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public List<Product> findBySellerFilter(int sellerid, Boolean status, int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.sellerId = :sellerid AND p.status = :status";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter("sellerid", sellerid);
		query.setParameter("status", status);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		return query.getResultList();
	}
	
	@Override
	public int countBySellerFilter(int sellerid, Boolean status)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p WHERE p.sellerId = :sellerid AND p.status = :status";
		Query query = enma.createQuery(jpql);
		query.setParameter("sellerid", sellerid);
		query.setParameter("status",status);
		return ((Long)query.getSingleResult()).intValue();
	}
	@Override
	public List<Product> findByCategoryId(int cid, int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.categoryId = :cid AND p.status = true";
		TypedQuery<Product> query = enma.createQuery(jpql,Product.class);
		query.setParameter("cid", cid);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		
		return query.getResultList();
	}
	@Override
	public int countByCategoryId(int cid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p WHERE p.categoryId = :cid AND p.status = true";
		Query query = enma.createQuery(jpql);
		query.setParameter("cid", cid);
		return ((Long)query.getSingleResult()).intValue();		
	}
	
	@Override
	public List<Product> findByName(String name, int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.productName LIKE :name";
		TypedQuery<Product> query = enma.createQuery(jpql,Product.class);
		query.setParameter("name", "%"+name+"%");
		query.setFirstResult(page*sizepage);
		return query.getResultList();
	}
	@Override
	public int countByName(String name)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p WHERE p.productName LIKE :name";
		Query query = enma.createQuery(jpql);
		query.setParameter("name", "%"+name+"%");
		
		return ((Long)query.getSingleResult()).intValue();
	}
}
