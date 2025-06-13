package org.example;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("runnable thread");
    }

    public static void main(String[] args) {
        Thread thread=new Thread(new MyRunnable());
        thread.start();
    }
}
