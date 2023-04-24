package iostart.Services;

import java.util.List;

import iostart.Entyti.CartItem;

public interface ICartItemServices {

	List<Object[]> findByUser(int userid);
	
	int countByUser(int userid);

	void delete(int id);

	void update(CartItem cartitem);

	void insert(CartItem cartitem);
	
	CartItem findById(int id);

}
