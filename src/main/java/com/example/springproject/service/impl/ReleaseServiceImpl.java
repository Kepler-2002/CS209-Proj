package com.example.springproject.service.impl;


import com.example.springproject.entity.Connection3;
import com.example.springproject.entity.Release;
import com.example.springproject.mapper.ReleaseMapper;
import com.example.springproject.service.ReleaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReleaseServiceImpl implements ReleaseService {

  @Autowired
  private ReleaseMapper releaseMapper;

  @Override
  public void insertReleases(List<Release> releases) {
    releaseMapper.insertReleases(releases);
  }

  @Override
  public int getReleases() {
    return releaseMapper.getReleases();
  }

  @Override
  public List<Connection3> getCommitsBeforeReleases() {
    return releaseMapper.getCommitsBeforeReleases();
  }

}
