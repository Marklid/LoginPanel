package in.careurhealth.careurhealth;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends Activity implements View.OnClickListener {

    TextView tvPatient,tvDoctor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvDoctor= (TextView) findViewById(R.id.tvDoctor);
        tvDoctor.setOnClickListener(this);

        tvPatient= (TextView) findViewById(R.id.tvPatient);
        tvPatient.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tvPatient:
                Intent i= new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(i);
                break;
            case R.id.tvDoctor:
                Intent id= new Intent(getApplicationContext(),DoctorLogin.class);
                startActivity(id);
                break;
        }


    }
}