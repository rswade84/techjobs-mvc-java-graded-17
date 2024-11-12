package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller  // Marks this class as a Spring MVC Controller,(for web request and views)

    // HomeController handles
            // - 1. Main entry point.
            // - 2. Setup Navigation Options
            // - 3. Pass navigation to view

public class HomeController {
    @GetMapping(value = "/")

    public String index(Model model) {

        // This creates a HashMap for navigation options (search & list)
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search"); // What is this doing?
        actionChoices.put("list", "List"); // What is this doing?

        // Pass navigation to viewer
        model.addAttribute("actions", actionChoices); // What is this doing?

        return "index";
    }

}

