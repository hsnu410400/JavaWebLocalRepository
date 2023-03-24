package ajax.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import ajax.entity.Employee;

public class EmployeeDao {
	
	private static List<Employee> employees = new CopyOnWriteArrayList<>();
	private static EmployeeDao _instance = new EmployeeDao();
	
	static {
		employees.add(new Employee(1, "John", 80000));
		employees.add(new Employee(2, "Mary", 90000));
		employees.add(new Employee(3, "Bob", 120000));
	}
	
	private EmployeeDao() {
		
	}
	
	public static EmployeeDao getInstance() {
		return _instance;
	}
	
	public List<Employee> getAllEmployees() {
		return employees;
	}
	
	public int getEmployeeNextId() {
		return employees.size() == 0 ? 1 : employees.get(employees.size()-1).getId() + 1;
	}
	
	public Employee getEmployeeById(Integer id) {
		Optional<Employee> employeeOpt = employees.stream()
							.filter(emp -> emp.getId().equals(id))
							.findFirst();
		return employeeOpt.isPresent() ? employeeOpt.get() : null;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void updateEmployee(Integer id, Employee employee) {
		// 根據 id 來查找 index
		int index = employees.indexOf(getEmployeeById(id));
		if(index != -1) {
			employees.set(index, employee);
		}
	}
	
	public void deleteEmployee(Integer id) {
		// 根據 id 來查找 index
		int index = employees.indexOf(getEmployeeById(id));
		if(index != -1) {
			employees.remove(index);
		}
	}
	
}