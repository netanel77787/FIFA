package edu.netanelma.FIFA.models.firebaseplayers;

public class Player {

    private String playerName;
    private String playerLastName;
    private String club;
    private String age;
    private String id;

    public Player() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getAge() {
        return age;
    }



    public void setAge(String age) {
            this.age = age;

    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Player(String playerName, String playerLastName, String club, String age, String id) {
        this.playerName = playerName;
        this.playerLastName = playerLastName;
        this.club = club;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerLastName='" + playerLastName + '\'' +
                ", club='" + club + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
