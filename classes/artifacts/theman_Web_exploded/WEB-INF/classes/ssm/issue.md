1. 导入JS的时候，在开发者工具的Source目录下如果找不到对应的资源文件，则说明导入失败，即使在NetWork选项卡中有获取到。导入JS的JS的文件名称必须与路径下的文件名称完全一致，如果文件名称为static/bootstrap-3.3.7-dist/js/bootstrap.js但是导入的时候指定的src的值为static/bootstrap-3.3.7-dist/js/bootstrap.min.js，将会发生错误。
2. Mybatis如果希望update返回受影响的行数，需要在jdbcURL中配置useAffectedRows=true
3. 给按钮绑定事件的时候，要考虑按钮是什么时候创建出来的，如果按钮是在加载完JS之后调用Ajax数据创建按钮，则不能直接绑定事件，因为绑定事件的时候按钮还没有被创建。这种情况下可以通过Jquery的on方法来绑定事件。
4. 给不用的控件赋值要用不同的方法。详见Jquery文档
5. prop修改和读取dom原生的属性的值，attr获取自定义属性的值