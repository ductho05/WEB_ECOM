package iostart.Services;

import java.util.List;

import iostart.Entyti.Orders;
import iostart.Entyti.Product;

public interface IProductServices {
	
	int count();

	Product findById(int productid);

	List<Product> findAll(int page, int pagesize);

	void delete(int productid);

	void update(Product product);

	void insert(Product product);
	
	List<Product> findByStatus(int page, int pagesize, Boolean status);
	
	int countByStatus(Boolean status);
	
	void updateStatus(int productId, Boolean status);
	
	List<Object[]> StatisPbyC();
	
	List<Object[]> StatisPbyAmount();
	
	int SumAmount();
	
	List<Object[]> findTop10Product();
	
	List<Product> findBySellerId(int sellerid, int page, int sizepage);
	
	int countBySellerid(int sellerid);

	int countBySellerFilter(int sellerid, Boolean status);

	List<Product> findBySellerFilter(int sellerid, Boolean status, int page, int sizepage);
	
	int countByCategoryId(int cid);

	List<Product> findByCategoryId(int cid, int page, int sizepage);
	
	List<Product> findByName(String name, int page, int sizepage);
	
	int countByName(String name);
	
}
