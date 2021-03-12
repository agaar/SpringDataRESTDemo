package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRepository employeeRepository;
	
	@Autowired																					//	@Transactional provided by JpaRepository
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}  //delegate calls from the service to the employeeDAO

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);  //Optional instead of having checking for nulls
		
		Employee theEmployee = null;
		if(result.isPresent()) {
			return result.get();
		} else {
			throw new RuntimeException("Did not find employee id- " + theId);
		}
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}






