package com.j2ee.java.model.bo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dao.AssetLiquidationDAO;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dto.AssetLiquidation;
@Component(value="AssetLiquidationBOImpl")
public class AssetLiquidationBOImpl implements AssetLiquidationBO {

	private static final Logger logger = LoggerFactory.getLogger(AssetLiquidationBOImpl.class);
	@Autowired
	@Qualifier("AssetLiquidationDAOImpl")
	private AssetLiquidationDAO assetLiquidationDAO;
	
	@Override
	public AssetLiquidation getByID(int id) {
		// TODO Auto-generated method stub
		AssetLiquidation AssetLiquidation = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			AssetLiquidation = assetLiquidationDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return AssetLiquidation;
	}

	@Override
	public List<AssetLiquidation> getAllAssetLiquidation() {
		// TODO Auto-generated method stub
		List<AssetLiquidation> listAssetLiquidation = new ArrayList<AssetLiquidation>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listAssetLiquidation = assetLiquidationDAO.getAllAssetLiquidation();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listAssetLiquidation;
	}

	@Override
	public boolean insertAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = assetLiquidationDAO.insertAssetLiquidation(assetLiquidation);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean updateAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = assetLiquidationDAO.updateAssetLiquidation(assetLiquidation);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean deleteAssetLiquidation(AssetLiquidation assetLiquidation) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = assetLiquidationDAO.deleteAssetLiquidation(assetLiquidation);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

}
