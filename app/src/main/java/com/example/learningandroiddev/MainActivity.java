package com.example.learningandroiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void submitOrder(View view){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        EditText theNameisThis = (EditText) findViewById(R.id.enter_your_name);
        String yourNameIsThis = theNameisThis.getText().toString();
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Coffee for " + yourNameIsThis);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price,hasWhippedCream,hasChocolate,yourNameIsThis));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }
    private void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view
        );
        quantityTextView.setText("" +number);
    }

    /**
     * This method displays the given price on the screen.
     */




    public int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 3;
        if (addWhippedCream){
            basePrice = basePrice + 4;
        }
        if (addChocolate){
            basePrice = basePrice + 2;
        }

        return quantity *basePrice;



    }
    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolateCream, String theName){

       String priceMessage = "\nName: " + theName;
          priceMessage += "\nAdd Whipped Cream: " + addWhippedCream;
        priceMessage += "\nAdd Chocolate: " + addChocolateCream;
         priceMessage += "\nQuantity:" + quantity;
         priceMessage += "\nTotal: $" + price;

         priceMessage += "\nThanks For Ordering";
         return priceMessage;


    }

    public void increment(View view){
        if(quantity == 100){
           
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if(quantity == 1){
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

}