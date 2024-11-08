package org.poseidon.trading.controller;

import jakarta.validation.Valid;

import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;
import org.poseidon.trading.repositories.UserRepository;
import org.poseidon.trading.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(final UserRepository userRepository, final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));

            userService.add(user);

            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        UserModel userModel = userService.findById(id);

        User user = new User();
        user.setId(id);
        user.setPassword("");
        user.setUsername(userModel.username());
        user.setFullname(userModel.fullname());
        user.setRole(userModel.role());

        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        userService.update(user);

        model.addAttribute("user", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);

        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
