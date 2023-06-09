package websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/websocketTest")
public class WebSocketServerTest {
	
	// 用來存已連接的客戶端 Socket Session 資訊
	private static CopyOnWriteArrayList<Session> sessions;
	
	static {
		sessions = new CopyOnWriteArrayList<>();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Client 已連線 ");
		sessions.add(session);
		System.out.println("目前連線數量: " + sessions.size());
		System.out.println("目前連線 session id: " + session.getId());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.printf("ID: %s message: %s\n", session.getId(), message);
		Gson gson = new Gson();
		MessageInfo messageInfo = gson.fromJson(message, MessageInfo.class);

		// 將當前連線數量設定為 count 屬性的值
		int count = sessions.size();
		messageInfo.setCount(count);

		// 將 Java 物件轉換為 JSON 字串
		String json = gson.toJson(messageInfo);

		// 將包含 count 屬性的 JSON 字串發送給所有客戶端
		sessions.forEach(s -> {
		    if(s.isOpen()) {
		        s.getAsyncRemote().sendText(json);
		    }
		});
	}
	
	@OnClose
	public void onClose(Session session) {
		// 將 client 連線從 sessions 當中移除
		System.out.printf("ID: %s 離開了\n", session.getId());
		sessions.remove(session);
		System.out.println("目前連線數量: " + sessions.size());
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.printf("ID: %s, 有錯誤發生: %s\n", session.getId(), throwable.getMessage());
	}
	
}
