package edu.netanelma.FIFA.models.api;

import edu.netanelma.FIFA.models.players.RecyclerResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerService {

    @GET("fifa/ultimate-team/api/fut/item")
    Call<RecyclerResponse> getPlayers();


}
