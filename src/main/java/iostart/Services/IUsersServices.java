package iostart.Services;

import java.util.List;

import org.apache.catalina.User;

import iostart.Entyti.Users;

public interface IUsersServices {
	int count();

	Users findById(int userid);

	List<Users> findAll(int page, int sizepage);

	List<Users> findAll();

	void update(Users user);

	void delete(int userid);

	void insert(Users user);
	
	Users findByUsernamePassRole(String username, String password);
	
	void insertUsert(String username, String email, String fullname, String password);
	
	Users findByUsernameEmail(String username, String email);
	
	void updatePassword(String email, String password);

	Users findByEmail(String email);
}
