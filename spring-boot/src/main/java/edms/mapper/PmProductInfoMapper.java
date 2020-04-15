package edms.mapper;

import static edms.mapper.PmProductInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import edms.model.PmProductInfo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface PmProductInfoMapper {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  BasicColumn[] selectList = BasicColumn.columnList(id, corpNo, brandNo, name, spec, price, unit, gmtCreate, gmtUpdate, isDeleted, imgs);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  long count(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
  int delete(DeleteStatementProvider deleteStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
  int insert(InsertStatementProvider<PmProductInfo> insertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
  int insertMultiple(MultiRowInsertStatementProvider<PmProductInfo> multipleInsertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("PmProductInfoResult")
  Optional<PmProductInfo> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.91+08:00", comments = "Source Table: pm_product_info")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "PmProductInfoResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "corp_no", property = "corpNo", jdbcType = JdbcType.VARCHAR),
      @Result(column = "brand_no", property = "brandNo", jdbcType = JdbcType.INTEGER),
      @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
      @Result(column = "spec", property = "spec", jdbcType = JdbcType.VARCHAR),
      @Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
      @Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
      @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "gmt_update", property = "gmtUpdate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT),
      @Result(column = "imgs", property = "imgs", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<PmProductInfo> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
  int update(UpdateStatementProvider updateStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int insert(PmProductInfo record) {
    return MyBatis3Utils.insert(this::insert, record, pmProductInfo, c ->
        c.map(id).toProperty("id")
            .map(corpNo).toProperty("corpNo")
            .map(brandNo).toProperty("brandNo")
            .map(name).toProperty("name")
            .map(spec).toProperty("spec")
            .map(price).toProperty("price")
            .map(unit).toProperty("unit")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
            .map(imgs).toProperty("imgs")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int insertMultiple(Collection<PmProductInfo> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, pmProductInfo, c ->
        c.map(id).toProperty("id")
            .map(corpNo).toProperty("corpNo")
            .map(brandNo).toProperty("brandNo")
            .map(name).toProperty("name")
            .map(spec).toProperty("spec")
            .map(price).toProperty("price")
            .map(unit).toProperty("unit")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
            .map(imgs).toProperty("imgs")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int insertSelective(PmProductInfo record) {
    return MyBatis3Utils.insert(this::insert, record, pmProductInfo, c ->
        c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(corpNo).toPropertyWhenPresent("corpNo", record::getCorpNo)
            .map(brandNo).toPropertyWhenPresent("brandNo", record::getBrandNo)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(spec).toPropertyWhenPresent("spec", record::getSpec)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtUpdate).toPropertyWhenPresent("gmtUpdate", record::getGmtUpdate)
            .map(isDeleted).toPropertyWhenPresent("isDeleted", record::getIsDeleted)
            .map(imgs).toPropertyWhenPresent("imgs", record::getImgs)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default Optional<PmProductInfo> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default List<PmProductInfo> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default List<PmProductInfo> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default Optional<PmProductInfo> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, pmProductInfo, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  static UpdateDSL<UpdateModel> updateAllColumns(PmProductInfo record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(record::getId)
        .set(corpNo).equalTo(record::getCorpNo)
        .set(brandNo).equalTo(record::getBrandNo)
        .set(name).equalTo(record::getName)
        .set(spec).equalTo(record::getSpec)
        .set(price).equalTo(record::getPrice)
        .set(unit).equalTo(record::getUnit)
        .set(gmtCreate).equalTo(record::getGmtCreate)
        .set(gmtUpdate).equalTo(record::getGmtUpdate)
        .set(isDeleted).equalTo(record::getIsDeleted)
        .set(imgs).equalTo(record::getImgs);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.911+08:00", comments = "Source Table: pm_product_info")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(PmProductInfo record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(record::getId)
        .set(corpNo).equalToWhenPresent(record::getCorpNo)
        .set(brandNo).equalToWhenPresent(record::getBrandNo)
        .set(name).equalToWhenPresent(record::getName)
        .set(spec).equalToWhenPresent(record::getSpec)
        .set(price).equalToWhenPresent(record::getPrice)
        .set(unit).equalToWhenPresent(record::getUnit)
        .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
        .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
        .set(isDeleted).equalToWhenPresent(record::getIsDeleted)
        .set(imgs).equalToWhenPresent(record::getImgs);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.912+08:00", comments = "Source Table: pm_product_info")
  default int updateByPrimaryKey(PmProductInfo record) {
    return update(c ->
        c.set(corpNo).equalTo(record::getCorpNo)
            .set(brandNo).equalTo(record::getBrandNo)
            .set(name).equalTo(record::getName)
            .set(spec).equalTo(record::getSpec)
            .set(price).equalTo(record::getPrice)
            .set(unit).equalTo(record::getUnit)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtUpdate).equalTo(record::getGmtUpdate)
            .set(isDeleted).equalTo(record::getIsDeleted)
            .set(imgs).equalTo(record::getImgs)
            .where(id, isEqualTo(record::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-04-15T09:35:54.912+08:00", comments = "Source Table: pm_product_info")
  default int updateByPrimaryKeySelective(PmProductInfo record) {
    return update(c ->
        c.set(corpNo).equalToWhenPresent(record::getCorpNo)
            .set(brandNo).equalToWhenPresent(record::getBrandNo)
            .set(name).equalToWhenPresent(record::getName)
            .set(spec).equalToWhenPresent(record::getSpec)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(unit).equalToWhenPresent(record::getUnit)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
            .set(isDeleted).equalToWhenPresent(record::getIsDeleted)
            .set(imgs).equalToWhenPresent(record::getImgs)
            .where(id, isEqualTo(record::getId))
    );
  }
}