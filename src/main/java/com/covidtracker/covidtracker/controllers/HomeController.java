package com.covidtracker.covidtracker.controllers;

import com.covidtracker.covidtracker.models.LocationStats;
import com.covidtracker.covidtracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CoronaVirusDataService coronaVirusDataService;

    @Autowired
    public HomeController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalGlobalCases", coronaVirusDataService.getTotalGlobalCases());
        model.addAttribute("newGlobalCases", coronaVirusDataService.getNewGlobalCases());
        return "home";
    }
}
