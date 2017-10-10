package com.example.admin.taarangana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView user_token;
    EditText usernameField,passwordField,mobile;
    TextView status;
    CheckBox robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_token=(TextView)findViewById(R.id.usertoken);
        usernameField=(EditText)findViewById(R.id.username);
        passwordField=(EditText)findViewById(R.id.password);
        mobile=(EditText)findViewById(R.id.mobile);
        robot=(CheckBox)findViewById(R.id.checkBox);

        status = (TextView)findViewById(R.id.textView6);
        loadSavedPreferences();
robot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            saveData();
            Intent i=new Intent();
            Bundle extras=new Bundle();

            i.setClass(Login.this,Game.class);

          //  extras.putString("name",name.getText().toString());;

            startActivity(i);

        }
        else
        {
            Log.e("without if","jjjj");
        }

    }
});
    }
    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        //method.setText("Get Method");
        new SigninActivity(this,status,0).execute(username,password);

    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        //method.setText("Post Method");
        new SigninActivity(this,status,1).execute(username,password);

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
    private void loadSavedPreferences() {
        boolean set=getIntent().getBooleanExtra("pass",false);
        robot.setChecked(set);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        usernameField.setText(sharedPreferences.getString("name",""));
        passwordField.setText(sharedPreferences.getString("email",""));
        mobile.setText(sharedPreferences.getString("mobile",""));
        sharedPreferences.edit().clear().commit();

    }
    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void saveData(){
        savePreferences("name", usernameField.getText().toString());
        savePreferences("email", passwordField.getText().toString());
        savePreferences("mobile", mobile.getText().toString());
    }
}
