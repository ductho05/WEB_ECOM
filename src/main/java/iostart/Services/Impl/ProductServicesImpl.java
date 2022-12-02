package iostart.Services.Impl;

import java.util.List;

import iostart.DAO.IProductDAO;
import iostart.DAO.Impl.ProductImpl;
import iostart.Entyti.Product;
import iostart.Services.IProductServices;

public class ProductServicesImpl implements IProductServices{

	IProductDAO productDAO = new ProductImpl();
	@Override
	public int count() {
		
		return productDAO.count();
	}

	@Override
	public Product findById(int productid) {
		
		return productDAO.findById(productid);
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		
		return productDAO.findAll(page, pagesize);
	}

	@Override
	public void delete(int productid) {
		
		productDAO.delete(productid);
	}

	@Override
	public void update(Product product) {
		
		productDAO.update(product);
	}

	@Override
	public void insert(Product product) {
		
		productDAO.insert(product);
	}

	@Override
	public List<Product> findByStatus(int page, int pagesize, Boolean status) {
		
		return productDAO.findByStatus(page, pagesize, status);
	}

	@Override
	public int countByStatus(Boolean status) {
		
		return productDAO.countByStatus(status);
	}

	@Override
	public void updateStatus(int productId, Boolean status) {
		
		productDAO.updateStatus(productId, status);
	}

}
