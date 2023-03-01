package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userDaoJPAImpl")
    private UserDao userDao;

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userDao.listUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDao.showUser(id));
        return "user";
    }

//    @GetMapping("/add")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "add";
//    }
    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "add";

        userDao.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDao.showUser(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "update";
        userDao.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        userDao.removeUser(id);
        return "redirect:/";
    }

}
