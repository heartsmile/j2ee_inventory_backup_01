package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.AssetLiquidation;
@Component(value="AssetLiquidationDAOImpl")
public class AssetLiquidationDAOImpl implements AssetLiquidationDAO {

	static Logger logger = Logger.getLogger(AssetLiquidationDAOImpl.class.getName());
	@Override
	public AssetLiquidation getByID(int id) {
		// TODO Auto-generated method stub
		return (AssetLiquidation) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(AssetLiquidation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetLiquidation> getAllAssetLiquidation() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from AssetLiquidation").list();
	}

	@Override
	public boolean insertAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(assetLiquidation);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save AssetLiquidation");
		}
		return result;
	}

	@Override
	public boolean updateAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(assetLiquidation);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update AssetLiquidation");
		}
		return result;
	}

	@Override
	public boolean deleteAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(assetLiquidation);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete AssetLiquidation");
		}
		return result;
	}

}
