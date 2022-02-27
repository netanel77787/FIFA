package edu.netanelma.FIFA.ui.addplayers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.netanelma.FIFA.R;
import edu.netanelma.FIFA.dismisskeyboard.DismissKeyboard;

public class AddPlayersFragment extends Fragment {
    //properties:
    private AddPlayersViewModel mViewModel;
    private EditText etAddName;
    private EditText etAddLastName;
    private EditText etAddClub;
    private EditText etAddAge;
    private Button btnAddPlayer;
    private ProgressDialog progressDialog;


    public void toggleProgressDialog(boolean shouldShow) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Adding player");
        }
        if (shouldShow)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_players, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AddPlayersViewModel.class);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    DismissKeyboard.hideSoftKeyboard(getActivity());
                }
            }
        });

        etAddName = view.findViewById(R.id.etAddName);
        etAddLastName = view.findViewById(R.id.etAddLastName);
        etAddClub = view.findViewById(R.id.etAddClub);
        etAddAge = view.findViewById(R.id.etAddAge);
        btnAddPlayer = view.findViewById(R.id.btnAddPlayer);

        btnAddPlayer.setOnClickListener(v -> {
            String name = etAddName.getText() == null ? "" : etAddName.getText().toString();
            String lastName = etAddLastName.getText() == null ? "" : etAddLastName.getText().toString();
            String club = etAddClub.getText() == null ? "" : etAddClub.getText().toString();

            String age = etAddAge.getText() == null ? "" : etAddAge.getText().toString();


            if (!name.isEmpty() && !lastName.isEmpty() && !club.isEmpty() && !age.isEmpty()) {

                if (Integer.parseInt(age) < 18){
                    etAddAge.setError("Only players aged 18+ can be added");
                    return;
                }
                mViewModel.addPlayer(name, lastName, club, age);
                toggleProgressDialog(true);
            } else {
                if (age.isEmpty() || Integer.parseInt(age) < 18) {
                    etAddAge.setError("Only players aged 18+ can be added");

                }

                if (name.isEmpty()) {
                    etAddName.setError("Name cannot be empty");

                }
                if (lastName.isEmpty()) {
                    etAddLastName.setError("Last name cannot be empty");

                }
                if (club.isEmpty()) {
                    etAddClub.setError("Player's club cannot be empty");

                }


                Toast.makeText(getContext(), "Please fill all the fields"
                        , Toast.LENGTH_LONG).show();
            }
        });


        mViewModel.isDone.observe(getViewLifecycleOwner(), isDone -> {

            etAddName.setText(null);
            etAddLastName.setText(null);
            etAddClub.setText(null);
            etAddAge.setText(null);

            toggleProgressDialog(false);


            Toast.makeText(getContext(), "Player Added", Toast.LENGTH_SHORT).show();
        });
    }
}


