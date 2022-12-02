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
}
