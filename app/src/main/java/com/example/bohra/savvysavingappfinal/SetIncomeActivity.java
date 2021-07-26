package com.example.bohra.savvysavingappfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This class contains the methods that are used for the setting of the users income.
 * This information is then used at a later date for viewing the users income as well as
 * users being able to append to their already inputted income so it can add to the text file
 * to be viewed by the user.
 */
public class SetIncomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_set_activity);

        //Creating the confirmation button for setting the income of the user.
        Button confirm3 = (Button) findViewById(R.id.confirmIncomeButton);

         /*
         When the button is clicked, it runs a click listener method which then encrypts the given input
         *by the user in the text field, and saves it to the finance.txt file.
         */
         confirm3.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View v)
            {
                EditText incomeEdit = (EditText) findViewById(R.id.incomeEditText);
                String incomeText = incomeEdit.getText().toString();

                /*
                *Checking to see whether the input field is empty to then throw an error message prompting
                *the user to input their income.
                */
                if(incomeText.isEmpty() == false){
                    int income = Integer.parseInt(incomeEdit.getText().toString());

                    Spinner incomePeriodSpinner = (Spinner)  findViewById(R.id.incomePeriodSpinner);
                    Period incomePeriod = Period.valueOf(incomePeriodSpinner.getSelectedItem().toString());


                    if ((income == 0) || (incomeEdit.getText().equals("")))
                    {
                        Toast budgetHighToast = Toast.makeText(getApplicationContext(), "You must enter your income!", Toast.LENGTH_SHORT);
                        budgetHighToast.show();
                    }
                    //Else send the income, budget and the income period to the finance text file.
                    else
                    {
                        IO io = new IO();

                        //Add all of the variables together to append to the finance text file.
                        String str = "income:"+income+"\n"+
                                "incomePeriod:"+incomePeriodSpinner.getSelectedItem().toString()+"\n";
                        io.writeFile("finance.txt",str);

                        Intent fixedCostsSetupIntent = new Intent(getApplicationContext(), FixedCostsSetup.class);
                        startActivity(fixedCostsSetupIntent);
                    }
                }
                else
                    {
                    Toast budgetHighToast = Toast.makeText(getApplicationContext(), "You must enter your income!", Toast.LENGTH_SHORT);
                    budgetHighToast.show();
                }
            }
        });
    }
}