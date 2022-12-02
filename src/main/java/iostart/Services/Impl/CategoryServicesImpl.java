package iostart.Services.Impl;

import java.util.List;
import iostart.DAO.ICategoryDAO;
import iostart.DAO.Impl.CategoryImpl;
import iostart.Entyti.Category;
import iostart.Services.ICategoryServices;

public class CategoryServicesImpl implements ICategoryServices{
	
	ICategoryDAO categoryDAO = new CategoryImpl(); 

	@Override
	public int count() {
		
		return categoryDAO.count();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		
		return categoryDAO.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll(int page,int pagesize) {
		
		return categoryDAO.findAll(page,pagesize);
	}

	@Override
	public List<Category> findAll() {
		
		return categoryDAO.findAll();
	}

	@Override
	public Category findById(int categoryId) {
		
		return categoryDAO.findById(categoryId);
	}

	@Override
	public void delete(int cateid) throws Exception {
		
		categoryDAO.delete(cateid);
	}

	@Override
	public void update(Category category) {
		
		categoryDAO.update(category);
	}

	@Override
	public void insert(Category category) {
		
		categoryDAO.insert(category);
	}
	
	
}
