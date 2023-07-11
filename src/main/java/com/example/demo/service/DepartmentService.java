package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.Department;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Cacheable(value = "departmentCache", key = "#departmentDto.departmentCode")
	public Department saveDepartment(DepartmentDto departmentDto) {
		Department department = null;
		try {
			department = departmentRepository.save(DepartmentMapper.convertToDepartment(departmentDto));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return department;
	}
	
	@Cacheable(value = "departmentCache")
	public List<DepartmentDto> getDepartments() {
		List<DepartmentDto> departmentDtos = new ArrayList<>();
		List<Department> departments = departmentRepository.findAll();
		
		if(CollectionUtils.isNotEmpty(departments)) {
			departmentDtos = departments.stream()
										.filter(Objects::nonNull)
										.map(depart -> DepartmentMapper.convertToDepartmentDto(depart))
										.filter(Objects::nonNull)
										.collect(Collectors.toList());
		}
		return departmentDtos;
	}
	
	@CachePut(value = "departmentCache", key = "#departmentCode")
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		DepartmentDto departmentDto = null;
		if(StringUtils.isNotEmpty(departmentCode)) {
			Department department = departmentRepository.findByDepartmentCode(departmentCode);
			departmentDto = DepartmentMapper.convertToDepartmentDto(department);
		}
		return departmentDto;
	}
	
	
	@CachePut(value = "departmentCache", key = "#departmentDto.departmentCode")
	public Department updateDepartment(DepartmentDto departmentDto) {
		Department updatedDepartment = null;
		try {
			Department department = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
			department.setDepartmentDesc(departmentDto.getDepartmentDesc());
			department.setDepartmentName(departmentDto.getDepartmentName());
			updatedDepartment = departmentRepository.save(department);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedDepartment;
	}
	
	@Transactional
	@CacheEvict(value = "departmentCache", key = "#departmentCode")
	public void deleteDepartment(String departmentCode) {
		try {
			departmentRepository.deleteByDepartmentCode(departmentCode);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
