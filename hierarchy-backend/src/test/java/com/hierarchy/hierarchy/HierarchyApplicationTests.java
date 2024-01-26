package com.hierarchy.hierarchy;

import com.hierarchy.hierarchy.exception.EmployeeAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.hierarchy.hierarchy.entity.Employee;
import com.hierarchy.hierarchy.repository.EmployeeRepository;
import com.hierarchy.hierarchy.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class HierarchyApplicationTests {
	@Mock
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(employeeRepository.existsByName(anyString())).thenReturn(true);
	}

	@Test
	public void testGetAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

		List<Employee> result = employeeService.getAllEmployees();

		assertEquals(employeeList, result, "List should match");
	}

	@Test
	public void testCreateEmployee_Success() {
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
		BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

		EmployeeService employeeService = new EmployeeService(employeeRepository, passwordEncoder);

		Employee employee = new Employee();
		employee.setName("John");
		employee.setPassword("1q2w3e4r,");

		Mockito.when(employeeRepository.existsByName(Mockito.eq("John"))).thenReturn(false);
		Mockito.when(passwordEncoder.encode(Mockito.eq("1q2w3e4r,"))).thenReturn("encodedPassword");
		Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenAnswer(invocation -> {
			Employee savedEmployee = invocation.getArgument(0);
			return savedEmployee;
		});

		Employee result = employeeService.createEmployee(employee);

		Mockito.verify(employeeRepository, Mockito.times(1)).existsByName(Mockito.eq("John"));
		Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(Employee.class));

		assertNotNull(result, "Result should not be null");
		assertEquals("encodedPassword", result.getPassword());
	}

	@Test
	public void testCreateEmployee_AlreadyExists() {
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
		BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

		EmployeeService employeeService = new EmployeeService(employeeRepository, passwordEncoder);

		Employee employee = new Employee();
		employee.setName("John");
		employee.setPassword("password");

		Mockito.when(employeeRepository.existsByName(Mockito.eq("John"))).thenReturn(true);

		assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.createEmployee(employee));
	}
}

