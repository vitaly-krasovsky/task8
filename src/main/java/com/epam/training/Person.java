package com.epam.training;

import com.google.common.util.concurrent.AtomicDouble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents person. Commented lines -> deadlock.
 *
 * @author vkrasovsky
 */
public class Person {
    private String name;
    //    private double amount;
    private AtomicDouble amount;
    private static final Logger LOGGER = LogManager.getLogger(Person.class);

    public Person(String name, double amount) {
        this.name = name;
//        this.amount = amount;
        this.amount = new AtomicDouble(amount);
    }

    public /*synchronized*/ void add(double amount, Person person) {
        LOGGER.debug("[" + Thread.currentThread().getName() + "]: " + this.getName() + " (+) " + amount);
//        this.amount = this.amount + amount;

        double v;
        do {
            v = this.amount.get();
        }
        while (!this.amount.compareAndSet(v, v + amount));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.debug(e.getMessage());
        }
        person.sub(amount, person);
    }

    public /*synchronized*/ void sub(double amount, Person person) {
        LOGGER.debug("[" + Thread.currentThread().getName() + "]: " + person.getName() + " (-) " + amount);
//        this.amount = this.amount - amount;

        double v;
        do {
            v = this.amount.get();
        }
        while (!this.amount.compareAndSet(v, v - amount));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.debug(e.getMessage());
        }
    }

    public double getBalance() {
        return amount.get();
//        return amount;
    }

    public String getName() {
        return name;
    }
}
