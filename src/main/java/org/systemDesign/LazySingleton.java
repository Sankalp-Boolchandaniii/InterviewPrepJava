package org.systemDesign;

public class LazySingleton {

    private static LazySingleton singletonDemo=null;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if (singletonDemo==null){
            singletonDemo=new LazySingleton();
        }
        return singletonDemo;
    }

}
