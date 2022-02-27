package edu.netanelma.FIFA.models.api;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.netanelma.FIFA.models.players.RecyclerPlayer;
import edu.netanelma.FIFA.models.players.RecyclerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerAPIManager {
    public Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.easports.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public RecyclerService service = retrofit.create(RecyclerService.class);

    public RecyclerAPIManager() {

    }

    public void getAllPlayers(MutableLiveData<List<RecyclerPlayer>> mutableLiveData ,MutableLiveData<Throwable> excLiveData){

        service.getPlayers().enqueue(new Callback<RecyclerResponse>() {
            @Override
            public void onResponse(Call<RecyclerResponse> call, Response<RecyclerResponse> response) {
                if (response.isSuccessful()){
                    RecyclerResponse recyclerResponse = response.body();
                    if (recyclerResponse != null){
                        List<RecyclerPlayer> allPlayers = recyclerResponse.getPlayers();
                        mutableLiveData.postValue(allPlayers);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecyclerResponse> call, Throwable t) {
                    excLiveData.postValue(t);
            }
        });
    }
}
