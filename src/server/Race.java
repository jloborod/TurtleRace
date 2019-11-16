package server;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Race implements Observer {
    private final int GOAL = 500;
    private ArrayList<Turtle> turtles;
    private ArrayList<Thread> turtlesRunning;
    private Turtle winner;

    public Race() {
        this.turtles = new ArrayList<Turtle>();
    }

    public Race(ArrayList<Turtle> turtles) {
        this.turtles = turtles;
    }

    public void addTurtle(Turtle turtle) {
        this.turtles.add(turtle);
    }

    public void removeTurtle(int position) {
        this.turtles.remove(position -1);
    }

    public String getTurtles() {
        AtomicInteger index = new AtomicInteger(1);
        String title = "Turtle List:";
        String separator = "\n---------------------------------\n";
        String turtlesString = this.turtles
                .stream()
                .map(turtle -> String.format("Turtle %s: %s", index.getAndIncrement(), turtle.toString()))
                .collect(Collectors.joining("\n"));

        return title + separator + turtlesString + separator;
    }

    public String startRace() {
        this.winner = null;

        // Add Observer into turtles
        this.turtles.stream().forEach(turtle -> {
            turtle.addObserver(this);
        });

        // Create an array of Threads
        this.turtlesRunning = (ArrayList<Thread>) this.turtles
                .stream()
                .map(turtle -> new Thread(turtle, turtle.getName()))
                .collect(Collectors.toList());

        // Start each thread
        this.turtlesRunning.stream().forEach(t -> {
            System.out.println(String.format("Starting race for %s", t.getName()));
            t.start();
        });

        // Join each thread
        this.turtlesRunning.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return String.format("The winner is: %s", this.winner.toString());
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.winner = (Turtle) observable;

        // Interrupt other threads
        this.turtlesRunning.stream().forEach(turtle -> turtle.interrupt());
    }
}

