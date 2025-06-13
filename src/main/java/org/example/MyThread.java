package org.example;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("thread running");
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
