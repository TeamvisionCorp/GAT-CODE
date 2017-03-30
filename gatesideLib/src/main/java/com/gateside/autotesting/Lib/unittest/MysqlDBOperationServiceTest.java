package com.gateside.autotesting.Lib.unittest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.dbService.MysqlService;

public class MysqlDBOperationServiceTest {
  @Test
  public void f() throws Exception {
	  MysqlService service=new MysqlService("jdbc:mysql://10.3.254.227:3306/laohu_cms");
	  List<List<String>> resultSet= service.executeQuery("SELECT * FROM ic_app_index","apple_user","aabbccdd");
	  System.out.println(resultSet.size());
	  System.out.println(resultSet.get(0).get(0));
  }
}
