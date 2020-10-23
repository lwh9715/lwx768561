package com.example.mapper;

import com.example.bean.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceMapper {
    /**
     * 从数据库中查询所有资源
     * @return
     */
    List<Resource> selectAllResource();
}
