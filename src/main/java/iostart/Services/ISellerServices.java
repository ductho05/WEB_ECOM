package iostart.Services;

import java.util.List;

import iostart.Entyti.Seller;

public interface ISellerServices {
	
	List<Seller> findAll(int page, int pagesize);
	
	int count();
	
	int countByActive(Boolean isactive);

	List<Seller> findAllByActive(int page, int pagesize, Boolean isactive);
	
	List<Object[]> findAllJoinUser(int sellerid);
	
	void update(Seller seller);
	
	Seller finById(int sellerid);
	
	List<Object[]> findTop10StoreByTime(String start_day, String end_day);

	List<Object[]> findTop10Store();
	
	void insert(Seller seller);
	
	List<Object[]> findAllByUser();

	List<Seller> findByUserid(int userid, int page, int sizepage);
	
	List<Seller> findAll();
}
