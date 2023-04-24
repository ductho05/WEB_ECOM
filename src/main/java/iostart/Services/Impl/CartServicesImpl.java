package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.ICartDAO;
import iostart.DAO.Impl.CartImpl;
import iostart.Entyti.Cart;
import iostart.Services.ICartServices;

public class CartServicesImpl implements ICartServices {

	ICartDAO cartDAO = new CartImpl();
	@Override
	public List<Cart> findAllByUser(int userid) {
		
		return cartDAO.findAllByUser(userid);
	}
	@Override
	public void delete(int id) {
		cartDAO.delete(id);
		
	}
	@Override
	public void update(Cart cart) {
		
		cartDAO.update(cart);
	}
	@Override
	public void insert(Cart cart) {
		
		cartDAO.insert(cart);
	}
	@Override
	public Cart findByUser(int userid) {
		
		return cartDAO.findByUser(userid);
	}

}
