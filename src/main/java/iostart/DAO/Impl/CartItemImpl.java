package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import iostart.Config.JpaConfig;
import iostart.DAO.ICartItemDAO;
import iostart.Entyti.Cart;
import iostart.Entyti.CartItem;
import iostart.Entyti.Product;

public class CartItemImpl implements ICartItemDAO{
	
	@Override
	public List<Object[]> findByUser(int userid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		CriteriaBuilder builder = enma.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<CartItem> rootCartItem = criteriaQuery.from(CartItem.class);
		Root<Cart> rootCart = criteriaQuery.from(Cart.class);
		Root<Product> rootproduct = criteriaQuery.from(Product.class);
		
		criteriaQuery.multiselect(rootCartItem, rootCart, rootproduct);
		
		rootCartItem.join("cart");
		rootCartItem.join("product");
		
		criteriaQuery.where(builder.equal(rootCartItem.get("cart").get("userid"), userid));
		
		TypedQuery<Object[]> query = enma.createQuery(criteriaQuery);
		List<Object[]> list = query.getResultList();
		return list;
	}
	
	@Override
	public void insert(CartItem cartitem)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(cartitem);
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
	public void update(CartItem cartitem)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(cartitem);
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
			CartItem cartitem = enma.find(CartItem.class, id);
			enma.remove(cartitem);
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
	public int countByUser(int userid)
	{
		int count;
		try {
			EntityManager enma = JpaConfig.getEntityManager();
			
			String jpql = "SELECT count(ci) FROM CartItem ci join Cart c ON ci.cartid = c.id WHERE c.userid = :userid";
			Query query = enma.createQuery(jpql);
			query.setParameter("userid", userid);			
			count =  ((Long)query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}
		return count;
	}
	@Override
	public CartItem findById(int id)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		CartItem cartItem = enma.find(CartItem.class, id);
		
		return cartItem;
	}
}
