package com.example.springproject.mapper;

import com.example.springproject.entity.Release;
import com.example.springproject.entity.Connection3;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReleaseMapper {

  void insertReleases(List<Release> releases);

  int getReleases();

  List<Connection3> getCommitsBeforeReleases();
}
