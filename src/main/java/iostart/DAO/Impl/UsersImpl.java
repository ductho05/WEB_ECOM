package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.catalina.User;

import iostart.Config.JpaConfig;
import iostart.DAO.IUsersDAO;
import iostart.Entyti.Users;

public class UsersImpl implements IUsersDAO {

	@Override
	public void insert(Users user) {
		
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			
			trans.begin();
			enma.persist(user);
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
	public void delete(int userid) {
		
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			
			trans.begin();
			Users user = enma.find(Users.class,userid);
			if (user != null)
			{
				enma.remove(user);
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
	public void update(Users user) {
		
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			
			trans.begin();
			enma.merge(user);
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
	public List<Users> findAll() {
		
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Users> query = enma.createNamedQuery("Users.findAll", Users.class);
		return query.getResultList();
	}
	
	@Override
	public List<Users> findAll(int page, int sizepage) {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Users> query = enma.createNamedQuery("Users.findAll", Users.class);
		query.setFirstResult(page*sizepage);
		query.setMaxResults(sizepage);
		List<Users> list = query.getResultList();
		return list;
	}
	
	@Override
	public Users findById(int userid) {
		EntityManager enma = JpaConfig.getEntityManager();
		
		Users user = enma.find(Users.class, userid);
		return user;
	}
	
	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(u) FROM Users u";
		
		Query query = enma.createQuery(jpql);
		
		return ((Long)query.getSingleResult()).intValue();
	}
	@Override
	public Users findByUsernamePassRole(String username, String password ) {
		EntityManager enma = JpaConfig.getEntityManager();
		Users user = new Users();
		try {
			String jpql = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password";
			TypedQuery<Users> query = enma.createQuery(jpql,Users.class);
			query.setParameter("username",username);
			query.setParameter("password", password);
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	@Override
	public void insertUsert(String username, String email, String fullname, String password ) {
		EntityManager enma = JpaConfig.getEntityManager();
		
		String jpql = "INSERT INTO Users u(u.username, u.email, u.fullname, u.password) VALUES(:username,:email,:fullname,:password)";
		Query query = enma.createNativeQuery(jpql);
		query.setParameter("username",username);
		query.setParameter("email",email);
		query.setParameter("fullname",fullname);
		query.setParameter("password", password);
		
		query.executeUpdate();
	}
	
	@Override
	public Users findByUsernameEmail(String username, String email ) {
		EntityManager enma = JpaConfig.getEntityManager();
		Users user = new Users();
		try
		{
			String jpql = "SELECT u FROM Users u WHERE u.username = :username OR u.email = :email";
			TypedQuery<Users> query = enma.createQuery(jpql,Users.class);
			query.setParameter("username",username);
			query.setParameter("email", email);
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		return user;
	}
	@Override
	public Users findByEmail(String email ) {
		EntityManager enma = JpaConfig.getEntityManager();
		Users user = new Users();
		try
		{
			String jpql = "SELECT u FROM Users u WHERE  u.email = :email";
			TypedQuery<Users> query = enma.createQuery(jpql,Users.class);
			query.setParameter("email", email);
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	@Override
	public void updatePassword(String email, String password)
	{
			EntityManager enma = JpaConfig.getEntityManager();
			String jpql = "UPDATE Users u SET u.password = :password WHERE u.email = :email";
			Query query = enma.createNativeQuery(jpql);
			query.setParameter("password",password);
			query.setParameter("email",email);
			
			query.executeUpdate();
	}
}
