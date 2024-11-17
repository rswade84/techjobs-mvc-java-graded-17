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
 * Display search form, Process search requests,
 * Shows search results, Handle different search types
 */

// Class Declaration & Annotations
@Controller
@RequestMapping("search")
public class SearchController {

    // Handler Methods - Add to view with model.add()
    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam(required = false) String searchTerm) {

        ArrayList<Job> jobs;

        // Search Type: empty
        if(searchTerm == null || searchTerm.equals("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            if (searchType.equals("all")) { // Search Type: all
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "Jobs with: " + searchTerm);
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm); // Search Type: specific term
                model.addAttribute("title", "Jobs with " +
                        columnChoices.get(searchType) + ": " + searchTerm);
            }
        }

        // Add results and columns to model for display
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}