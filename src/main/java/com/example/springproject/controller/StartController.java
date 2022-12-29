package com.example.springproject.controller;

import com.example.springproject.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/basic")
public class StartController {

  @Autowired
  private StartService startService;

  @RequestMapping("/truncate")
  public String truncateTable(@RequestParam String tableName) {
    startService.truncateTable(tableName);
    return "Table " + tableName + " truncated";
  }

}
