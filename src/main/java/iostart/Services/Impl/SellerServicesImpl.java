package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.ISellerDAO;
import iostart.DAO.Impl.SellerImpl;
import iostart.Entyti.Seller;
import iostart.Services.ISellerServices;

public class SellerServicesImpl implements ISellerServices{

	ISellerDAO sellerDAO = new SellerImpl();
	@Override
	public List<Seller> findAll(int page, int pagesize) {
		
		return sellerDAO.findAll(page, pagesize);
	}
	@Override
	public int count() {
		
		return sellerDAO.count();
	}
	@Override
	public int countByActive(Boolean isactive) {
		
		return sellerDAO.countByActive(isactive);
	}
	@Override
	public List<Seller> findAllByActive(int page, int pagesize, Boolean isactive) {
		
		return sellerDAO.findAllByActive(page, pagesize, isactive);
	}
	@Override
	public List<Object[]> findAllJoinUser(int sellerid) {
		
		return sellerDAO.findAllJoinUser(sellerid);
	}
	@Override
	public void update(Seller seller) {
		
		sellerDAO.update(seller);
	}
	@Override
	public Seller finById(int sellerid) {
		
		return sellerDAO.finById(sellerid);
	}
	@Override
	public List<Object[]> findTop10StoreByTime(String start_day, String end_day) {
		
		return sellerDAO.findTop10StoreByTime(start_day, end_day);
	}
	@Override
	public List<Object[]> findTop10Store() {
		
		return sellerDAO.findTop10Store();
	}
	@Override
	public void insert(Seller seller) {
		sellerDAO.insert(seller);
	}
	@Override
	public List<Object[]> findAllByUser() {
		
		return sellerDAO.findAllByUser();
	}
	@Override
	public List<Seller> findByUserid(int userid,int page, int sizepage) {
		
		return sellerDAO.findByUserid(userid, page, sizepage);
	}
	@Override
	public List<Seller> findAll() {
		
		return sellerDAO.findAll();
	}

}
