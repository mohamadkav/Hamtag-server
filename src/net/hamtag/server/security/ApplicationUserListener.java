package net.hamtag.server.security;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.session.SessionDestroyedEvent;

	public class ApplicationUserListener implements ApplicationListener<ApplicationEvent> ,HttpSessionAttributeListener{
		
		public ApplicationUserListener(){
		}

		@Override
		public void onApplicationEvent(ApplicationEvent arg0) {
			if (arg0 instanceof AuthenticationSuccessEvent)
		    {
		    }
			if (arg0 instanceof AbstractAuthenticationFailureEvent)
		    {
//				ActivityLog log = new ActivityLog("login_fail").register(null);
//				log.success(null);
		    }
			if (arg0 instanceof SessionDestroyedEvent){
//				ActivityLog log = new ActivityLog("logout").register(null);
//				log.success(null);
			}
			
		}

		@Override
		public void attributeAdded(HttpSessionBindingEvent arg0) {
			if(arg0.getName().equals("SPRING_SECURITY_CONTEXT")){
			}
			
		}

		@Override
		public void attributeRemoved(HttpSessionBindingEvent arg0) {
			if(arg0.getName().equals("SPRING_SECURITY_CONTEXT")){
			}
			
		}

		@Override
		public void attributeReplaced(HttpSessionBindingEvent arg0) {
			// TODO Auto-generated method stub
		}
		
	}

