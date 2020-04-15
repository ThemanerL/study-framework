package edms.mapper;

import edms.model.OmOrder;
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

import static edms.mapper.OmOrderDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface OmOrderMapper {
  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  BasicColumn[] selectList = BasicColumn.columnList(id, type, confirmTime, totalQuantity, totalAmount, gmtCreate, gmtUpdate, isDeleted);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  long count(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
  int delete(DeleteStatementProvider deleteStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
  int insert(InsertStatementProvider<OmOrder> insertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
  int insertMultiple(MultiRowInsertStatementProvider<OmOrder> multipleInsertStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("OmOrderResult")
  Optional<OmOrder> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "OmOrderResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
      @Result(column = "confirm_time", property = "confirmTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "total_quantity", property = "totalQuantity", jdbcType = JdbcType.INTEGER),
      @Result(column = "total_amount", property = "totalAmount", jdbcType = JdbcType.DECIMAL),
      @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "gmt_update", property = "gmtUpdate", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
  })
  List<OmOrder> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
  int update(UpdateStatementProvider updateStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int insert(OmOrder record) {
    return MyBatis3Utils.insert(this::insert, record, omOrder, c ->
        c.map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(confirmTime).toProperty("confirmTime")
            .map(totalQuantity).toProperty("totalQuantity")
            .map(totalAmount).toProperty("totalAmount")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int insertMultiple(Collection<OmOrder> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, omOrder, c ->
        c.map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(confirmTime).toProperty("confirmTime")
            .map(totalQuantity).toProperty("totalQuantity")
            .map(totalAmount).toProperty("totalAmount")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtUpdate).toProperty("gmtUpdate")
            .map(isDeleted).toProperty("isDeleted")
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int insertSelective(OmOrder record) {
    return MyBatis3Utils.insert(this::insert, record, omOrder, c ->
        c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(confirmTime).toPropertyWhenPresent("confirmTime", record::getConfirmTime)
            .map(totalQuantity).toPropertyWhenPresent("totalQuantity", record::getTotalQuantity)
            .map(totalAmount).toPropertyWhenPresent("totalAmount", record::getTotalAmount)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtUpdate).toPropertyWhenPresent("gmtUpdate", record::getGmtUpdate)
            .map(isDeleted).toPropertyWhenPresent("isDeleted", record::getIsDeleted)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default Optional<OmOrder> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default List<OmOrder> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default List<OmOrder> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default Optional<OmOrder> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, omOrder, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  static UpdateDSL<UpdateModel> updateAllColumns(OmOrder record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalTo(record::getId)
        .set(type).equalTo(record::getType)
        .set(confirmTime).equalTo(record::getConfirmTime)
        .set(totalQuantity).equalTo(record::getTotalQuantity)
        .set(totalAmount).equalTo(record::getTotalAmount)
        .set(gmtCreate).equalTo(record::getGmtCreate)
        .set(gmtUpdate).equalTo(record::getGmtUpdate)
        .set(isDeleted).equalTo(record::getIsDeleted);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  static UpdateDSL<UpdateModel> updateSelectiveColumns(OmOrder record, UpdateDSL<UpdateModel> dsl) {
    return dsl.set(id).equalToWhenPresent(record::getId)
        .set(type).equalToWhenPresent(record::getType)
        .set(confirmTime).equalToWhenPresent(record::getConfirmTime)
        .set(totalQuantity).equalToWhenPresent(record::getTotalQuantity)
        .set(totalAmount).equalToWhenPresent(record::getTotalAmount)
        .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
        .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
        .set(isDeleted).equalToWhenPresent(record::getIsDeleted);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int updateByPrimaryKey(OmOrder record) {
    return update(c ->
        c.set(type).equalTo(record::getType)
            .set(confirmTime).equalTo(record::getConfirmTime)
            .set(totalQuantity).equalTo(record::getTotalQuantity)
            .set(totalAmount).equalTo(record::getTotalAmount)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtUpdate).equalTo(record::getGmtUpdate)
            .set(isDeleted).equalTo(record::getIsDeleted)
            .where(id, isEqualTo(record::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: om_order")
  default int updateByPrimaryKeySelective(OmOrder record) {
    return update(c ->
        c.set(type).equalToWhenPresent(record::getType)
            .set(confirmTime).equalToWhenPresent(record::getConfirmTime)
            .set(totalQuantity).equalToWhenPresent(record::getTotalQuantity)
            .set(totalAmount).equalToWhenPresent(record::getTotalAmount)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtUpdate).equalToWhenPresent(record::getGmtUpdate)
            .set(isDeleted).equalToWhenPresent(record::getIsDeleted)
            .where(id, isEqualTo(record::getId))
    );
  }
}