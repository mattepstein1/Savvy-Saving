package com.example.bohra.savvysavingappfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is for the creation of the pin screen where the user sets up the pin to be later used
 * by the application and by the user so that they can log into the application when closed.
 */

public class CreatePinActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpin);

        //Declaration of the confirm button to be displayed on the setup pin screen.
        final Button confirmButton2 = (Button) findViewById(R.id.ConfirmPinCreatebutton);
        final EditText enterPinText = (EditText) findViewById(R.id.enterPinText);
        final IO io = new IO();

        /*
         When the button is clicked, it runs a click listener method which then encrypts the given input
         *by the user in the text field, and saves it to the pin.txt file.
         */
        confirmButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String pinChecker = enterPinText.getText().toString();
                if(pinChecker.isEmpty() == false){

                    Integer pinToSend = Integer.parseInt(enterPinText.getText().toString());
                    //Condition handling for checking the pin that was entered in the setup process is valid and of the correct format
                    if((!enterPinText.getText().toString().equals("")) && (enterPinText.getText().toString().length() == 4))
                    {
                        pinToSend *= 11;
                        io.writeFile("pin.txt",pinToSend.toString());
                        Intent incomeSetIntent = new Intent(getApplicationContext(), SetIncomeActivity.class);
                        startActivity(incomeSetIntent);
                    }
                    //If the pin is the incorrect format, then it will display an error message asking for a valid pin
                    else {
                        Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a valid 4 Digit PIN!", Toast.LENGTH_SHORT);
                        notToast.show();
                    }
                }
                else{
                    Toast invalidToast = Toast.makeText(getApplicationContext(), "Please enter a valid PIN!", Toast.LENGTH_SHORT);
                    invalidToast.show();
                }
            }
        });


    }

}