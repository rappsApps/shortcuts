package com.example.demo;

import com.example.demo.dao.UrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UrlController {
    @Autowired
    private UrlDao urlDao;
    private Url url;

    @RequestMapping(value="/Url", method = RequestMethod.GET)
    public String helloForm(){
        return "helloform";
    }

    @RequestMapping(value = "/Url", method = RequestMethod.POST)
    public String hello(HttpServletRequest request, Model model){
        String long_url = request.getParameter("long_url");

        if (long_url == null || long_url == ""){
            long_url = "somethingWrong";
        }
        Url url = new Url(long_url);
        urlDao.save(url);
        model.addAttribute("long_url", long_url);
        return "hello";
    }
    @RequestMapping(value = "/url")
    public String url(Model model){
        List<Url> urls = urlDao.findAll();
        model.addAttribute("urls", urls);
        System.out.println("Something!");
        return "url";
    }
}
