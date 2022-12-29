package com.example.springproject.mapper;

import com.example.springproject.entity.Commit;
import com.example.springproject.entity.Connection1;
import com.example.springproject.entity.Connection2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommitMapper {

  void insertCommits(List<Commit> Commits);

  List<Connection1> getCommitNumByTime();

  List<Connection2> getCommitNumByDeveloper();
}
