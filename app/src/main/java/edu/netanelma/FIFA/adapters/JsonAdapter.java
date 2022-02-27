package edu.netanelma.FIFA.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.netanelma.FIFA.R;
import edu.netanelma.FIFA.models.players.RecyclerPlayer;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder>{

    List<RecyclerPlayer> players;

    public JsonAdapter(List<RecyclerPlayer> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_player_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerPlayer player = players.get(position);
        holder.tvFirstName.setText(player.getFirstName());
        holder.tvLastName.setText(player.getLastName());
        holder.tvAge.setText(Integer.toString(player.getAge()));
        holder.tvPosition.setText(player.getPositionFull());
        holder.tvFoot.setText(player.getFoot());
        holder.tvLeague.setText(player.getLeague().toString());
        holder.tvNation.setText(player.getNation().toString());
        holder.tvClub.setText(player.getClub().toString());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvAge;
        TextView tvPosition;
        TextView tvFoot;
        TextView tvLeague;
        TextView tvNation;
        TextView tvClub;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvFoot = itemView.findViewById(R.id.tvFoot);
            tvLeague = itemView.findViewById(R.id.tvLeague);
            tvNation = itemView.findViewById(R.id.tvNation);
            tvClub = itemView.findViewById(R.id.tvClub);


        }


    }
}
