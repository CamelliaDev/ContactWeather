package com.camellia.contactweather.contacts;//package com.camellia.contactweather.contacts;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
//import com.camellia.contactweather.R;
//
//public class Location extends AppCompatActivity {
//
//    Button btnConfirm;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.location);
//
//        final DataBaseHelper db = new DataBaseHelper(this);
//
//        btnConfirm = findViewById(R.id.confirmButton);
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = getIntent();
//
//                String contactName = intent.getStringExtra("displayName"); // will return "FirstKeyValue"
//                String contactPhone = intent.getStringExtra("phone"); // will return "SecondKeyValue"
//                db.addContact(contactName, contactPhone, 59, 29 );
//                finish();
//            }
//        });
//
//
//    }
//
//
//}
