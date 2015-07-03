package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.Provider;

public interface ProviderBO {

	public Provider getByID(int id);
	
	public List<Provider> getAllProvider();
	
	public boolean insertProvider(Provider Provider);
	
	public boolean updateProvider(Provider Provider);
	
	public boolean deleteProvider(Provider Provider);
}
