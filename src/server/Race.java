package server;
import java.util.*;

public class Race {
    private List<Turtle> turtles;

    public Race(List<Turtle> turtles) {
        this.turtles = turtles;
    }

    public void addTurtle(Turtle turtle) {
        this.turtles.add(turtle);
    }

    public void removeTurtle(int position) {
        this.turtles.remove(position -1);
    }

    public List<Turtle> getTurtles() {
        return this.turtles;
    }
}

