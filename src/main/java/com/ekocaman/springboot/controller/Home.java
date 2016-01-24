package com.ekocaman.springboot.controller;

import com.ekocaman.springboot.service.GitkitService;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Home {
    private static final Logger logger = LoggerFactory.getLogger(Home.class);

    private final GitkitService gitkitService;

    @Autowired
    public Home(GitkitService gitkitService) {
        this.gitkitService = gitkitService;
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) throws GitkitClientException {
        Map<String, Object> params = new HashMap<>();

        GitkitUser user = gitkitService.validateTokenInRequest(request);
        if (user != null) {
            logger.info("Logged User : {}", user);
            params.put("user", user);
        }

        return new ModelAndView("index", params);
    }

    @RequestMapping("/gitkit")
    public String gitkit() {
        return "gitkit-widget";
    }
}
