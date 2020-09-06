package edms.service;

import edms.mapper.PmProductInfoMapper;
import edms.model.PmProductInfo;
import edms.model.PmProductInfoExample;

import java.util.List;

/**
 * @author 李重辰
 * @date 2020/3/29 16:45
 */
public class HelloService {

  private PmProductInfoMapper mapper;

  public List<PmProductInfo> testSelectByExample() {
    PmProductInfoExample example = new PmProductInfoExample();
    example.createCriteria().andIdEqualTo(1L);
    return mapper.selectByExample(example);
  }
}
