package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 *  wepass static ref of HashMap, constructor initialize those 2 hash,
 *   Display job listings, Filter jobs by category
 *   Show all jobs option, Manage column choices for display
 *-
 */

// Class Declaration and Annotations
@Controller
@RequestMapping(value = "list") // base URL for all methods in this controller
public class ListController {

    // HashMaps
    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Object> tableChoices = new HashMap<>();

    // Constructor, .put() method
    public ListController() {
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("positionType", "Position Type");
        columnChoices.put("coreCompetency", "Skill");

        tableChoices.put("employer", JobData.getAllEmployers());
        tableChoices.put("location", JobData.getAllLocations());
        tableChoices.put("positionType", JobData.getAllPositionTypes());
        tableChoices.put("coreCompetency", JobData.getAllCoreCompetency());
    }

    // Instance Methods - model.add()
    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("tableChoices", tableChoices);
        model.addAttribute("employers", JobData.getAllEmployers());
        model.addAttribute("locations", JobData.getAllLocations());
        model.addAttribute("positions", JobData.getAllPositionTypes());
        model.addAttribute("skills", JobData.getAllCoreCompetency());

        return "list";
    }

    // RequestParam - binds web request to method parameters

    @GetMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
                                           @RequestParam String column,
                                           @RequestParam(required = false) String value) {
        ArrayList<Job> jobs;

        if (column.equals("all")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            // Filter jobs by selected column and value
            jobs = JobData.findByColumnAndValue(column, value);
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }

        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}