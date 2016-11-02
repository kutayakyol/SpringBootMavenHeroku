package com.kutay.controller;

import com.kutay.entities.User;
import com.kutay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Kutay on 28.10.2016.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model m) {

        return "home";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllRolList(Model m) {

        m.addAttribute("listUser", userService.getAll());

        return "userList";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addRol(Model m, ModelMap modelMap) {

        User u = new User();

            m.addAttribute("user", u);
            modelMap.put("User", u);

        return "userAdd";
    }

    @RequestMapping(value = "createUser", params = "save", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "User") User user, ModelMap modelMap, HttpSession session, BindingResult result) {
        User u = new User();

        try {
            System.out.println("userId" + user.getId().toString());
            System.out.println("user_ad : " + user.getUserName().toString());
        } catch (Exception e) {
            System.out.println("addUser create.... userName yok");
        }

        try {
            u.setId(user.getId());
            u.setUserName(user.getUserName());
            u.setCreateDate(new Date());
            u.setNote(null);

            userService.create(u);
            session.setAttribute("statumessage", "S");
        } catch (Exception e) {
            session.setAttribute("statumessage", e.toString());
        }

        return "redirect:userList.html";
    }

    @RequestMapping(value = "createUser", params = "cancel", method = RequestMethod.POST)
    public String createUserCancel(@ModelAttribute(value = "User") User user, ModelMap modelMap, HttpSession session, BindingResult result) {

        return "redirect:userList.html";
    }

    @RequestMapping(value = "getNote", method = RequestMethod.GET)
    public String getUserNote(@RequestParam(value = "id") BigDecimal id, Model m, ModelMap modelMap, HttpSession httpsession) {

        modelMap.put("note", userService.getNote(id));

        return "userNote";
    }

    @RequestMapping(value = "getNote", params = "cancel", method = RequestMethod.POST)
    public String backUserList( ModelMap modelMap, HttpSession session, BindingResult result) {

        return "redirect:userList.html";
    }
}
