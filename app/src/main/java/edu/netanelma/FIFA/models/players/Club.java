package edu.netanelma.FIFA.models.players;

public class Club {
    String name;

    public Club() {

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
