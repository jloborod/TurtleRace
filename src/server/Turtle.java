package server;

import java.util.Random;

public class Turtle implements Runnable {
    private final int GOAL = 500;
    private final int MAX_STEP = 50;
    private String name;
    private String dorsal;
    private int distance;

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
        this.distance = 0;
        String name = Thread.currentThread().getName();

        System.out.println(String.format("Thread started:::%s", name));
        while(this.distance < GOAL) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.distance += getRandomInt(0, MAX_STEP);
            System.out.println(String.format("%s -> %s", name, this.distance));
        }

        System.out.println(String.format("Thread terminated:::%s", Thread.currentThread().getName()));
    }
}
