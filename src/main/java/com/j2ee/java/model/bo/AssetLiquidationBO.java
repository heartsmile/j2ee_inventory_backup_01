package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.AssetLiquidation;

public interface AssetLiquidationBO {

	public AssetLiquidation getByID(int id);
	
	public List<AssetLiquidation> getAllAssetLiquidation();
	
	public boolean insertAssetLiquidation(AssetLiquidation assetLiquidation);
	
	public boolean updateAssetLiquidation(AssetLiquidation assetLiquidation);
	
	public boolean deleteAssetLiquidation(AssetLiquidation assetLiquidation);
}
