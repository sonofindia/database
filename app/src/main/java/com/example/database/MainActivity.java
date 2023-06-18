package com.example.database;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etNum, etEmail;
    private Button btnRegister, btnShow;
    private TextView tvResult;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etNum = findViewById(R.id.etNum);
        etEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btnRegister);
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult);
        databaseHelper = new DatabaseHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String number = etNum.getText().toString();
                String email = etEmail.getText().toString();
// Insert data into the database
                databaseHelper.insertData(name,number, email);
                etName.setText("");
                etNum.setText("");
                etEmail.setText("");
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Retrieve data from the database and display in TextView
                String data = databaseHelper.getData();
                tvResult.setText(data);
            }
        });
    }
}