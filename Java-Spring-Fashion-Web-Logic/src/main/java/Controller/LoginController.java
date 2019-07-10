package Controller;

import Entity.StaffEntity;
import Service.StaffService;
import Utils.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "login/")
@SessionAttributes("staff")
public class LoginController {
    ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
    @Autowired
    StaffService staffService;

    @GetMapping
    public String Default() {
        return "login";
    }

    @PostMapping("createStaff/")
    public String Login(@RequestParam String emailSu, @RequestParam String passwordSu,@RequestParam String passwordRSu,ModelMap modelMap) {
        if (emailSu != null & passwordSu != null & passwordRSu != null & passwordSu.equals(passwordRSu)) {
            if (isValid(emailSu)) {
                if (staffService.creatStaff(emailSu, passwordSu) != null) {
                    modelMap.addAttribute("staff",staffService.creatStaff(emailSu, passwordSu));
                    return "index";
                }
            }
            modelMap.addAttribute(WebConstant.MESSAGE_RESPONSE,"nhap sai email!");
        }
        return "login";
    }

    @PostMapping("checkLogin/")
    public String createStaff(@RequestParam String email, @RequestParam String password,ModelMap modelMap) {
        if (email != null && password != null) {
            StaffEntity staffEntity =staffService.checkLogin(email, password);
            if (staffEntity != null) {
                modelMap.addAttribute("staff",staffEntity);
                return "index";
            }
            modelMap.addAttribute(WebConstant.MESSAGE_RESPONSE,"co loi ");

        }
        return "login";
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
