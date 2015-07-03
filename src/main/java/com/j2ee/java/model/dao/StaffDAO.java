package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Staff;

public interface StaffDAO {

	public Staff getByID(int id);
	
	public List<Staff> getAllStaff();
	
	public boolean insertStaff(Staff Staff);
	
	public boolean updateStaff(Staff Staff);
	
	public boolean deleteStaff(Staff Staff);
}
