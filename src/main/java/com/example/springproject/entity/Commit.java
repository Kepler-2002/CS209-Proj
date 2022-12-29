package com.example.springproject.entity;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;


/**
 * Javadoc
 */
@Data
public class Commit {

  private String sha;
  private Date commit_date;
  private String developer;
  private Timestamp commit_time;
}
