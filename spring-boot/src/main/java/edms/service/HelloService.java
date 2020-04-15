package edms.service;

import edms.mapper.PmProductInfoMapper;
import edms.model.PmProductInfo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import javax.annotation.Resource;
import java.util.List;

import static edms.mapper.PmProductInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @author 李重辰
 * @date 2020/3/29 16:45
 */
public class HelloService {

  @Resource
  private PmProductInfoMapper mapper;

  public List<PmProductInfo> testSelectByExample() {
    SelectStatementProvider selectStatement = select(id, corpNo, brandNo, name, spec, images)
        .from(pmProductInfo)
        .where(id, isEqualTo(1L))
        .build()
        .render(RenderingStrategies.MYBATIS3);
    return mapper.selectMany(selectStatement);
  }
}
