package com.epam.training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents transaction between two persons.
 *
 * @author vkrasovsky
 */
public class Transaction implements Runnable {
    private Person person1;
    private Person person2;
    private double amount;
    private static final Logger LOGGER = LogManager.getLogger(Transaction.class);

    public Transaction(Person person1, Person person2, double amount) {
        this.person1 = person1;
        this.person2 = person2;
        this.amount = amount;
    }

    @Override
    public void run() {
        LOGGER.debug("[" + Thread.currentThread().getName() + "]: " + "Start transaction between " + person1.getName() + " and " + person2.getName());
        person1.add(amount, person2);
//        person2.sub(amount);
    }
}
