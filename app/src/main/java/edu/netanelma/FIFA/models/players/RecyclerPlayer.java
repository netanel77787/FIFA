package edu.netanelma.FIFA.models.players;

public class RecyclerPlayer {
        private String firstName;
        private String lastName;
        private int age;
        private String positionFull;
        private String foot;

        private League league;
        private Nation nation;
        private Club club;



        public RecyclerPlayer() {


        }

        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public int getAge() {
                return age;
        }

        public String getPositionFull() {
                return positionFull;
        }

        public String getFoot() {
                return foot;
        }

        public League getLeague() {
                return league;
        }

        public Nation getNation() {
                return nation;
        }

        public Club getClub() {
                return club;
        }


        @Override
        public String toString() {
                return "RecyclerPlayer{" +
                        "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", age=" + age +
                        ", positionFull='" + positionFull + '\'' +
                        ", foot='" + foot + '\'' +
                        ", league=" + league +
                        ", nation=" + nation +
                        ", club=" + club +
                        '}';
        }
}
