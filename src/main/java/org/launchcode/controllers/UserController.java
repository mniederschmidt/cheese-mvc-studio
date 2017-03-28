package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid User newUser,
                      Errors errors, Model model,
                      @RequestParam String verify){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add User");
            return "user/add";
        }

        if (!(verify.equals(newUser.getPassword()))) {
            model.addAttribute("title", "Add User");
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }

        model.addAttribute("title", "List Users");
        model.addAttribute(newUser);
        UserData.add(newUser);
        return "user/index";
    }
}