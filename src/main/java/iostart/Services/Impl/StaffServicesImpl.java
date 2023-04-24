package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.IStaffDAO;
import iostart.DAO.Impl.StaffImpl;
import iostart.Entyti.Staff;
import iostart.Services.IStaffServices;

public class StaffServicesImpl implements IStaffServices{
	
	IStaffDAO staffDAO = new StaffImpl();

	@Override
	public List<Staff> findBYSellerid(int sellerid) {
		
		return staffDAO.findBYSellerid(sellerid);
	}

	@Override
	public Staff findById(int id) {
		
		return staffDAO.findById(id);
	}

	@Override
	public void delete(int id)throws Exception {
		
		staffDAO.delete(id);
	}

	@Override
	public void update(Staff staff) {
		
		staffDAO.update(staff);
	}

	@Override
	public void insert(Staff staff) {
		
		staffDAO.insert(staff);
	}

}
