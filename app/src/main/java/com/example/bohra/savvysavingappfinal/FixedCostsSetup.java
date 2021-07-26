package com.example.bohra.savvysavingappfinal;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * This class contains the necessary methods for setting the initial fixed cost setup.
 * It allows the user to input an array of fixed costs such as gym memberships ect to be used
 * at a later date by the user and by the app to show user data.
 */
public class FixedCostsSetup extends AppCompatActivity
{
    //Declaration of array-list for storing the fixed costs.
    private ArrayList<FixedPayment> fixedPayments = new ArrayList<FixedPayment>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixedcostssetup);

        //Creation of the button for the "continue to next screen" button
        Button continueButton = (Button) findViewById(R.id.continueButton);

        //Listener sends the user to the set savings activity, sends the fixed cost information to the io file
        continueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String filename = "FixedCosts.txt";
                File file = new File(Environment.getExternalStorageDirectory(),filename);
                IO io = new IO();
                String content = "";

                for(FixedPayment f:fixedPayments)
                {
                    String recurrment = f.getRecurrment().toString()+":";
                    String fixedAtegory = f.getFixedCategory().toString()+":";
                    String fixAmount = String.valueOf(f.getFixedAmount())+"\n";

                    content += recurrment += fixedAtegory +=fixAmount;
                }

                if(!file.exists()) io.writeFile(filename,content);
                else io.writeFile(filename,content);

                Intent savingSetupIntent = new Intent(getApplicationContext(), SavingSetup.class);
                startActivity(savingSetupIntent);
            }
        });

        Button addButton = (Button)findViewById(R.id.addFixedCostButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {

            /*This listener has the constraints to check the users input to ensure that it is in the correct format.
            *It then adds the information to the fixed payment array list.
            */
            @Override
            public void onClick(View v)
            {
                EditText fixedCostAmount = (EditText) findViewById(R.id.amountEditText);

                Spinner fixedPeriodSpinner = (Spinner)  findViewById(R.id.periodSpinner);
                Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

                Period fixedPeriod = Period.valueOf(fixedPeriodSpinner.getSelectedItem().toString());
                SpendingCategory chosenCategory = SpendingCategory.valueOf(categorySpinner.getSelectedItem().toString());
                String fixedString = fixedCostAmount.getText().toString();
                if (fixedString.isEmpty() == false)
                {
                    int fixedAmount = Integer.parseInt(fixedCostAmount.getText().toString());
                    FixedPayment instanceFixedPayment = new FixedPayment(fixedPeriod, chosenCategory, fixedAmount);

                    if (fixedAmount <= 0)
                    {
                        Toast invalidAmount = Toast.makeText(getApplicationContext(), "Must be a positive amount!", Toast.LENGTH_SHORT);
                        invalidAmount.show();
                    }

                    else
                        {
                        fixedPayments.add(instanceFixedPayment);
                        fixedCostAmount.setText("");
                        Toast fixedAdded = Toast.makeText(getApplicationContext(), "Your fixed payment was added", Toast.LENGTH_SHORT);
                        fixedAdded.show();
                    }
                }

                else
                    {
                    Toast invalidAmount = Toast.makeText(getApplicationContext(), "Enter an amount!", Toast.LENGTH_SHORT);
                    invalidAmount.show();
                }
            }
        });
    }
}
