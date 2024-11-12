package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller // Marks this class as a Spring MVC Controller (for web request and views)
@RequestMapping("search") // All paths in this controller start with /search

// SearchController handles
// - 1. Display search form
// - 2. Process search requests
// - 3. Show search results
// - 4. Handle different search types

public class SearchController {

    @GetMapping(value = "")
    // Model carries data to view
    // Returns search.html with empty form
    public String search(Model model) {
        // Add column choices to model for search form display
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    // Model carries data to view
    // RequestParam gets form data
    // Returns search.html with results
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam(required = false) String searchTerm) {
        ArrayList<Job> jobs;

        // Handle empty, null, or "all" search terms
        if(searchTerm == null || searchTerm.equals("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            // Handle specific search types
            if (searchType.equals("all")) {
                // Search across all fields
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "Jobs with: " + searchTerm);
            } else {
                // Search in specific column
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("title", "Jobs with " +
                        columnChoices.get(searchType) + ": " + searchTerm);
            }
        }

        // Add results and columns to model for display
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        // This returns search.html from above
        return "search";
    }
}