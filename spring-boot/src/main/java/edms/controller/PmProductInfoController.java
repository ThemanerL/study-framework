package edms.controller;

import edms.mapper.PmProductInfoMapper;
import edms.model.PmProductInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李重辰
 * @date 2020/9/1 22:11
 */
@RestController
public class PmProductInfoController {

  @Resource
  PmProductInfoMapper mapper;

  @GetMapping("/productInfo/{id}")
  public PmProductInfo getPmProductInfoById(@PathVariable("id") Long id) {
    return mapper.selectByPrimaryKey(id);
  }

  @GetMapping("/productInfo")
  public PmProductInfo insertPmProductInfo(PmProductInfo pmProductInfo) {
    mapper.insert(pmProductInfo);
    return pmProductInfo;
  }
}
