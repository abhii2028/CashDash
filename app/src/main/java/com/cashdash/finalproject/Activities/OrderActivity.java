package com.cashdash.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cashdash.finalproject.Model.Order;
import com.cashdash.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    Button proceed;
    EditText orderName,orderAddress,orderNumber,orderAmount;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        proceed = findViewById(R.id.proceed);
        orderName = findViewById(R.id.orderName);
        orderAddress = findViewById(R.id.orderAddress);
        orderNumber = findViewById(R.id.orderNumber);
        orderAmount = findViewById(R.id.orderAmount);

        mDatabase = FirebaseDatabase.getInstance().getReference("OrderList");

    }

    public void proceedtopay(View view)
    {
        AddOrder();

        Intent intent = new Intent(OrderActivity.this,PaymentActivity.class);
        intent.putExtra("filter",1);
        startActivity(intent);
    }

    private void AddOrder() {
        String name = orderName.getText().toString();
        String address = orderAddress.getText().toString();
        String number = orderNumber.getText().toString();
        String amount = orderAmount.getText().toString();

        String id = mDatabase.push().getKey();
        Order order = new Order(id,name,address,number,amount);
        mDatabase.child(id).setValue(order);

        orderName.setText("");
        orderAddress.setText("");
        orderNumber.setText("");
        orderAmount.setText("");

    }
}
