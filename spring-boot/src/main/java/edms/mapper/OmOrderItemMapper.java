package edms.mapper;

import edms.model.OmOrderItem;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static edms.mapper.OmOrderItemDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface OmOrderItemMapper {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  BasicColumn[] selectList = BasicColumn.columnList(id, it, productId, productPrice, productQuantity, productUnit, productName, productSpec, gmtCreate, gmtUpdate, isDeleted);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  long count(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
  int delete(DeleteStatementProvider deleteStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
  int insert(InsertStatementProvider<OmOrderItem> insertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
  int insertMultiple(MultiRowInsertStatementProvider<OmOrderItem> multipleInsertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("OmOrderItemResult")
  Optional<OmOrderItem> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "OmOrderItemResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "it", property = "it", jdbcType = JdbcType.VARCHAR),
      @Result(column = "product_id", property = "productId", jdbcType = JdbcType.BIGINT),
      @Result(column = "product_price", property = "productPrice", jdbcType = JdbcType.DECIMAL),
      @Result(column = "product_quantity", property = "productQuantity", jdbcType = JdbcType.INTEGER),
      @Result(column = "product_unit", property = "productUnit", jdbcType = JdbcType.VARCHAR),
      @Result(column = "product_name", property = "productName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "product_spec", property = "productSpec", jdbcType = JdbcType.VARCHAR),
      @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "gmt_update", property = "gmtUpdate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.INTEGER)
  })
  List<OmOrderItem> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
  int update(UpdateStatementProvider updateStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int insert(OmOrderItem record) {
    return MyBatis3Utils.insert(this::insert, record, omOrderItem, c ->
        c.map(id).toProperty("id")
            .map(it).toProperty("it")
            .map(productId).toProperty("productId")
            .map(productPrice).toProperty("productPrice")
            .map(productQuantity).toProperty("productQuantity")
            .map(productUnit).toProperty("productUnit")
            .map(productName).toProperty("productName")
            .map(productSpec).toProperty("productSpec")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int insertMultiple(Collection<OmOrderItem> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, omOrderItem, c ->
        c.map(id).toProperty("id")
            .map(it).toProperty("it")
            .map(productId).toProperty("productId")
            .map(productPrice).toProperty("productPrice")
            .map(productQuantity).toProperty("productQuantity")
            .map(productUnit).toProperty("productUnit")
            .map(productName).toProperty("productName")
            .map(productSpec).toProperty("productSpec")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int insertSelective(OmOrderItem record) {
    return MyBatis3Utils.insert(this::insert, record, omOrderItem, c ->
        c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(it).toPropertyWhenPresent("it", record::getIt)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(productPrice).toPropertyWhenPresent("productPrice", record::getProductPrice)
            .map(productQuantity).toPropertyWhenPresent("productQuantity", record::getProductQuantity)
            .map(productUnit).toPropertyWhenPresent("productUnit", record::getProductUnit)
            .map(productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(productSpec).toPropertyWhenPresent("productSpec", record::getProductSpec)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtUpdate).toPropertyWhenPresent("gmtUpdate", record::getGmtUpdate)
            .map(isDeleted).toPropertyWhenPresent("isDeleted", record::getIsDeleted)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default Optional<OmOrderItem> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default List<OmOrderItem> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default List<OmOrderItem> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default Optional<OmOrderItem> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, omOrderItem, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  static UpdateDSL<UpdateModel> updateAllColumns(OmOrderItem record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(record::getId)
        .set(it).equalTo(record::getIt)
        .set(productId).equalTo(record::getProductId)
        .set(productPrice).equalTo(record::getProductPrice)
        .set(productQuantity).equalTo(record::getProductQuantity)
        .set(productUnit).equalTo(record::getProductUnit)
        .set(productName).equalTo(record::getProductName)
        .set(productSpec).equalTo(record::getProductSpec)
        .set(gmtCreate).equalTo(record::getGmtCreate)
        .set(gmtUpdate).equalTo(record::getGmtUpdate)
        .set(isDeleted).equalTo(record::getIsDeleted);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(OmOrderItem record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(record::getId)
        .set(it).equalToWhenPresent(record::getIt)
        .set(productId).equalToWhenPresent(record::getProductId)
        .set(productPrice).equalToWhenPresent(record::getProductPrice)
        .set(productQuantity).equalToWhenPresent(record::getProductQuantity)
        .set(productUnit).equalToWhenPresent(record::getProductUnit)
        .set(productName).equalToWhenPresent(record::getProductName)
        .set(productSpec).equalToWhenPresent(record::getProductSpec)
        .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
        .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
        .set(isDeleted).equalToWhenPresent(record::getIsDeleted);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int updateByPrimaryKey(OmOrderItem record) {
    return update(c ->
        c.set(it).equalTo(record::getIt)
            .set(productId).equalTo(record::getProductId)
            .set(productPrice).equalTo(record::getProductPrice)
            .set(productQuantity).equalTo(record::getProductQuantity)
            .set(productUnit).equalTo(record::getProductUnit)
            .set(productName).equalTo(record::getProductName)
            .set(productSpec).equalTo(record::getProductSpec)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtUpdate).equalTo(record::getGmtUpdate)
            .set(isDeleted).equalTo(record::getIsDeleted)
            .where(id, isEqualTo(record::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order_item")
  default int updateByPrimaryKeySelective(OmOrderItem record) {
    return update(c ->
        c.set(it).equalToWhenPresent(record::getIt)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(productPrice).equalToWhenPresent(record::getProductPrice)
            .set(productQuantity).equalToWhenPresent(record::getProductQuantity)
            .set(productUnit).equalToWhenPresent(record::getProductUnit)
            .set(productName).equalToWhenPresent(record::getProductName)
            .set(productSpec).equalToWhenPresent(record::getProductSpec)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
            .set(isDeleted).equalToWhenPresent(record::getIsDeleted)
            .where(id, isEqualTo(record::getId))
    );
  }
}