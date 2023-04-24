package iostart.Services;

import java.util.List;

import iostart.Entyti.Staff;

public interface IStaffServices {

	List<Staff> findBYSellerid(int sellerid);
	
	Staff findById(int id);

	void delete(int id)throws Exception;

	void update(Staff staff);

	void insert(Staff staff);
}
