package core.basesyntax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {
    @GetMapping
    public RedirectView redirectDefaultMapping() {
        return new RedirectView("/index");
    }

    @GetMapping("/index")
    public String index() {
        return "/index";
    }
}
