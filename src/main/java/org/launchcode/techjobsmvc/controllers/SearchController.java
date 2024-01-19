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
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    //Create a handler to process a search request and render the updated search view.
        //Use PostMapping because takes into account data provided

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs; //storing results in a jobs ArrayList
        if (searchTerm.equals("all")) {  //If the search term is "all" then call findAll() for the job data
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With All: ");
        }
        if (searchTerm.equals("")) {  //If the search term is blank then call findAll() for the job data
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With All: ");
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
        } //otherwise

        //model.addAttribute("type", searchType);
        model.addAttribute("jobs", jobs); //passing in jobs into the view
        model.addAttribute("columns", columnChoices); //pass the columnChoices into the view
        return "search";

    }

}

