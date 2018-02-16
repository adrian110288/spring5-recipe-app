package guru.springframework.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    public String getIndexPage() {
        return "index";
    }
}
