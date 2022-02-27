package edu.netanelma.FIFA.ui.newplayers;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.netanelma.FIFA.models.firebaseplayers.Player;

public class PlayersViewModel extends ViewModel {

    MutableLiveData<List<Player>> playersList = new MutableLiveData<>();

    public PlayersViewModel() {
      getPlayers();
    }

    public MutableLiveData<List<Player>> getPlayersList() {
        return playersList;
    }

    public void getPlayers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("NewPlayers");
        ArrayList<Player> list = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot row : snapshot.getChildren()) {
                    Player player = row.getValue(Player.class);

                    list.add(player);
                }
                playersList.postValue(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}

