package edu.netanelma.FIFA.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.netanelma.FIFA.R;
import edu.netanelma.FIFA.models.firebaseplayers.Player;

public class FirebaseRecyclerAdapter extends RecyclerView.Adapter<FirebaseRecyclerAdapter.ViewHolder> {

    private List<Player> newPlayerList;

    public FirebaseRecyclerAdapter(List<Player> newPlayerList) {
        this.newPlayerList = newPlayerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.newplayer_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = newPlayerList.get(position);
        holder.tvNewName.setText(player.getPlayerName());
        holder.tvNewLastName.setText(player.getPlayerLastName());
        holder.tvNewClub.setText(player.getClub());
        holder.tvNewAge.setText((player.getAge()));

    }

    @Override
    public int getItemCount() {
        return newPlayerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewName;
        TextView tvNewLastName;
        TextView tvNewClub;
        TextView tvNewAge;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewName = itemView.findViewById(R.id.tvNewName);
            tvNewLastName = itemView.findViewById(R.id.tvNewLastName);
            tvNewClub = itemView.findViewById(R.id.tvNewClub);
            tvNewAge = itemView.findViewById(R.id.tvNewAge);

        }

    }
}
