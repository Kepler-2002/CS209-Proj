package com.example.springproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StartMapper {

  void truncateTables(@Param("tableName") String tableName);
}
