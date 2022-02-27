package edu.netanelma.FIFA.ui.homerecycler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.netanelma.FIFA.models.api.RecyclerAPIManager;
import edu.netanelma.FIFA.models.players.RecyclerPlayer;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<RecyclerPlayer>> playersLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> excLiveData = new MutableLiveData<>();


    // constructor
    public HomeViewModel() {
        RecyclerAPIManager manager = new RecyclerAPIManager();
        manager.getAllPlayers(playersLiveData,excLiveData);
    }

    // getters
    public LiveData<List<RecyclerPlayer>> getPlayersLiveData() {
        return playersLiveData;
    }
    public LiveData<Throwable> getExcLiveData() {
        return excLiveData;
    }

}