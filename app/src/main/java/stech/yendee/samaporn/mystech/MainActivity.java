package stech.yendee.samaporn.mystech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString, truepasswordString;
    private Boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View  การผูกตัวแปรกับ view ที่อยู่ใน Activity
        initialView();

        //Create Controller
        controller();


    }    // Main Method

    private void controller() {
        textView.setOnClickListener(MainActivity.this);
        button.setOnClickListener(MainActivity.this);

    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtRegister);
        button = (Button) findViewById(R.id.btnLogin);

    }


    @Override
    public void onClick(View v) {


        String tag = "SriwattanaV1";

        // For TextView   ทำเงื่อนไข
        if (v == textView) {
            Log.d(tag, "You Click TextView");

            //Create Intent  สืบทอด class
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);


        }

        //For Button  You click button
        if (v == button) {
            Log.d(tag, "You click Button");

            // Get Value From EditText
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //Check Space
            if (userString.equals("") || passwordString.equals("")) {

                //Have Space
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Have Space", "Please Fill All");
            } else {
                //No Space
                checkUserAnPass();
            }


        }


    }   //onClick

    private void checkUserAnPass() {
        try {
            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute();
            String strJson = getUser.get();
            Log.d("TestV2", "Json ==>" + strJson);

            //Check User
            JSONArray jsonArray = new JSONArray(strJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    aBoolean = false;
                    truepasswordString = jsonObject.getString("Password");


                }

            } //for Loop

            if (aBoolean) {
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Use False", "No this User on Database");

            } else if (!(passwordString.equals(truepasswordString))) {
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Password Fallse", "Please Try Again");

            } else {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
                finish();


            }


        } catch (Exception e) {
            Log.d("TestV2", "e check ==>" + e.toString());

        }
    }

}   // Main class นี่คือ class หลัก
