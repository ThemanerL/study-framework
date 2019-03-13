package mybatis.basic.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李重辰
 * @date 2019/2/19 13:45
 */
@WebServlet("/MybatisServlet")
public class MybatisServlet extends javax.servlet.http.HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=GBK");
    EmployeeTest test = new EmployeeTest();
    try {
      request.setAttribute("emp1", test.printEmpByInterfaceXml(1));
      request.setAttribute("emp2", test.printEmpByInterfaceXml(2));
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}
