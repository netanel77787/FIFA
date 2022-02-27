package edu.netanelma.FIFA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.netanelma.FIFA.dismisskeyboard.DismissKeyboard;

public class RegisterActivity extends AppCompatActivity {
    private ConstraintLayout clRegister;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private EditText etUsername;
    private Button btnRegister;
    private Button btnLogin;
    private ConstraintLayout clKeyboard;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        clRegister = findViewById(R.id.clRegister);
        clKeyboard = findViewById(R.id.clKeyboard);


        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnRegister = findViewById(R.id.btnRegister);



        clKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DismissKeyboard.hideSoftKeyboard(RegisterActivity.this);

            }
        });


        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        });


        btnRegister.setOnClickListener(view -> {
            if (!isEmailValid() | !isPasswordValid() | !isPhoneNumberValid() | !isUsernameValid()) {
                return;
            }

            toggleProgressDialog(true);


            mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                    .addOnSuccessListener(this, authResult -> {
                        succeeded();
                    })
                    .addOnFailureListener(this, e -> {
                        failed(e);
                    });

        });

    }

    public void failed(Exception e) {
        toggleProgressDialog(false);
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void succeeded() {
        toggleProgressDialog(false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public String getUsername() {
        return etUsername.getText().toString();
    }

    public String getPhoneNumber() {
        return etPhoneNumber.getText().toString();
    }

    public String getEmail() {
        return etEmail.getText().toString();

    }

    public String getPassword() {
        return etPassword.getText().toString();
    }

    public boolean isUsernameValid() {
        boolean isValid = getUsername().length() > 1;
        if (!isValid) {
            etUsername.setError("Username must contain more characters (at least 2)");
        }
        return isValid;
    }

    public boolean isPhoneNumberValid() {
        boolean isValid = Patterns.PHONE.matcher(getPhoneNumber()).matches();
        if (!isValid) {
            etPhoneNumber.setError("The phone number is not valid");
        }
        return isValid;
    }

    public boolean isEmailValid() {
        boolean isValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
        if (!isValid) {
            etEmail.setError("The email is not valid");
        }
        return isValid;
    }

    private boolean isPasswordValid() {
        boolean isValid = getPassword().length() > 5;
        if (!isValid) {
            etPassword.setError("Password must contain more characters (at least 6)");
        }
        return isValid;
    }


    public ProgressDialog progressDialog;

    public void toggleProgressDialog(boolean shouldShow) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Connecting to the application");
        }
        if (shouldShow)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }
}