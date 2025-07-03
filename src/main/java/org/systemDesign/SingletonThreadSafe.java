package org.systemDesign;

public class SingletonThreadSafe {

    private static SingletonThreadSafe singletonThreadSafe=null;

    private SingletonThreadSafe(){}

    public static SingletonThreadSafe getInstance(){
        if (singletonThreadSafe==null){
            synchronized (SingletonThreadSafe.class){
                if (singletonThreadSafe==null) {
                    singletonThreadSafe = new SingletonThreadSafe();
                }
            }
        }
        return singletonThreadSafe;
    }

}
