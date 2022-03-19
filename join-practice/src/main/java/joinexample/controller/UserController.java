package joinexample.controller;

import joinexample.dto.AccountForm;
import joinexample.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @GetMapping("/loginUser")
    public String createUserForm(Model model) {
        model.addAttribute("userForm", new AccountForm());
        return "user/login/register";
    }

    @PostMapping("/loginUser")
    public String createUser(@Validated AccountForm accountForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/login/register";
        }

        accountService.createUser(accountForm);

        return "redirect:/";
    }
}
