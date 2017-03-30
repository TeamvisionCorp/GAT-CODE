package com.gateside.autotesting.Lib.unittest;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.gateside.autotesting.Lib.dbService.*;

public class ExcelDBOperationTest {
//  @Test
//  public void f() throws SQLException, ClassNotFoundException {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\result.xls");
//	  ResultSet rs= service.read("select  t2.planname, sum(t1.active) as Active,sum(t1.reg) as reg From [Sheet1$] t1, [Sheet3$] t2, [Sheet4$] t3 where t1.planid=t2.planid  and t1.unitid=t3.unitid group by t2.planname");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("planname")+"\t");
//	   System.out.print(rs.getString("Active")+"\t");
//	   System.out.print(rs.getString("reg")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
//  }
  
//  @Test
//  public void f() throws SQLException, ClassNotFoundException {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\result.xls");
//	  ResultSet rs= service.read("select  t1.planid, sum(t1.active) as Active,sum(t1.reg) as reg From [Sheet1$] t1  group by t1.planid");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("planid")+"\t");
//	   System.out.print(rs.getString("Active")+"\t");
//	   System.out.print(rs.getString("reg")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
//  }
//  @Test
//  public void t() throws Exception
//  {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\sem.xls");
//	  ResultSet rs= service.read("select  t1.planname, sum(t1.active) as Active,sum(t1.reg) as reg From [Sheet3$] t1 group by t1.planname");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("planname")+"\t");
//	   System.out.print(rs.getString("Active")+"\t");
//	   System.out.print(rs.getString("reg")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
////  }
//  @Test
//  public void t() throws Exception
//  {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\sem.xls");
//	  ResultSet rs= service.read("select  t1.planname, sum(t1.active) as Active,sum(t1.reg) as reg From [Sheet3$] t1 where t1.accountid=1381016 group by t1.planname");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("planname")+"\t");
//	   System.out.print(rs.getString("Active")+"\t");
//	   System.out.print(rs.getString("reg")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
//  }
//  @Test
//  public void SEMCost() throws Exception
//  {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\sem.xls");
//	  ResultSet rs= service.read("select  t1.gamename,sum(t1.cost) as cost, sum(t1.active) as Active,sum(t1.reg) as reg From [Sheet3$] t1 group by t1.gamename order by sum(t1.cost) desc ");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("gamename")+"\t");
//	   System.out.print(rs.getString("cost")+"\t");
//	   System.out.print(rs.getString("Active")+"\t");
//	   System.out.print(rs.getString("reg")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
//  }
//  
//  @Test
//public void SEMPlanID() throws Exception
//  {
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\api.xls");
//	  ResultSet rs= service.read("select t1.planid,t1.unitid,t1.wordid,t1.imp,t1.click,t1.cost,t1.postion,t2.pv,t2.ip,t2.cpv,t2.bindnum from [Sheet1$] t1 , [Sheet2$] t2 ");
//	  while(rs.next())  
//	  {  
////		  String idString=rs.getString(columnIndex)
////		  String gameID=rs.getString("gameid");
////		  String uuidString=rs.getString("uuid");
//
//			  System.out.print(rs.getString("planid")+"\t");
//			  System.out.print(rs.getString("imp")+"\t");
//			  System.out.print(rs.getString("click")+"\t");
//			  System.out.print(rs.getString("cost")+"\t");
//			  System.out.print(rs.getString("postion")+"\t");
//			  System.out.print(rs.getString("pv")+"\t");
//			  System.out.print(rs.getString("ip")+"\t");
//			  System.out.print(rs.getString("cpv")+"\t");
//			  System.out.print(rs.getString("bindnum")+"\t");
//			  System.out.print("\n");
//			 Write(rs.getString("planid")+"\t");
//			 Write(rs.getString("imp")+"\t");
//			 Write(rs.getString("click")+"\t");
//			 Write(rs.getString("cost")+"\t");
//			 Write(rs.getString("postion")+"\t");
//			 Write(rs.getString("pv")+"\t");
//			 Write(rs.getString("ip")+"\t");
//			 Write(rs.getString("cpv")+"\t");
//			 Write(rs.getString("bindnum")+"\t");
//			 Write("\n");
//	  }  
//  }
//  
//  
//  @Test
//  public void PlanToName() throws Exception
//    {
//  	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\api.xls");
//  	  ResultSet rs= service.read("select t2.planid,t2.planname,t1.unitid,t1.wordid,t1.pv,t1.ip,t1.cpv,t1.bindnum from [Sheet2$] t1  inner join [Sheet3$] t2 on t1.planid=t2.planid ");
//  	  while(rs.next())  
//  	  {  
////  		  String idString=rs.getString(columnIndex)
////  		  String gameID=rs.getString("gameid");
////  		  String uuidString=rs.getString("uuid");
//              String plannameString=rs.getString("planname");
//              String pvString=rs.getString("pv");
//              String ip=rs.getString("ip");
//              String cpvString=rs.getString("cpv");
//              String bindString=rs.getString("bindnum");
//              System.out.print(plannameString+"\t");
//  			  System.out.print(pvString+"\t");
//  			  System.out.print(ip+"\t");
//  			  System.out.print(cpvString+"\t");
//  			  System.out.print(bindString+"\t");
//  			  System.out.print("\n");
//  			 Write(plannameString+"\t");
//  			 Write(pvString+"\t");
//  			 Write(pvString+"\t");
//  			 Write(cpvString+"\t");
//  			 Write(bindString+"\t");
//  			 Write("\n");
//  	  }  
//    }
  
//  @Test
//  public void GenerateData() throws Exception
//    {
//  	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\api.xls");
//  	  ResultSet rs= service.read("select t1.planname,sum(t2.pv) as pv from [Sheet1$] t1  inner join [Sheet4$] t2 on t1.planname=t2.planname group by t1.planname");
//  	  while(rs.next())  
//  	  {  
////  		  String idString=rs.getString(columnIndex)
////  		  String gameID=rs.getString("gameid");
//              String plannameString=rs.getString("planname");
//              String pvString=rs.getString("pv");
////              String ip=rs.getString("ip");
////              String cpvString=rs.getString("cpv");
////              String bindString=rs.getString("bindnum");
//              System.out.print(plannameString+"\t");
//  			  System.out.print(pvString+"\t");
////  			  System.out.print(ip+"\t");
////  			  System.out.print(cpvString+"\t");
////  			  System.out.print(bindString+"\t");
//  			  System.out.print("\n");
//  			 Write(plannameString+"\t");
//  			 Write(pvString+"\t");
////  			 Write(pvString+"\t");
////  			 Write(cpvString+"\t");
////  			 Write(bindString+"\t");
//  			 Write("\n");
//  	  }  
//    }
  
  
//  @Test
//  public void GenerateData() throws Exception
//    {
//  	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\api.xls");
//  	  ResultSet rs= service.read("select t2.planname,sum(t1.pv) as pv from [Sheet2$] t1 left  join [Sheet3$] t2 on t1.planid=t2.planid group by t2.planname");
//  	  while(rs.next())  
//  	  {  
////  		  String idString=rs.getString(columnIndex)
////  		  String gameID=rs.getString("gameid");
//              String plannameString=rs.getString("planname");
//              String pvString=rs.getString("pv");
////              String ip=rs.getString("ip");
////              String cpvString=rs.getString("cpv");
////              String bindString=rs.getString("bindnum");
//              System.out.print(plannameString+"\t");
//  			  System.out.print(pvString+"\t");
////  			  System.out.print(ip+"\t");
////  			  System.out.print(cpvString+"\t");
////  			  System.out.print(bindString+"\t");
//  			  System.out.print("\n");
//  			 Write(plannameString+"\t");
//  			 Write(pvString+"\t");
////  			 Write(pvString+"\t");
////  			 Write(cpvString+"\t");
////  			 Write(bindString+"\t");
//  			 Write("\n");
//  	  }  
//    }
  
