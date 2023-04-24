package iostart.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import iostart.Config.JpaConfig;
import iostart.DAO.IShipperDAO;
import iostart.Entyti.Shipper;

public class ShipperImpl implements IShipperDAO{

	@Override
	public void insert(Shipper shipper)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.persist(shipper);
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
	public void update(Shipper shipper)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			
			trans.begin();
			enma.merge(shipper);
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
			
			Shipper shipper = enma.find(Shipper.class, id);
			trans.begin();
			enma.remove(shipper);
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
	public List<Shipper> findAll(int page, int sizepage)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		List<Shipper> list = new ArrayList<Shipper>();
		try {
			TypedQuery<Shipper> query = enma.createNamedQuery("Shipper.findAll", Shipper.class);
			query.setFirstResult(sizepage*page);
			query.setMaxResults(sizepage);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}		
		return list;
	}
	
	@Override
	public int count()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		int count = 0;
		try {
			String jpql = "SELECT count(sp) FROM Shipper sp";
			Query query = enma.createQuery(jpql);
			count = ((Long)query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}
		return count;
	}
	
	@Override
	public Shipper findById(int id)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		Shipper shipper = new Shipper();
		try {
			
			shipper = enma.find(Shipper.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
			shipper = null;
		}
		
		return shipper;
	}
}
