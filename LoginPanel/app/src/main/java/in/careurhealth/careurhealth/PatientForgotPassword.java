package in.careurhealth.careurhealth;

/**
 * Created by Nikant20 on 22-07-2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientForgotPassword extends AppCompatActivity implements View.OnClickListener {
    EditText etEmailId;
    Button btSubmit;
    TextInputLayout tilEmailId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_forgot_password);

        etEmailId= (EditText) findViewById(R.id.etEmailId);

        btSubmit= (Button) findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(this);

        tilEmailId= (TextInputLayout) findViewById(R.id.tilEmailId);
        etEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                validateEmail();

            }

            private void validateEmail() {

                String emailId=etEmailId.getText().toString();
                if(TextUtils.isEmpty(emailId) || !emailId.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                    tilEmailId.setError("Please enter valid email id");
                }
                else
                    tilEmailId.setErrorEnabled(false);

            }
        });

    }

    @Override
    public void onClick(View v) {

        String userName=etEmailId.getText().toString().trim();

        if (!userName.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

            Toast.makeText(getApplicationContext(),"Enter a valid email",Toast.LENGTH_SHORT).show();
        }

        else {


            AlertDialog.Builder adb = new AlertDialog.Builder(PatientForgotPassword.this);
            adb.setIcon(R.mipmap.ic_launcher);
            adb.setTitle("kindly check your email");
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getApplicationContext(), DoctorLogin.class);
                    startActivity(i);
                }
            });
            adb.show();
        }


    }
}