package form.com.leo_form.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controler01")
public class controler01 {
    @GetMapping
 public String cntrl01(){
    return "ifwlkjhsf";
 }
}
