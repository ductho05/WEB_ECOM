package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.IShipperDAO;
import iostart.DAO.Impl.ShipperImpl;
import iostart.Entyti.Shipper;
import iostart.Services.IShipperServices;

public class ShipperServicesImpl implements IShipperServices{

	IShipperDAO shipperDAO = new ShipperImpl();
	@Override
	public Shipper findById(int id) {
		
		return shipperDAO.findById(id);
	}

	@Override
	public int count() {
		
		return shipperDAO.count();
	}

	@Override
	public List<Shipper> findAll(int page, int sizepage) {
		
		return shipperDAO.findAll(page, sizepage);
	}

	@Override
	public void delete(int id) {
		
		shipperDAO.delete(id);
	}

	@Override
	public void update(Shipper shipper) {
		
		shipperDAO.update(shipper);
	}

	@Override
	public void insert(Shipper shipper) {
		
		shipperDAO.insert(shipper);
	}
	
}
