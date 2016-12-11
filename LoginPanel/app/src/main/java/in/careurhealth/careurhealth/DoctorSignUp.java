package in.careurhealth.careurhealth;

/**
 * Created by Nikant20 on 21-07-2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class DoctorSignUp extends AppCompatActivity implements View.OnClickListener {

    TextView tvSubmit;
    EditText etEmailId, etPassword, etCPassword, etMobile;
    FirebaseAuth firebaseAuth;
    TextInputLayout tilEmailId, tilPassword, tilCPassword, tilMobile;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        //initialize view controls

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        etEmailId = (EditText) findViewById(R.id.etEmailId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etCPassword = (EditText) findViewById(R.id.etCPassword);
        etMobile = (EditText) findViewById(R.id.etMobile);

        tvSubmit = (TextView) findViewById(R.id.tvSubmit);

        tilEmailId = (TextInputLayout) findViewById(R.id.tilEmailId);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilCPassword = (TextInputLayout) findViewById(R.id.tilCPassword);
        tilMobile = (TextInputLayout) findViewById(R.id.tilMobile);

        //event handling

        tvSubmit.setOnClickListener(this);


        etEmailId.addTextChangedListener(new MyTextWatcher(etEmailId));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));
        etCPassword.addTextChangedListener(new MyTextWatcher(etCPassword));
        etMobile.addTextChangedListener(new MyTextWatcher(etMobile));
    }

    @Override
    public void onClick(View v) {

        registerUser();

    }

    private void registerUser() {

        String email = etEmailId.getText().toString();
        String password = etPassword.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User,Please wait..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                } else

                {
                    Toast.makeText(getApplicationContext(), "Failed To Register The User", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {

                case R.id.etEmailId:
                    validateEmail();
                    break;
                case R.id.etPassword:
                    validatePassword();
                    break;
                case R.id.etCPassword:
                    validateConformPassword();
                    break;
                case R.id.etMobile:
                    validateMobile();
                    break;

            }


        }

        private void validateConformPassword() {

            String password = etPassword.getText().toString();
            String cpassword = etCPassword.getText().toString();

            if (!password.equalsIgnoreCase(cpassword)) {
                tilCPassword.setError("Please enter the same password again");
            } else
                tilCPassword.setErrorEnabled(false);


        }


        private void validateMobile() {
            String mobile = etMobile.getText().toString();

            if (mobile.length() < 10) {
                tilMobile.setError("Enter a valid mobile no.");
            } else
                tilMobile.setErrorEnabled(false);
        }

        private void validatePassword() {

            String password = etPassword.getText().toString();

            if (password.length() < 6) {
                tilPassword.setError("Password must be of 6 characters atleast");
            } else
                tilPassword.setErrorEnabled(false);
        }

        private void validateEmail() {

            String email = etEmailId.getText().toString();


            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                tilEmailId.setError("Please enter valid email id");
            } else
                tilEmailId.setErrorEnabled(false);
        }
    }


}
