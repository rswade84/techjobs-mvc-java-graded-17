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

/*
        NOTE1 : Currently has one method (search)
                      Renders the search form template

*/

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    //Task 3
    // TODO #3 - Create a handler to process a search request and render the updated search view.

    /* NOTES: Model is a parameter used to pass data from the controller to the viewer.
                     model.addAttribute() = adds data that the view (search.html) can access and display

                      @RequestParam String searchType:
                            - @RequestParam binds the value of a request parameter to the method parameter called -----> searchType which represents the type of search (e.g., "employer", "location", "skill") that the user selects in the form.
                            - The value for searchType will be provided by the name attribute of a corresponding form field in search.html.


                     @RequestParam(required = false) String searchTerm:
                            - This binds the value of the search term to the method parameter called -----> searchTerm.
                            - required = false means that this parameter is optional. If the user leaves the search field empty or the parameter is not provided in the request, it won't cause an error, and searchTerm will be null.
                            - This is important because if the search term is empty, the method logic should display all jobs (by calling JobData.findAll()).
    */

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm) {
        ArrayList<Job> jobs;
        if(searchTerm == null || searchTerm.equals("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            // Changed to use findByColumnAndValue for specific searches
            if (searchType.equals("all")) {
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "Jobs with: " + searchTerm);
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            }
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
    }