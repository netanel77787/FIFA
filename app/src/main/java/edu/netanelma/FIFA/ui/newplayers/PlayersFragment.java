package edu.netanelma.FIFA.ui.newplayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import edu.netanelma.FIFA.adapters.FirebaseRecyclerAdapter;
import edu.netanelma.FIFA.models.firebaseplayers.Player;

public class PlayersFragment extends Fragment {
   RecyclerView rvNewPlayers;

    private PlayersViewModel playersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_players, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playersViewModel =
                new ViewModelProvider(this).get(PlayersViewModel.class);
        rvNewPlayers = view.findViewById(R.id.rvNewPlayers);

        PullRefreshLayout layout = (PullRefreshLayout) view.findViewById(R.id.playersRefreshLayout);

        layout.setOnRefreshListener(() -> new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                layout.setRefreshing(false);
            }
        },500));

        playersViewModel.getPlayersList().observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
            // Insert a new adapter with "players"
                rvNewPlayers = view.findViewById(R.id.rvNewPlayers);
                rvNewPlayers.setLayoutManager(new LinearLayoutManager(getContext()));
                rvNewPlayers.setAdapter(new FirebaseRecyclerAdapter(players));


            }
        });


    }
}