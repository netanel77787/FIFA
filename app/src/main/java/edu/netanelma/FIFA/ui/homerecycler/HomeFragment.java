package edu.netanelma.FIFA.ui.homerecycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.widget.PullRefreshLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.netanelma.FIFA.R;
import edu.netanelma.FIFA.adapters.JsonAdapter;
import edu.netanelma.FIFA.models.players.RecyclerPlayer;

public class HomeFragment extends Fragment {

    RecyclerView rvPlayers;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        PullRefreshLayout layout = (PullRefreshLayout) view.findViewById(R.id.homeRefreshLayout);

        layout.setOnRefreshListener(() -> new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                layout.setRefreshing(false);
            }
        },500));



        homeViewModel.getPlayersLiveData().observe(getViewLifecycleOwner(), new Observer<List<RecyclerPlayer>>() {
            @Override
            public void onChanged(List<RecyclerPlayer> recyclerPlayers) {
                rvPlayers = view.findViewById(R.id.rvPlayers);
                rvPlayers.setLayoutManager(new LinearLayoutManager(getContext()));
                rvPlayers.setAdapter(new JsonAdapter(recyclerPlayers));
            }
        });

        homeViewModel.getExcLiveData().observe(getViewLifecycleOwner() ,exc ->{
            Toast.makeText(getContext(),exc.getMessage(),Toast.LENGTH_LONG).show();
        });
        return view;
    }
}
