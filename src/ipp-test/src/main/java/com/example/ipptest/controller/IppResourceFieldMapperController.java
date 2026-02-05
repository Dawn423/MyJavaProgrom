package com.example.ipptest.controller;

import com.example.ipptest.domain.IppResourceFieldMapper;
import com.example.ipptest.service.IppResourceFieldMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ipp/resource-field-mapper")
public class IppResourceFieldMapperController {

    @Autowired
    private IppResourceFieldMapperService service;

    @GetMapping("/{id}")
    public IppResourceFieldMapper selectByPrimaryKey(@PathVariable("id") Long id) {
        return service.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<IppResourceFieldMapper> selectAll() {
        return service.selectAll();
    }

    @GetMapping("/field/{field}")
    public List<IppResourceFieldMapper> selectByField(@PathVariable("field") Integer field) {
        return service.selectByField(field);
    }

    @GetMapping("/by-original")
    public List<IppResourceFieldMapper> selectByOriginalStr(@RequestParam("originalStr") String originalStr) {
        return service.selectByOriginalStr(originalStr);
    }

    @GetMapping("/by-standard")
    public List<IppResourceFieldMapper> selectByStandardStr(@RequestParam("standardStr") String standardStr) {
        return service.selectByStandardStr(standardStr);
    }

    @PostMapping
    public int insert(@RequestBody IppResourceFieldMapper record) {
        if (record.getCreateAt() == null) {
            record.setCreateAt(new Date());
        }
        // 按你的规则：新增时 id 必须连续（max(id)+1）
        return service.insertContinuous(record);
    }

    @PutMapping
    public int updateByPrimaryKey(@RequestBody IppResourceFieldMapper record) {
        return service.updateByPrimaryKey(record);
    }

    @PatchMapping
    public int updateByPrimaryKeySelective(@RequestBody IppResourceFieldMapper record) {
        return service.updateByPrimaryKeySelective(record);
    }

    @DeleteMapping("/{id}")
    public int deleteByPrimaryKey(@PathVariable("id") Long id) {
        return service.deleteByPrimaryKey(id);
    }

    /**
     * 通过 originalStr + standardStr + field 删除一条记录，并保持 id 连续。
     * 示例：DELETE /api/ipp/resource-field-mapper/by-condition?originalStr=公顷_test&standardStr=ha&field=1
     */
    @DeleteMapping("/by-condition")
    public int deleteByKey(@RequestParam("originalStr") String originalStr,
                           @RequestParam("standardStr") String standardStr,
                           @RequestParam("field") Integer field) {
        return service.deleteByKey(originalStr, standardStr, field);
    }
}
