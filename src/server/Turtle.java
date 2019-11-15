package server;

public class Turtle {
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
}
