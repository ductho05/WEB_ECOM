package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import iostart.Config.JpaConfig;
import iostart.DAO.IStaffDAO;
import iostart.Entyti.Category;
import iostart.Entyti.Staff;

public class StaffImpl implements IStaffDAO{
	
	@Override
	public List<Staff> findBYSellerid(int sellerid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select s from Staff s where s.seller_id = :sellerid";
		TypedQuery<Staff> query = enma.createQuery(jpql,Staff.class);
		query.setParameter("sellerid", sellerid);
		return query.getResultList();
	}
	@Override
	public void insert(Staff staff)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(staff);
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
	public void update(Staff staff)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(staff);
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
	public void delete(int id) throws Exception{

		EntityManager enma = JpaConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

		trans.begin();

		Staff staff = enma.find(Staff.class,id);

		if(staff != null) {

		enma.remove(staff);

		}else {

		throw new Exception("Không tìm thấy");

		}
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
	public Staff findById(int id)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		Staff staff = enma.find(Staff.class, id);
		return staff;
	}
}
