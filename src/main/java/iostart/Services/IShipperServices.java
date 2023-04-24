package iostart.Services;

import java.util.List;

import iostart.Entyti.Shipper;

public interface IShipperServices {

	Shipper findById(int id);

	int count();

	List<Shipper> findAll(int page, int sizepage);

	void delete(int id);

	void update(Shipper shipper);

	void insert(Shipper shipper);
}
