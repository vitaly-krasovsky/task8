package com.epam.training;

/**
 * @author vkrasovsky
 */
public class App {


    /**
     * Balance calculation:
     * person-1: 100 + 30 - 30 = 100
     * person-2: 100 - 30 + 50 = 120
     * person-3: 100 - 50 + 30 = 80
     *
     * @param args the args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Person person1 = new Person("person-1", 100);
        Person person2 = new Person("person-2", 100);
        Person person3 = new Person("person-3", 100);

        Runnable transaction1 = new Transaction(person1, person2, 30);
        Runnable transaction2 = new Transaction(person2, person3, 50);
        Runnable transaction3 = new Transaction(person3, person1, 30);

        Thread thread1 = new Thread(transaction1);
        Thread thread2 = new Thread(transaction2);
        Thread thread3 = new Thread(transaction3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("name = " + person1.getName() + ", balance = " + person1.getBalance());
        System.out.println("name = " + person2.getName() + ", balance = " + person2.getBalance());
        System.out.println("name = " + person3.getName() + ", balance = " + person3.getBalance());
    }
}
