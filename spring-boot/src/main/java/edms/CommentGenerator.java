package edms;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 自定义注释生成器
 *
 * @author 李重辰
 * @date 2020/04/13 09:03
 */
public class CommentGenerator extends DefaultCommentGenerator {
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");

  @Override
  protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
  }

  @Override
  public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    String remarks = introspectedColumn.getRemarks();
    if (StringUtility.stringHasValue(remarks)) {
      field.addJavaDocLine("/**");
      String[] remarkLines = remarks.split(System.getProperty("line.separator"));
      for (String remarkLine : remarkLines) {
        field.addJavaDocLine(" * " + remarkLine);
      }
      field.addJavaDocLine(" */");
    }
  }

  @Override
  public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    classComment(topLevelClass, introspectedTable);
  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
  }

  /**
   * 公用代码
   */
  private void classComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    // 获取表注释
    String remarks = introspectedTable.getRemarks();
    innerClass.addJavaDocLine("/**");
    if (!"".equals(remarks)) {
      innerClass.addJavaDocLine(" * " + remarks);
      innerClass.addJavaDocLine(" *");
    }
    innerClass.addJavaDocLine(" * @author " + "李重辰");
    innerClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
    innerClass.addJavaDocLine(" */");
    System.out.println(innerClass.getClass().getSimpleName());
  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    classComment(innerClass, introspectedTable);
  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    classComment(innerClass, introspectedTable);
  }

  /**
   * 删除xml中自带的注释
   */
  @Override
  public void addComment(XmlElement xmlElement) {

  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                              IntrospectedColumn introspectedColumn) {
    String remarks = introspectedColumn.getRemarks();
    if (StringUtility.stringHasValue(remarks)) {
      field.addJavaDocLine("/**");
      String[] remarkLines = remarks.split(System.getProperty("line.separator"));
      for (String remarkLine : remarkLines) {
        field.addJavaDocLine(" * " + remarkLine);
      }
      addJavadocTag(field, false);
      field.addJavaDocLine(" */");
    }

  }

  @Override
  public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                               IntrospectedColumn introspectedColumn) {
  }

  @Override
  public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

  }

  @Override
  public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

  }
}
