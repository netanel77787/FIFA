package edu.netanelma.FIFA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.netanelma.FIFA.dismisskeyboard.DismissKeyboard;


public class ForgotFragment extends Fragment {

    TextView tvForgot;

    public ForgotFragment() {
        // Required empty public constructor
    }

    public static ForgotFragment newInstance() {
        return new ForgotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null) {
                    DismissKeyboard.hideSoftKeyboard(getActivity());
                }
            }
        });

        tvForgot = view.findViewById(R.id.tvForgot);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            tvForgot.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }


}