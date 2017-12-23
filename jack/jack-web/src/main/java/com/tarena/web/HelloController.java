package com.tarena.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.tarena.entity.User;
@Controller
@RequestMapping("/demo")
public class HelloController {
    
    //使用request接收参数
    @RequestMapping("/test1.do")
    public ModelAndView test1(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println(userName);
        System.out.println(password);
        return new ModelAndView("jsp/hello");
    }
    
    //使用方法参数接收参数
    @RequestMapping("/test2.do")
    public ModelAndView test2(String userName, 
            @RequestParam("password") String pwd) {
        System.out.println(userName);
        System.out.println(pwd);
        return new ModelAndView("jsp/hello");
    }
    
    //使用对象接收参数
    @RequestMapping("/test3.do")
    public ModelAndView test3(User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        return new ModelAndView("jsp/hello");
    }
    
    //使用ModelAndView传出数据
    @RequestMapping("/test4.do")
    public ModelAndView test4() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("success", true);
        data.put("message", "操作成功");
        return new ModelAndView("jsp/hello", data);
    }
    
    //使用ModelMap传出数据
    @RequestMapping("/test5.do")
    public ModelAndView test5(ModelMap model) {
        model.addAttribute("success", false);
        model.addAttribute("message", "操作失败");
        return new ModelAndView("jsp/hello");
    }
    
    
    //使用@ModelAttribute传出bean属性
    @ModelAttribute("age")
    public int getAge() {
        return 25;
    }
    
    //使用@ModelAttribute传出参数值
    @RequestMapping("/test6.do")
    public ModelAndView test6(
            @ModelAttribute("userName") String userName,
            String password) {
        return new ModelAndView("jsp/hello");
    }
    
    //使用session
    @RequestMapping("/test7.do")
    public ModelAndView test7(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("salary", 6000.0);
        return new ModelAndView("jsp/hello");
    }
    
    //返回String
    @RequestMapping("/test8.do")
    public String test8(User user, ModelMap model) {
        model.addAttribute("user", user);
        return "jsp/hello";
    }
    //系统错误页面
    @RequestMapping("/test9.do")
    public String test9() {
        return "jsp/error";
    }
    
    //使用RedirectView重定向
    @RequestMapping("/test10.do")
    public ModelAndView test10(User user) {
        if(user.getUserName().equals("tarena")) {
            return new ModelAndView("jsp/hello");
        } else {
            return new ModelAndView(new RedirectView("test9.do"));
        }
    }
    
    //使用redirect重定向
    @RequestMapping("/test11.do")
    public String test11(User user) {
        if(user.getUserName().equals("tarena")) {
            return "jsp/hello";
        } else {
            return "redirect:test9.do";
        }
    }    
    
}
