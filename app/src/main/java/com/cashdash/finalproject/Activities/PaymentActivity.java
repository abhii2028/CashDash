package com.cashdash.finalproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cashdash.finalproject.R;

public class PaymentActivity extends AppCompatActivity {

    int flag;
    private String TAG = "UpiActivity";
    String payeeAddress = "abhijeetprusty28@paytm";
    String payeeName = "Abhijeet Prusty";
    String transactionNote = "Test for Deeplinking";
    String amount = "1";
    String currencyUnit = "INR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        flag = getIntent().getIntExtra("filter",0);

        if (flag == 1)
        {
            Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+transactionNote+
                    "&am="+amount+"&cu="+currencyUnit);
            Log.d(TAG, "onClick: uri: "+uri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivityForResult(intent,1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: requestCode: "+requestCode);
        Log.d(TAG, "onActivityResult: resultCode: "+resultCode);
        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined

        if(data!=null) {
            Log.d(TAG, "onActivityResult: data: " + data.getStringExtra("response"));
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase())) {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(PaymentActivity
                .this, "Can't go back Right now", Toast.LENGTH_SHORT).show();
    }
}
