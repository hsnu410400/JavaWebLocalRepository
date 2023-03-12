package spa.entity;

// 預約訂單
public class Order {
	// 欄位屬性
	private Integer id; // 訂單編號
	private String userName; // 客戶名稱(登入帳號)
	private Integer spaId; // 按摩服務編號
	private Integer spaTime ; // 按摩服務時間
	private Integer spaPrice ; // 按摩服務價格
	private Integer masterId; // 師傅編號
	private String reserve; // 預約日期
	
	// 組合
	private Spa spa;
	private Master master;
	private Member member;
	
	// 組合 getter/setter
	public Spa getSpa() {
		return spa;
	}
	public void setSpa(Spa spa) {
		this.spa = spa;
	}
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSpaId() {
		return spaId;
	}
	public void setSpaId(Integer spaId) {
		this.spaId = spaId;
	}
	public Integer getSpaTime() {
		return spaTime;
	}
	public void setSpaTime(Integer spaTime) {
		this.spaTime = spaTime;
	}
	public Integer getSpaPrice() {
		return spaPrice;
	}
	public void setSpaPrice(Integer spaPrice) {
		this.spaPrice = spaPrice;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userName=" + userName + ", spaId=" + spaId + ", spaTime=" + spaTime
				+ ", spaPrice=" + spaPrice + ", masterId=" + masterId + ", reserve=" + reserve + "]";
	}
	
	
	
	
}
