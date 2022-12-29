package com.example.springproject.service;

import com.example.springproject.entity.Release;
import com.example.springproject.entity.Connection3;

import java.util.List;

public interface ReleaseService {

  void insertReleases(List<Release> releases);

  int getReleases();

  List<Connection3> getCommitsBeforeReleases();
}
