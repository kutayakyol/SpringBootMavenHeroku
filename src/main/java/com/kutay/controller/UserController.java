package com.kutay.controller;

import com.kutay.entities.User;
import com.kutay.service.UserService;
import com.kutay.util.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * Created by Kutay on 28.10.2016.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model m) {

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
    public String create(@ModelAttribute(value = "User") @Valid User user,BindingResult bindingResult,HttpSession session,Model model) {
        User u = new User();

        try {
            System.out.println("userId" + user.getId().toString());
            System.out.println("user_ad : " + user.getUserName().toString());
        } catch (Exception e) {
            System.out.println("addUser create.... userName yok");
        }

        try {
            Random rnd = new Random();
            int sayi = rnd.nextInt(999999999);

            java.math.BigDecimal kullaniciid = new java.math.BigDecimal(String.valueOf(sayi));

            u.setId(kullaniciid);
            u.setUserName(user.getUserName());
            u.setCreateDate(new Date());
            u.setNote(null);

            userService.create(u);
            session.setAttribute("statumessage", "S");
        } catch (Exception e) {
            session.setAttribute("statumessage", e.toString());
        }

        return "redirect:getAll";
    }

    @RequestMapping(value = "createUser", params = "cancel", method = RequestMethod.POST)
    public String createUserCancel(@ModelAttribute(value = "User") @Valid User user,BindingResult bindingResult,HttpSession session,Model model) {

        return "redirect:getAll";
    }

    @RequestMapping(value = "getNote", method = RequestMethod.GET)
    public String getUserNote(@RequestParam(value = "id") BigDecimal id, Model m, ModelMap modelMap, HttpSession httpsession) {

        modelMap.put("note", userService.getNote(id));

        return "userNote";
    }

    @RequestMapping(value = "/editNote", method = RequestMethod.GET)
    public String editNote(@RequestParam(value = "id") BigDecimal id, Model m, ModelMap modelMap, HttpSession httpsession) {

        User u = new User();

        u=userService.getNote(id);

        m.addAttribute("user", u);
        modelMap.put("User", u);

        return "editNote";
    }

    @RequestMapping(value = "updateNote", params = "save", method = RequestMethod.POST)
    public String updateNote(@ModelAttribute(value = "User") @Valid User user,BindingResult bindingResult,HttpSession session,Model model) {
        User u = new User();

        u=userService.getNote(user.getId());

        try {
            System.out.println("userId" + user.getId().toString());
            System.out.println("user_ad : " + user.getUserName().toString());
        } catch (Exception e) {
            System.out.println("addUser update.... userName yok");
        }

        try {

            u.setNote(user.getNote());


            userService.editNote(u);
            session.setAttribute("statumessage", "S");
        } catch (Exception e) {
            session.setAttribute("statumessage", e.toString());
        }

        return "redirect:getNote?id="+user.getId();
    }

    @RequestMapping(value = "updateNote", params = "cancel", method = RequestMethod.POST)
    public String updateNoteCancel(@ModelAttribute(value = "User") @Valid User user,BindingResult bindingResult,HttpSession session,Model model) {

        return "redirect:getNote?id="+user.getId();
    }
}
