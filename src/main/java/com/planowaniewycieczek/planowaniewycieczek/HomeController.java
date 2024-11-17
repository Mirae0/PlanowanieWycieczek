package com.planowaniewycieczek.planowaniewycieczek;

import com.planowaniewycieczek.planowaniewycieczek.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/loginP")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("Uname", "Jan");
        System.out.println(user.getName());
        return "/hello.html";
    }

//    @PostMapping("/new-user")
//    public String newUserLogin(@ModelAttribute User user, Model model) {
//        //model.addAttribute("name", user);
//        //model.addAttribute("id",1);
//        System.out.println(user);
//        return "/hello";
//    }


}
