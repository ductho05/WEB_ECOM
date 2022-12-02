package iostart.Services;

import java.util.List;

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

}
