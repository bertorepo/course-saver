package com.fujitsu.ph.tsup.employee.management.web;

/*Iwarat, Jhon Harvey A*/
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fujitsu.ph.tsup.employee.management.model.EmployeeUpdateForm;

@Controller
@RequestMapping("/employee")
public class EmployeeUpdateController {
    private static Logger logger = LoggerFactory.getLogger(EmployeeUpdateController.class);

    @GetMapping("/Update")
    public String show(Model model) {
        logger.debug("Model:{}", model);

        if (model.containsAttribute("updating")) {
            return "employee-management/EmployeeUpdate";
        }

        EmployeeUpdateForm employeeUpdate = new EmployeeUpdateForm();
        employeeUpdate.setId(7);
        employeeUpdate.setNumber("220053693");
        employeeUpdate.setLastName("Iwarat");
        employeeUpdate.setFirstName("Harvey");
        employeeUpdate.setEmailAddress("j.iwarat@fujitsu.com");
        employeeUpdate.setUsername("j.iwarat");

        model.addAttribute("updating", employeeUpdate);
        return "employee-management/EmployeeUpdate";

    }

    @PostMapping("/Update")
    public String submit(@Valid @ModelAttribute("updating") EmployeeUpdateForm employeeUpdate, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {

        logger.debug("Employee:{}", employeeUpdate);
        logger.debug("Result:{}", result);

        model.addAttribute("employee", employeeUpdate);
        if (result.hasErrors()) {
            return "employee-management/EmployeeUpdate";
        }

        redirectAttributes.addFlashAttribute("updating", employeeUpdate);
        return "redirect:/employee/Update";

    }
}
