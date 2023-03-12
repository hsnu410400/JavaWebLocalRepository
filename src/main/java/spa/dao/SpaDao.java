package spa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import spa.entity.Master;
import spa.entity.Member;
import spa.entity.Order;
import spa.entity.Spa;

public class SpaDao {
	// 資料庫
	private static List<Spa> spaList;
	private static List<Master> masterList;
	private static List<Order> orderList;
	private static List<Member> memberList;
	static {
		spaList = List.of(
				new Spa(1, "SPA", "透過調節水溫、水壓,結合香蘭精油 礦物質等,達到各種療效", 90, 1500),
				new Spa(2, "推拿", "在身體特定部位施行按捏、摩擦、震動、活動關節等動作,恢復或改善身體機能", 60, 1000),
				new Spa(3, "足底按摩", "對應身體的某一器官、部位的問題,調節下身體機能", 30, 600),
				new Spa(4, "指壓", "運用手指或手肘根據經絡及肌肉走向,按壓緩解身體的酸痛不適", 60, 1200),
				new Spa(5, "油壓", "手法較指壓溫和,將精油混合按摩油,人塗抹在需要按摩的部位", 60, 1380),
				new Spa(6, "泰式按摩", "通過壓足、壓腰、踩脊等方式協助伸展肢體,是各類按摩中最大動作的一種", 90, 1680)
		);
		masterList = List.of(
				new Master(101, "John"), new Master(102, "Mary"), new Master(103, "Bob")
		);
		orderList = new ArrayList<>();
		memberList = List.of(new Member("A01", "1234", 1), new Member("B02", "2222", 0), new Member("C03", "3333", 0));
	}
	
	public List<Member> queryAllMembers() {  // 查詢所有客戶(Member)資料
		return memberList;
	}
	
	public List<Spa> queryAllSpas() { // 查詢所有 SPA 服務項目
		return spaList;
	}
	
	public List<Master> queryAllMasters() { // 查詢所有按摩師傅
		return masterList;
	}
	
	public List<Order> queryOrders() { // 查詢所有訂單
		return orderList;
	}
	
	public List<Order> queryOrdersByMember(Member member) { // 查詢 Member 所有訂單
		String username = member.getUsername();
		return orderList.stream()  // 建立串流
						.filter(m -> m.getUserName().equals(username))  // 過濾
						.collect(Collectors.toList());  // collect 將得到的結果收集起來轉成 List 輸出
	}
	
	public List<Order> queryOrdersByUsername(String userName) { // 查詢 userName 的所有訂單
		return orderList.stream()
				.filter(order -> order.getUserName().equals(userName))
				.collect(Collectors.toList());
	}
	
	public Order getOrderById(Integer id) { // 根據 order id 查詢該筆訂單紀錄 
		Optional<Order> optOrder = orderList.stream()
				.filter(order -> order.getId().intValue() == id.intValue())
				.findFirst();
		// 組合配置注入
		Order order = null;
		if (optOrder.isPresent()) {
			order = optOrder.get();
			order.setSpa(getSpaById(order.getSpaId()));
			order.setMaster(getMasterById(order.getMasterId()));
			order.setMember(getMemberByUsername(order.getUserName()));
		}
		return order;		
	}
	
	public boolean updateOrderById(Integer id, Order newOrder) { // 根據 order id 來修改訂單資料
		Order order = getOrderById(id);
		if(order != null) {
			order = newOrder;
			return true;
		}
		return false;
	}
	
	public boolean deleteOrderById(Integer id) { // 根據 order id 來刪除訂單資料
		Order order = getOrderById(id);
		if(order != null) {
			orderList.remove(order);
			return true;
		}
		return false;
	}
	
	public Spa getSpaById(Integer id) {
		Optional<Spa> optSpa = spaList.stream()
				.filter(spa -> spa.getId().intValue() == id.intValue())
				.findFirst();
		return optSpa.isPresent() ? optSpa.get() : null;	
	}
	
	public Master getMasterById(Integer id) {
		Optional<Master> optMaster = masterList.stream()
				.filter(m -> m.getId().intValue() == id.intValue())
				.findFirst();
		return optMaster.isPresent() ? optMaster.get() : null;	
	}
	
	public Member getMemberByUsername(String username) {
		Optional<Member> optMember = memberList.stream()
				.filter(m -> m.getUsername().equals(username))
				.findFirst();
		return optMember.isPresent() ? optMember.get() : null;	
	}
	
}
