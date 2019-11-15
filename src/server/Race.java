package server;
import java.util.*;

public class Race {
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

    public Integer getTurtles() {
        return this.turtles.size();
    }
}

