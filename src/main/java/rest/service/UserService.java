package rest.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rest.entity.User;

// SingleTon (單例服務)
public class UserService {
	
	private static List<User> users = new CopyOnWriteArrayList<>();
	
	static {
		users.add(new User("John", 35000));
		users.add(new User("Mary", 45000));
		users.add(new User("Helen", 55000));
		users.add(new User("Jean", 75000));
		users.add(new User("Craig", 90000));
	}
	
	private static UserService _instance = new UserService();
	private UserService() {
		
	}
	public static UserService getUserServiceInstance() {
		return _instance;
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public void update(int index, User user) {
		users.set(index, user);
	}
	
	public void delete(int index) {
		users.remove(index);
	}
	
	public User get(int index) {
		return users.get(index);
	}
	
	public List<User> queryAll() {
		return users;
	}
	
}
