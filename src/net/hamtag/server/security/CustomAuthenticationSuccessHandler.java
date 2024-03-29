package net.hamtag.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import net.hamtag.server.datatypes.device.DeviceMgr;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  
	  
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
                                        HttpServletResponse httpServletResponse,  
                                        Authentication authentication)  
            throws IOException, ServletException {  
        //do some logic here if you want something to be done whenever  
        //the user successfully logs in.  
  
        HttpSession session = httpServletRequest.getSession();  
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        session.setAttribute("username", authUser.getUsername());  
        session.setAttribute("authorities", authentication.getAuthorities());  
        //set our response to OK status  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
  
        //since we have created our custom success handler, its up to us to where  
        //we will redirect the user after successfully login  
        if(DeviceMgr.getDeviceByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName())!=null){
        	httpServletResponse.sendRedirect("webapp/index.html");
        }
        else
        	httpServletResponse.sendRedirect("admin/index.html");  
    }  
}  