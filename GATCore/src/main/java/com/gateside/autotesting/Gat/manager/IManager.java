package com.gateside.autotesting.Gat.manager;

import com.gateside.autotesting.Gat.dataobject.TestObject;

public interface IManager
{
   public TestObject getItem(String ID) throws Exception;
}
