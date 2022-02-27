package edu.netanelma.FIFA;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.netanelma.FIFA.dismisskeyboard.DismissKeyboard;


public class LoginFragment extends Fragment {
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private Button btnActivityLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Button btnForgotEmail;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLoginEmail = view.findViewById(R.id.etLoginEmail);
        etLoginPassword = view.findViewById(R.id.etLoginPassword);
        btnActivityLogin = view.findViewById(R.id.btnActivityLogin);

        btnForgotEmail = view.findViewById(R.id.btnForgotEmail);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null) {
                    DismissKeyboard.hideSoftKeyboard(getActivity());
                }
            }
        });


        btnActivityLogin.setOnClickListener(v -> {
            if (!isEmailValid() | !isPasswordValid()) {
                return;
            }

            toggleProgressDialog(true);


            mAuth.signInWithEmailAndPassword(getEmail(), getPassword())
                    .addOnSuccessListener(authResult ->
                            succeeded())
                    .addOnFailureListener(e ->
                            failed(e));

        });


        btnForgotEmail.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.logintoForget);
        });
    }


    public void failed(Exception e) {
        toggleProgressDialog(false);
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void succeeded() {
        toggleProgressDialog(false);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }

    public String getEmail() {
        return etLoginEmail.getText().toString();

    }

    public String getPassword() {
        return etLoginPassword.getText().toString();
    }


    public boolean isEmailValid() {
        boolean isValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
        if (!isValid) {
            etLoginEmail.setError("The email is not valid");
        }
        return isValid;
    }

    private boolean isPasswordValid() {
        boolean isValid = getPassword().length() > 5;
        if (!isValid) {
            etLoginPassword.setError("The password is not valid");
        }
        return isValid;
    }


    public void toggleProgressDialog(boolean shouldShow) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Connecting to the application");
        }
        if (shouldShow)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}

