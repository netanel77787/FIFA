package edu.netanelma.FIFA.models.players;

public class League {
    String name;

    public League() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
