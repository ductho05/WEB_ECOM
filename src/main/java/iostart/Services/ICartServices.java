package iostart.Services;

import java.util.List;

import iostart.Entyti.Cart;

public interface ICartServices {
	
	List<Cart> findAllByUser(int userid);
	
	void delete(int id);

	void update(Cart cart);

	void insert(Cart cart);
	
	Cart findByUser(int userid);
}
