package com.example.demo.mapper;

import java.util.Objects;

import com.example.demo.dao.Department;
import com.example.demo.dto.DepartmentDto;

public interface DepartmentMapper {
	
	public static DepartmentDto convertToDepartmentDto(Department department) {
		DepartmentDto departmentDto = null;
		if(Objects.nonNull(department)) {
			departmentDto = new DepartmentDto();
			departmentDto.setId(department.getId());
			departmentDto.setDepartmentName(department.getDepartmentName());
			departmentDto.setDepartmentDesc(department.getDepartmentDesc());
			departmentDto.setDepartmentCode(department.getDepartmentCode());
		}
		
		return departmentDto;
	}
	
	public static Department convertToDepartment(DepartmentDto departmentDto) {
		Department department = null;
		if(Objects.nonNull(departmentDto)) {
			department = new Department();
			department.setId(departmentDto.getId());
			department.setDepartmentName(departmentDto.getDepartmentName());
			department.setDepartmentDesc(departmentDto.getDepartmentDesc());
			department.setDepartmentCode(departmentDto.getDepartmentCode());
		}
		
		return department;
	}

}
