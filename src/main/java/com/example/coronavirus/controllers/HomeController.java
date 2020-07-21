package com.example.coronavirus.controllers;

import com.example.coronavirus.models.LocationStats;
import com.example.coronavirus.services.CoronaVirusDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  CoronaVirusDataService coronaVirusDataService;


  @GetMapping("/")
  public String home(Model model){

    List<LocationStats> allStats = coronaVirusDataService.getAllStats();
    int toalReportdCases = allStats.stream().mapToInt(stat -> stat.getLatestTotal()).sum();
    int toalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
    model.addAttribute("locationStats",allStats);
    model.addAttribute("toalReportdCases",toalReportdCases);
    model.addAttribute("toalNewCases",toalNewCases);
    return "home";
  }

}
