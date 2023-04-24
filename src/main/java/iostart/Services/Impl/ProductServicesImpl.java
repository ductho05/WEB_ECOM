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

	@Override
	public List<Object[]> StatisPbyC() {
		
		return productDAO.StatisPbyC();
	}

	@Override
	public List<Object[]> StatisPbyAmount() {
		
		return productDAO.StatisPbyAmount();
	}

	@Override
	public int SumAmount() {
		
		return productDAO.SumAmount();
	}

	@Override
	public List<Object[]> findTop10Product() {
		
		return productDAO.findTop10Product();
	}

	@Override
	public List<Product> findBySellerId(int sellerid, int page, int sizepage) {
		
		return productDAO.findBySellerId(sellerid, page, sizepage);
	}

	@Override
	public int countBySellerid(int sellerid) {
		
		return productDAO.countBySellerid(sellerid);
	}

	@Override
	public int countBySellerFilter(int sellerid, Boolean status) {
		
		return productDAO.countBySellerFilter(sellerid, status);
	}

	@Override
	public List<Product> findBySellerFilter(int sellerid, Boolean status, int page, int sizepage) {
		
		return productDAO.findBySellerFilter(sellerid, status, page, sizepage);
	}

	@Override
	public int countByCategoryId(int cid) {
		
		return productDAO.countByCategoryId(cid);
	}

	@Override
	public List<Product> findByCategoryId(int cid, int page, int sizepage) {
		
		return productDAO.findByCategoryId(cid, page, sizepage);
	}

	@Override
	public List<Product> findByName(String name, int page, int sizepage) {
		return productDAO.findByName(name, page, sizepage);
	}

	@Override
	public int countByName(String name) {
		
		return productDAO.countByName(name);
	}
}
