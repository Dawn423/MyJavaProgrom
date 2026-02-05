package com.example.ipptest.service;

import com.example.ipptest.domain.IppResourceFieldMapper;
import com.example.ipptest.mapper.IppResourceFieldMapperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IppResourceFieldMapperService {

    @Autowired
    private IppResourceFieldMapperMapper mapper;

    /**
     * 按“连续自增 id”规则新增：id = max(id) + 1（若表为空则从 1 开始）。
     * 注意：这会强制保证 id 连续，但会带来并发/外键风险（仅适合测试或明确无外键引用场景）。
     */
    @Transactional
    public int insertContinuous(IppResourceFieldMapper record) {
        Long maxId = mapper.selectMaxId();
        long nextId = (maxId == null ? 1L : maxId + 1L);
        record.setId(nextId);
        return mapper.insert(record);
    }

    /**
     * 删除并“下方顺延”：物理删除指定 id，并将所有 id > deletedId 的记录 id 依次 -1，
     * 同时重置 AUTO_INCREMENT，保证后续新增 id 仍连续。
     */
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        int deleted = mapper.deleteByPrimaryKey(id);
        if (deleted <= 0) {
            return deleted;
        }
        mapper.decrementIdsGreaterThan(id);
        Long maxId = mapper.selectMaxId();
        long nextId = (maxId == null ? 1L : maxId + 1L);
        mapper.resetAutoIncrement(nextId);
        return deleted;
    }

    public int insert(IppResourceFieldMapper record) {
        return mapper.insert(record);
    }

    public int insertSelective(IppResourceFieldMapper record) {
        return mapper.insertSelective(record);
    }

    public IppResourceFieldMapper selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<IppResourceFieldMapper> selectAll() {
        return mapper.selectAll();
    }

    public List<IppResourceFieldMapper> selectByField(Integer field) {
        return mapper.selectByField(field);
    }

    public List<IppResourceFieldMapper> selectByOriginalStr(String originalStr) {
        return mapper.selectByOriginalStr(originalStr);
    }

    public List<IppResourceFieldMapper> selectByStandardStr(String standardStr) {
        return mapper.selectByStandardStr(standardStr);
    }

    /**
     * 根据 originalStr + standardStr + field 删除，并保持 id 连续。
     * 默认只删匹配到的第一条（去重后一般也只会有一条）。
     */
    @Transactional
    public int deleteByKey(String originalStr, String standardStr, Integer field) {
        IppResourceFieldMapper record = mapper.selectByKey(originalStr, standardStr, field);
        if (record == null || record.getId() == null) {
            return 0;
        }
        return deleteByPrimaryKey(record.getId());
    }

    public int updateByPrimaryKeySelective(IppResourceFieldMapper record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(IppResourceFieldMapper record) {
        return mapper.updateByPrimaryKey(record);
    }
}
