package edu.netanelma.FIFA.ui.addplayers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.netanelma.FIFA.models.firebaseplayers.Player;

public class AddPlayersViewModel extends ViewModel {

     MutableLiveData<Boolean> isDone = new MutableLiveData<>();

    public void addPlayer(String playerName, String playerLastName, String club, String age) {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("NewPlayers");

        DatabaseReference reference = dbRef.push();

        String id = reference.getKey();

        Player player = new Player(playerName, playerLastName, club, age, id);

        reference.setValue(player).addOnSuccessListener(done -> {
            isDone.postValue(true);
        });

    }

}