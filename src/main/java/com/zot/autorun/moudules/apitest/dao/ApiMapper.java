package com.zot.autorun.moudules.apitest.dao;

import com.zot.autorun.moudules.apitest.entity.ApiData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiMapper {

    ApiData selectApiById(int id);

    ApiData selectApiByName(String apiName);
}
