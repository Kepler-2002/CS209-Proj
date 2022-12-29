package com.example.springproject.service;

import com.example.springproject.entity.Commit;
import com.example.springproject.entity.Connection1;
import com.example.springproject.entity.Connection2;

import java.util.List;

public interface CommitService {

  void insertCommits(List<Commit> Commits);

  List<Connection1> getCommitNumByTime();

  List<Connection2> getCommitNumByDeveloper();
}
