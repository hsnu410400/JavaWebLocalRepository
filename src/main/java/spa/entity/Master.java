package spa.entity;

// 按摩師傅
public class Master {
	private Integer id; // 師傅編號
	private String name; // 師傅姓名
	
	public Master() {
		
	}
	
	public Master(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Master [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
