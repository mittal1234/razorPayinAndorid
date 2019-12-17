package com.example.razorpaysample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class RazorPayActivity extends AppCompatActivity implements PaymentResultListener {

    AppCompatButton button;
    String TAG="Payment Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);


        Checkout.preload(getApplicationContext());

        button = findViewById(R.id.btnPayment);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }


    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
//        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Test");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Order #123456");
            options.put("id", "order_DaZlswtdcn9UNV");
            options.put("currency", "INR");
            options.put("entity","razorpay_signature");
            options.put("email", "user@gmail.com");
            options.put("status","created");
            options.put("receipt","rcptid_11");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "shubham.mobcoder@gmail.com");
            preFill.put("contact", "8979490617");
            options.put("prefill", preFill);

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "500");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this, "Payemnt Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d(TAG, "onPaymentError: "+s);
        Toast.makeText(this, "error" +s, Toast.LENGTH_SHORT).show();
    }
}
