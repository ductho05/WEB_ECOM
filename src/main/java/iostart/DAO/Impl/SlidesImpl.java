package iostart.DAO.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import iostart.Config.JpaConfig;
import iostart.DAO.ISlidesDAO;
import iostart.Entyti.Slides;

public class SlidesImpl implements ISlidesDAO {
	
	@Override
	public List<Slides> findAll()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Slides> query = enma.createNamedQuery("Slides.findAll", Slides.class);
		
		return query.getResultList();
	}
}
