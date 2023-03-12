package spa.entity;

// 按摩資料
public class Spa {
	private Integer id; // 按摩服務編號
	private String name; // 按摩服務名稱
	private String content; // 按摩服務說明
	private Integer time; // 按摩服務時間(分)
	private Integer price; // 按摩服務價格
	
	public Spa() {
		
	}
	
	public Spa(Integer id, String name, String content, Integer time, Integer price) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.time = time;
		this.price = price;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Spa [id=" + id + ", name=" + name + ", content=" + content + ", time=" + time + ", price=" + price
				+ "]";
	}
	
	
	
}
