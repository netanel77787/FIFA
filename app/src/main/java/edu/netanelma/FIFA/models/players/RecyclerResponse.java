package edu.netanelma.FIFA.models.players;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecyclerResponse {

    @SerializedName("items")
    private List<RecyclerPlayer> players;

    public RecyclerResponse() {
    }

    public List<RecyclerPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<RecyclerPlayer> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "RecyclerResponse{" +
                "players=" + players +
                '}';
    }
}
