package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.ISlidesDAO;
import iostart.DAO.Impl.SlidesImpl;
import iostart.Entyti.Slides;
import iostart.Services.ISlidesServices;

public class SlidesServicesImpl implements ISlidesServices{

	ISlidesDAO slideDAO = new SlidesImpl();
	@Override
	public List<Slides> findAll() {
		
		return slideDAO.findAll();
	}

}
