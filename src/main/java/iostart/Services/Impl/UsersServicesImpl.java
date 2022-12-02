package iostart.Services.Impl;

import java.util.List;

import org.apache.catalina.User;

import iostart.DAO.IUsersDAO;
import iostart.DAO.Impl.UsersImpl;
import iostart.Entyti.Users;
import iostart.Services.IUsersServices;

public class UsersServicesImpl implements IUsersServices{

	IUsersDAO userDAO = new UsersImpl();
	@Override
	public int count() {
		
		return userDAO.count();
	}

	@Override
	public Users findById(int userid) {
		
		return userDAO.findById(userid);
	}

	@Override
	public List<Users> findAll(int page, int sizepage) {
		
		return userDAO.findAll(page, sizepage);
	}

	@Override
	public List<Users> findAll() {
		
		return userDAO.findAll();
	}

	@Override
	public void update(Users user) {
		
		userDAO.update(user);
	}

	@Override
	public void delete(int userid) {
		
		userDAO.delete(userid);
	}

	@Override
	public void insert(Users user) {
		
		userDAO.insert(user);
	}

	@Override
	public Users findByUsernamePassRole(String username, String password) {
		
		return userDAO.findByUsernamePassRole(username, password);
	}

	@Override
	public void insertUsert(String username, String email, String fullname, String password) {
		
		userDAO.insertUsert(username, email, fullname, password);
	}

	@Override
	public Users findByUsernameEmail(String username, String email) {
		
		return userDAO.findByUsernameEmail(username, email);
	}

	@Override
	public void updatePassword(String email, String password) {
		
		userDAO.updatePassword(email, password);
	}

	@Override
	public Users findByEmail(String email) {
		
		return userDAO.findByEmail(email);
	}

}
