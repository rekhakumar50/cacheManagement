package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dao.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDepartmentCode(String departmentCode);
	
	@Modifying
	@Query("delete from Department d where d.departmentCode=:departmentCode")
	void deleteByDepartmentCode(String departmentCode);

}
