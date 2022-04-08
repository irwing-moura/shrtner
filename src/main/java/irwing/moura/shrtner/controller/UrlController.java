package irwing.moura.shrtner.controller;

import irwing.moura.shrtner.request.UrlRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class UrlController {


    @GetMapping("/index")
    public String index(Model model) {

        if (!model.containsAttribute("urlRequest")) {
            model.addAttribute("urlRequest", new UrlRequest());
        }

        model.addAttribute("loading", false);

        return "index";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute ("urlRequest") UrlRequest urlRequest, RedirectAttributes redirectAttributes) throws IOException {

        redirectAttributes.addFlashAttribute("url", urlRequest.getUrl());
        redirectAttributes.addFlashAttribute("loading", true);
        return "redirect:index";

    }



}
