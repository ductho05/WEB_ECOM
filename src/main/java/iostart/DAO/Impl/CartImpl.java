package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import iostart.Config.JpaConfig;
import iostart.DAO.ICartDAO;
import iostart.Entyti.Cart;

public class CartImpl implements ICartDAO{
	
	@Override
	public List<Cart>findAllByUser(int userid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c WHERE userid = :userid";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
		query.setParameter("userid", userid);
		
		return query.getResultList();
	}
	@Override
	public void insert(Cart cart)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.persist(cart);
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
	public void update(Cart cart)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(cart);
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
	public void delete(int id)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			Cart cart = enma.find(Cart.class, id);
			enma.remove(cart);
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
	public Cart findByUser(int userid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c From Cart c Where c.userid = :userid";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
		query.setParameter("userid", userid);
		Cart cart = query.getSingleResult();
		return cart;
	}
}
