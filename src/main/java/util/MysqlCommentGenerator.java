package main.java.util;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * XML不重写，set get 删掉多余的
 *
 * @author 李重辰
 * @date 2019/3/31 12:57
 */
public class MysqlCommentGenerator extends DefaultCommentGenerator {

  private Properties properties;

  public MysqlCommentGenerator() {
    properties = new Properties();
  }

  @Override
  protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    String author = properties.getProperty("author", "李重辰");
    String dateFormat = properties.getProperty("dateFormat", "yyyy/MM/dd hh:mm");
    javaElement.addJavaDocLine(" *");
    StringBuilder sb = new StringBuilder();
    sb.append(" * ");
    sb.append(author);
    if (markAsDoNotDelete) {
      sb.append(" do_not_delete_during_merge");
    }
    String s = new SimpleDateFormat(dateFormat).format(new Date());
    sb.append(' ');
    sb.append(s);
    javaElement.addJavaDocLine(sb.toString());
  }

  @Override
  public void addConfigurationProperties(Properties properties) {
    // 获取自定义的 properties
    this.properties.putAll(properties);
  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    // 获取列注释
    String remarks = introspectedColumn.getRemarks();
    field.addJavaDocLine("/**");
    field.addJavaDocLine(" * " + remarks);
    field.addJavaDocLine(" */");
  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

  }

  /**
   * 公用代码
   *
   * @param innerClass        、
   * @param introspectedTable 、
   */
  private void myClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    String author = properties.getProperty("author", "李重辰");
    String dateFormat = properties.getProperty("dateFormat", "yyyy/MM/dd hh:mm");
    SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
    // 获取表注释
    String remarks = introspectedTable.getRemarks();
    innerClass.addJavaDocLine("/**");
    if (!"".equals(remarks)) {
      innerClass.addJavaDocLine(" * " + remarks);
      innerClass.addJavaDocLine(" *");
    }
    innerClass.addJavaDocLine(" * @author " + author);
    innerClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
    innerClass.addJavaDocLine(" */");
    System.out.println(innerClass.getClass().getSimpleName());
  }

  @Override
  public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    myClassComment(topLevelClass, introspectedTable);
  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    myClassComment(innerClass, introspectedTable);
  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    myClassComment(innerClass, introspectedTable);
  }

  @Override
  public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
  }

  @Override
  public void addSetterComment(Method method,
                               IntrospectedTable introspectedTable,
                               IntrospectedColumn introspectedColumn) {
  }

  @Override
  public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
  }

}
