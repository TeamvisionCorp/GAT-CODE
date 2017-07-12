package com.gateside.autotesting.Gat.manager;

import java.util.List;

import com.gateside.autotesting.Gat.dataobject.TestObject;


public abstract class TestObjectImporter
{
	public abstract void doImport(String objectFilePath) throws Exception;
	
	public abstract List<String> getFilePath(String rootDir) throws Exception;
	
	public abstract String 	toJsonString(TestObject testObject,Integer objectType) throws Exception;
}
