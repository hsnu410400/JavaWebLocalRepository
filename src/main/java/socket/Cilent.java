package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
	
	public static void main(String[] args) throws Exception {
		String hostName = "localhost";
		int port = 5555;
		
		// Client 連到 Server 端
		Socket socket = new Socket(hostName, port);
		System.out.println("Client 連到 Server 端成功");
		
		// 建立 Client-Server 的 I/O 通訊
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		Random random = new Random();
		while (true) {
			int number = random.nextInt(10000); 
			// 傳送給 server 端
			out.println(number + "");
			out.flush();
			//System.out.println(number + "");
			if(number == 9999) {
				break;
			}
			Thread.sleep((long)(Math.random() * 700 + 200)); // 0.2~0.9 秒發送一次
		}
		
		socket.close();
		System.out.println("Client 關閉");
	}
	
}