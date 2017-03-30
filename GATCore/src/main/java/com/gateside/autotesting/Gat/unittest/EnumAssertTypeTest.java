package com.gateside.autotesting.Gat.unittest;


import org.junit.Test;

import com.gateside.autotesting.Gat.dataobject.testcase.EnumAssertType;

public class EnumAssertTypeTest {

	@Test
	public void test() 
	{
		System.out.println("Equal".equals(EnumAssertType.Equal.toString()));
	}

}
