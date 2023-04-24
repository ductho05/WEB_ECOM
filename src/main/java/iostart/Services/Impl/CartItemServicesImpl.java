package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.ICartItemDAO;
import iostart.DAO.Impl.CartItemImpl;
import iostart.Entyti.CartItem;
import iostart.Services.ICartItemServices;

public class CartItemServicesImpl implements ICartItemServices{

	ICartItemDAO cartitemDAO = new CartItemImpl();
	@Override
	public List<Object[]> findByUser(int userid) {
		
		return cartitemDAO.findByUser(userid);
	}
	@Override
	public int countByUser(int userid) {
		
		return cartitemDAO.countByUser(userid);
	}
	@Override
	public void delete(int id) {
		
		cartitemDAO.delete(id);
	}
	@Override
	public void update(CartItem cartitem) {
		cartitemDAO.update(cartitem);;
	}
	@Override
	public void insert(CartItem cartitem) {
		
		cartitemDAO.insert(cartitem);
	}
	@Override
	public CartItem findById(int id) {
		
		return cartitemDAO.findById(id);
	}

}
