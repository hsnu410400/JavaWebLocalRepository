package ajax.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ajax.entity.Employee;

public class EmployeeDao {
	
	private static List<Employee> employees = new CopyOnWriteArrayList<>();
	private static EmployeeDao _instance = new EmployeeDao();
	
	private EntityManagerFactory entityManagerFactory;
	
	private EmployeeDao() {
		// 建立工廠管理器實體 (實務上要配置在 ServletContextListsner 中)
		entityManagerFactory = Persistence.createEntityManagerFactory("demo");
	}
	
	public static EmployeeDao getInstance() {
		return _instance;
	}
	
	public List<Employee> getAllEmployees() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("Select employee From Employee employee"); // PQL
		List<Employee> employees = query.getResultList(); // 將查詢結果傳換成 List
		entityManager.close(); // 關閉 entityManager
		return employees;
	}
	
	public Employee getEmployeeById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Employee employee = entityManager.find(Employee.class, id);
		entityManager.close();
		return employee;
	}
	
	public void addEmployee(Employee employee) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// 建立交易
		EntityTransaction etx = entityManager.getTransaction();
		etx.begin(); // 開始
		// 新增
		entityManager.persist(employee);
		//etx.rollback(); // 回滾(若有錯誤發生)
		etx.commit(); // 提交
		entityManager.close();
	}
	
	public void updateEmployee(Integer id, Employee employee) {
		Employee existingEmployee = getEmployeeById(id);
		if(existingEmployee == null) {
			return;
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// 建立交易
		EntityTransaction etx = entityManager.getTransaction();
		etx.begin(); // 開始
		// 修改
		entityManager.merge(employee);
		etx.commit(); // 提交
		entityManager.close();
	}
	
	public void deleteEmployee(Integer id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// 建立交易
		EntityTransaction etx = entityManager.getTransaction();
		etx.begin(); // 開始
		// 注意在進行刪除的時候要把查找也放到 etx 環境中避免斷開連接的實體
		Employee existingEmployee = getEmployeeById(id);
		if(existingEmployee == null) {
			return;
		}
		// 移除
		Employee managedEmployee = entityManager.merge(existingEmployee);
		entityManager.remove(managedEmployee);
		
		etx.commit(); // 提交
		
		entityManager.close();
	}

	@Override
	protected void finalize() throws Throwable {
		 // 關閉工廠 (實務上要配置在 ServletContextListsner 中)
		if(entityManagerFactory != null) { 
			entityManagerFactory.close();
		}
	}
	
	
}