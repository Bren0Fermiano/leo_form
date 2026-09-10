package form.com.leo_form.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class controler01 {
   @GetMapping ("/employees")
 public String cntrl01(){
    return "employees.html";
 }
}
