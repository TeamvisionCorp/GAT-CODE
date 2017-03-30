package com.gateside.autotesting.Lib.unittest;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import org.testng.annotations.Test;

import com.gateside.autotesting.Lib.common.ClassReflector;

public class ClassReflectorTest 
{
  @Test
  public void setFiledTest()
  {
	  Person jackPerson=new Person();
	  try 
	  {
		ClassReflector.setFiled(jackPerson.getClass(),jackPerson,"age", 18);
		System.out.print(jackPerson.getAge());
		ClassReflector.setFiled(jackPerson.getClass(),jackPerson,"name","baidu");
		System.out.print(jackPerson.getAge());
		System.out.print(jackPerson.getName());
	  } 
	  catch (Exception e)
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
  }
  
  @Test
  public void createInstanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
	  String jarFilePathString="D:\\Work\\Code\\EclipseWorkSpace\\TestProject\\lib\\targetProject.jar";
	  Object targetInstanceObject= ClassReflector.createInstance(jarFilePathString,"com.baidu.targetProject.TargetClass");
      ClassReflector.getMethod(targetInstanceObject,"targetMethod").invoke(targetInstanceObject);
  }
  
  class Person
  {
	  private Integer age=0;
	  private String name="";

	/**
	 * @return the age
	 */
	public Integer getAge()
	{
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age)
	{
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
  }
}
