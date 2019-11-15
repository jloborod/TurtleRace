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
}

