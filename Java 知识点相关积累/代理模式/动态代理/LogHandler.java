package com.bjpowernode.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	
	//Ŀ����----������Ķ����ڵ���ʱ��Ҫ������
	private Object targetObject;
	
	//���������࣬����Ϊ���������
	public Object newProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		//���ش�����,�����ֱ��Ǳ�����Ķ������װ���������������ʵ�ֵĽӿڣ���������ࣨLogHandler��
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
							   targetObject.getClass().getInterfaces(), this);
	}
	
	@Override
	//JDK�ڲ����ɵĴ�������õķ�����proxyΪ�������ʵ����methodΪĿ�귽����argsΪĿ�귽���Ĳ���
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("start-->>" + method.getName());
		for (int i=0; i<args.length; i++) {
			System.out.println(args[i]);
		}
		Object ret = null;
		try {
			//����Ŀ�귽���������ٵ���Ŀ�귽��֮ǰд���Լ���Ҫ���߼�
			ret = method.invoke(targetObject, args);
			System.out.println("success-->>" + method.getName()); 
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("error-->>" + method.getName());
			throw e;
		}
		return ret;
	}

}
