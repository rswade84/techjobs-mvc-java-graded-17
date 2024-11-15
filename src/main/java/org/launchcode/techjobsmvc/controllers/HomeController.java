package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;

/**
 * Created by LaunchCode
 * MEP, CreateNav, addNav to Viewer
 */

// Class Declaration and Annotations
@Controller
public class HomeController {
    @GetMapping(value = "/")

    // Creates HashMap to store navigation options
    public String index(Model model) {
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        // Adds the HashMap to the model to view
        model.addAttribute("actions", actionChoices);

        // Returns the name of the view template to render
        return "index";
    }
}

