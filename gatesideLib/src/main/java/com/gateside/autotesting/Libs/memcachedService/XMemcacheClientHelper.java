package com.gateside.autotesting.Libs.memcachedService;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemcacheClientHelper 
{
	
	private String serverAddress="";
	
	public XMemcacheClientHelper(String serverAddress,Integer port)
	{
		this.serverAddress=serverAddress+":"+String.valueOf(port);
	}
	
	public Object getValue(String key) throws Exception
	{		
		MemcachedClient memcachedClient=null;
		Object result=null;
		try 
		{
			memcachedClient =this.getClient(this.serverAddress);
            result = memcachedClient.get(key);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
		   Logger.getRootLogger().error(e.getMessage());
		}
		return result;
	}
	
	public void setValue(String key,Integer expireTime,Object value) throws Exception
	{		
		MemcachedClient memcachedClient=null;
		String result;
		try 
		{
			memcachedClient =this.getClient(this.serverAddress);
            memcachedClient.set(key,expireTime,value);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
    
	public void delete(String key)
	{
		MemcachedClient memcachedClient=null;
		String result;
		try 
		{
			memcachedClient =this.getClient(this.serverAddress);
            memcachedClient.delete(key);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
	
	public void flushServer(String serverAddress,Integer port)
	{
		MemcachedClient memcachedClient=null;
		String result;
		try 
		{
			memcachedClient =this.getClient(serverAddress+":"+String.valueOf(port));
			InetSocketAddress address=new InetSocketAddress(serverAddress,port);
			memcachedClient.flushAll(address);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
	
	
	public void replace(String key,Integer expireTime,Object value)
	{
		MemcachedClient memcachedClient=null;
		String result;
		try 
		{
			memcachedClient =this.getClient(this.serverAddress);
			memcachedClient.replace(key,expireTime,value);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
	
	public void touch(String key,Integer expireTime)
	{
		MemcachedClient memcachedClient=null;
		String result;
		try 
		{
			memcachedClient =this.getClient(this.serverAddress);
			memcachedClient.touch(key,expireTime);
            shutDown(memcachedClient);
		}
		catch (Exception e)
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
	
	private MemcachedClient getClient(String serverAddress) throws Exception
	{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
		        AddrUtil.getAddresses(serverAddress));
		MemcachedClient memcachedClient = builder.build();
		return memcachedClient;
	}
	
	private void shutDown(MemcachedClient memcachedClient)
	{
		try 
		{
		    memcachedClient.shutdown();
		}
		catch (Exception e) 
		{
			Logger.getRootLogger().error(e.getMessage());
		}
	}
}
