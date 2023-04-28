package com.hamitmizrak.controller.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityMvc {

    // HOME
    // http://localhost:2222
    // http://localhost:2222/home
    @GetMapping("/home")
    public String homePage(){
        return "index";
    }

    // LOGOUT
    // http://localhost:2222/logout44
    @GetMapping("/logout44")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model){
        //sayfaya giriş yapmış userlar
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            model.addAttribute("admin_logout","Sistemden çıkış yapıldı");
        }else{
            model.addAttribute("admin_logout","Sistemden çıkış yapılmadı !!!!");
        }
        return "/logout";
    }
} //end class
