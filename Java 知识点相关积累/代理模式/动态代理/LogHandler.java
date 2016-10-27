package com.bjpowernode.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	
	//目标类----被代理的对象，在调用时需要传过来
	private Object targetObject;
	
	//创建代理类，参数为被代理对象
	public Object newProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		//返回代理类,参数分别是被代理的对象的类装载器，被代理对象实现的接口，还有这个类（LogHandler）
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
							   targetObject.getClass().getInterfaces(), this);
	}
	
	@Override
	//JDK内部生成的代理类调用的方法，proxy为代理类的实例，method为目标方法，args为目标方法的参数
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("start-->>" + method.getName());
		for (int i=0; i<args.length; i++) {
			System.out.println(args[i]);
		}
		Object ret = null;
		try {
			//调用目标方法，可以再调用目标方法之前写上自己需要的逻辑
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
