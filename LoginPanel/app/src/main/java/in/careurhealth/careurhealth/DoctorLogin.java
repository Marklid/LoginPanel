package in.careurhealth.careurhealth;

/**
 * Created by Nikant20 on 21-07-2016.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class DoctorLogin extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName,etPassword;
    TextInputLayout tilUserName,tilPassword;
    Button btLogin;
    TextView tvForgotPassword,tvSignUp;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

         /*initializing view controls*/
        intializeViewControls();

        //handling events

        eventHandling();




    }

    private void eventHandling() {

        btLogin.setOnClickListener(this);

        tvSignUp.setOnClickListener(this);

        tvForgotPassword.setOnClickListener(this);


        etUserName.addTextChangedListener(new MyTextWatcher(etUserName));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));

    }

    private void intializeViewControls() {

        etUserName= (EditText) findViewById(R.id.etUserName);
        etPassword= (EditText) findViewById(R.id.etPassword);

        btLogin= (Button) findViewById(R.id.btLogin);

        tvSignUp= (TextView) findViewById(R.id.tvSignUp);
        tvForgotPassword= (TextView) findViewById(R.id.tvForgotPassword);

        tilUserName= (TextInputLayout) findViewById(R.id.tilUserName);
        tilPassword= (TextInputLayout) findViewById(R.id.tilPassword);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btLogin:
                Intent i = new Intent(getApplicationContext(), DoctorView.class);
                startActivity(i);
                break;

            case R.id.tvForgotPassword:
                Intent in = new Intent(getApplicationContext(), DoctorForgotPassword.class);
                startActivity(in);
                break;


            case R.id.tvSignUp:
                Intent sp = new Intent(getApplicationContext(),DoctorSignUp.class);
                startActivity(sp);
                break;
        }
    }




    public  class MyTextWatcher implements TextWatcher{
            private View view;
            public MyTextWatcher(View view){
                this.view=view;
            }


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String username=etUserName.getText().toString();
                String password=etPassword.getText().toString();

                switch (view.getId()){

                    case R.id.etUserName: {
                        if (TextUtils.isEmpty(username) || !username.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                            tilUserName.setError("Enter Valid Email id");

                        } else {
                            tilUserName.setErrorEnabled(false);
                        }
                    }
                    break;

                    case R.id.etPassword: {
                        if (TextUtils.isEmpty(password) ||  password.length()<6) {
                            tilPassword.setError("Enter valid password ");

                        } else {
                            tilPassword.setErrorEnabled(false);
                        }
                    }
                    break;




                }



            }
        }
    }

