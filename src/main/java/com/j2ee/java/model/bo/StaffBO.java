package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.Staff;

public interface StaffBO {

	public Staff getByID(int id);
	
	public List<Staff> getAllStaff();
	
	public boolean insertStaff(Staff staff);
	
	public boolean updateStaff(Staff staff);
	
	public boolean deleteStaff(Staff staff);
}
