package spa.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import spa.entity.Member;

@WebListener
public class SpaSessionAttrListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		try {
			Member m = (Member)event.getSession().getAttribute("member");
			if(m.getUsername().equals("C03")) {
				System.out.println("有可疑分子進入 ...");
				event.getSession().invalidate();
			}
			
		} catch(Exception e) {
			
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
	
}