  @Test
  public void GenerateAPIPlanData() throws Exception
    {
  	  ExcelService service=new ExcelService("d:\\api.xls");
  	  ResultSet rs= service.executeQuery("select t1.planname,sum(t1.pv) as pv from [Sheet1$] t1  group by t1.planname");
  	  while(rs.next())  
  	  {  
//  		  String idString=rs.getString(columnIndex)
//  		  String gameID=rs.getString("gameid");
              String plannameString=rs.getString("planname");
              String pvString=rs.getString("pv");
//              String ip=rs.getString("ip");
//              String cpvString=rs.getString("cpv");
//              String bindString=rs.getString("bindnum");
              System.out.print(plannameString+"\t");
  			  System.out.print(pvString+"\t");
//  			  System.out.print(ip+"\t");
//  			  System.out.print(cpvString+"\t");
//  			  System.out.print(bindString+"\t");
  			  System.out.print("\n");
  			 Write(plannameString+"\t");
  			 Write(pvString+"\t");
//  			 Write(pvString+"\t");
//  			 Write(cpvString+"\t");
//  			 Write(bindString+"\t");
  			 Write("\n");
  	  }  
    }
  
//  @Test
//  public void GenerateTotalData() throws Exception
//    {
//  	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\api.xls");
//  	  ResultSet rs= service.read("select t1.planname,sum(t2.pv) as pv from [Sheet7$] t1 left outer join [Sheet4$] t2 on t1.planname=t2.planname group by t1.planname");
//  	  while(rs.next())  
//  	  {  
////  		  String idString=rs.getString(columnIndex)
////  		  String gameID=rs.getString("gameid");
//              String plannameString=rs.getString("planname");
//              String pvString=rs.getString("pv");
////              String ip=rs.getString("ip");
////              String cpvString=rs.getString("cpv");
////              String bindString=rs.getString("bindnum");
//              System.out.print(plannameString+"\t");
//  			  System.out.print(pvString+"\t");
////  			  System.out.print(ip+"\t");
////  			  System.out.print(cpvString+"\t");
////  			  System.out.print(bindString+"\t");
//  			  System.out.print("\n");
//  			 Write(plannameString+"\t");
//  			 Write(pvString+"\t");
////  			 Write(pvString+"\t");
////  			 Write(cpvString+"\t");
////  			 Write(bindString+"\t");
//  			 Write("\n");
//  	  }  
//    }
  
//@Test
//public void SEMCost() throws Exception
//{
//	  ExcelDBOperationService service=new ExcelDBOperationService("d:\\promotion.xls");
//	  ResultSet rs= service.read("select t1.planid,t1.unitid,t1.gameid,count(t1.uuid) as count from [Sheet4$] t1 group by t1.planid,t1.unitid,t1.gameid ");
//	  while(rs.next())  
//	  {  
//
//	   System.out.print(rs.getString("planid")+"\t");
//	   System.out.print(rs.getString("unitid")+"\t");
//	   System.out.print(rs.getString("gameid")+"\t");
//	   System.out.print(rs.getString("count")+"\t");
//	   System.out.print("\n");
//	   
//	        
//	  }  
//}
	

  
  
	
	
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
  public  void Write(String str) {


		FileWriter writer;

		try {

			writer = new FileWriter("D:\\Result.txt",true);


			writer.write(str);

			writer.flush();

			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
