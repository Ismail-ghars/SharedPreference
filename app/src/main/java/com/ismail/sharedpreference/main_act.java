package com.example.sharedpreference;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreference.R;

public class main_act extends AppCompatActivity {
    private SharedPreferences shPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText user,password;
    private Button btn_send;
    CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        box=(CheckBox)findViewById(R.id.checkbox);
        btn_send=(Button)findViewById(R.id.btn_send);
        shPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=shPreferences.edit();
        shared();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onClick(View v) {
                if(box.isChecked()){
                    mEditor.putString(getString(R.string.checkbox),"true");
                    mEditor.commit();
                    String name=user.getText().toString();
                    mEditor.putString(getString(R.string.name),name);
                    mEditor.commit();
                    String pass=password.getText().toString();
                    mEditor.putString(getString(R.string.password),pass);
                    Toast.makeText(getApplicationContext(), "Vos info à été enregistrer", Toast.LENGTH_SHORT).show();
                    mEditor.commit();
                }
                else
                {
                    mEditor.putString(getString(R.string.checkbox),"false");

                    mEditor.putString(getString(R.string.name),"");
                    mEditor.commit();
                    mEditor.putString(getString(R.string.password),"");
                    mEditor.commit();

                }
            }
        });

    }
    private void  shared(){
        String chek=shPreferences.getString(getString(R.string.checkbox),"false");
        String name=shPreferences.getString(getString(R.string.name),"");
        String pass=shPreferences.getString(getString(R.string.password),"");
        user.setText(name);
        password.setText(pass);
        if(chek.equals("true")){
            box.setChecked(true);

        }
        else{
            box.setChecked(false);
        }
    }
}
