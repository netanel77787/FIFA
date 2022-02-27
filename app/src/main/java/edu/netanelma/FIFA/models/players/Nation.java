package edu.netanelma.FIFA.models.players;

public class Nation {
    String name;

    public Nation() {

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
