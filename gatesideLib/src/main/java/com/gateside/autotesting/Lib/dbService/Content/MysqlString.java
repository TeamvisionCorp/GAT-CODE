package com.gateside.autotesting.Lib.dbService.Content;

public class MysqlString
{
	public static String GETCOLUMNNames="desc "+"${TABLENAME} ;";
	
	public static String INSERT="INSERT INTO "+"${TABLENAME}"+"  ("+"${COLUMNNAMES} ) VALUES("+"${VALUES}"+")";

} 