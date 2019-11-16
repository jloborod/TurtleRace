package server;

import java.util.Observable;
import java.util.Random;

public class Turtle extends Observable implements Runnable {
    private final int MAX_STEP = 10;
    private String name;
    private String dorsal;

    public Turtle(String name, String dorsal) {
        this.name = name;
        this.dorsal = dorsal;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String toString() {
        return String.format("Name: %s and dorsal: %s", this.name, this.dorsal);
    }

    private static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {
        int distance = 0;
        String name = Thread.currentThread().getName();

        while(!Thread.currentThread().isInterrupted()) {
            distance += getRandomInt(0, MAX_STEP);

            this.setChanged();
            this.notifyObservers(distance);
            this.clearChanged();
        }
    }
}
