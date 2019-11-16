package server;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Race {
    private final int GOAL = 500;
    private ArrayList<Turtle> turtles;

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

    public void startRace() {
        // Create an array of Threads
        ArrayList<Thread> turtlesRunning = (ArrayList<Thread>) this.turtles
                .stream()
                .map(turtle -> new Thread(turtle, turtle.getName()))
                .collect(Collectors.toList());

        // Start each thread
        turtlesRunning.stream().forEach(t -> {
            t.start();
        });

        // Join each thread
        turtlesRunning.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

