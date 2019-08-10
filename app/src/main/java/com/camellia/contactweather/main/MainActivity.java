package com.camellia.contactweather.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactData;
import com.camellia.contactweather.contacts.AllContactsActivity;
import com.camellia.contactweather.contacts.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter myAdapter;
    private List<AllContactData> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.myToolBar));
        setTitle("Contact Weather");

        recyclerView = findViewById(R.id.myRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(myAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        floatingButton = findViewById(R.id.floatingButton);
        clickOnFloatingButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initContactData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "sync clicked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    public void initContactData() {
        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList<AllContactData> list = db.readContacts();
        contactList.clear();
        contactList.addAll(list);
        myAdapter.notifyDataSetChanged();
    }

    public void clickOnFloatingButton() {
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}
