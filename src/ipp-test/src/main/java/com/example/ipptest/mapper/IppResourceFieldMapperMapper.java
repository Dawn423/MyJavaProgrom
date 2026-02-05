package com.example.ipptest.mapper;

import com.example.ipptest.domain.IppResourceFieldMapper;
import java.util.List;

public interface IppResourceFieldMapperMapper {
    int deleteByPrimaryKey(Long id);
    int insert(IppResourceFieldMapper record);
    int insertSelective(IppResourceFieldMapper record);
    IppResourceFieldMapper selectByPrimaryKey(Long id);
    Long selectMaxId();
    List<IppResourceFieldMapper> selectAll();
    List<IppResourceFieldMapper> selectByField(Integer field);
    List<IppResourceFieldMapper> selectByOriginalStr(String originalStr);
    List<IppResourceFieldMapper> selectByStandardStr(String standardStr);
    IppResourceFieldMapper selectByKey(String originalStr, String standardStr, Integer field);
    int decrementIdsGreaterThan(Long id);
    int resetAutoIncrement(Long nextId);
    int updateByPrimaryKeySelective(IppResourceFieldMapper record);
    int updateByPrimaryKey(IppResourceFieldMapper record);
}
