package com.example.springproject.service.impl;

import com.example.springproject.mapper.StartMapper;
import com.example.springproject.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartServiceImpl implements StartService {

  @Autowired
  private StartMapper startMapper;

  @Override
  public void truncateTable(String tableName) {
    if (tableName.startsWith("`"))
      startMapper.truncateTables(tableName.substring(1,tableName.length()-1));
    else
      startMapper.truncateTables(tableName);
  }
}
