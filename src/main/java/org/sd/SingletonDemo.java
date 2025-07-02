package org.sd;

public class SingletonDemo {

    private static SingletonDemo singletonDemo=null;

    private SingletonDemo(){}

    public static SingletonDemo getInstance(){
        if (singletonDemo==null){
            singletonDemo=new SingletonDemo();
        }
        return singletonDemo;
    }

}
