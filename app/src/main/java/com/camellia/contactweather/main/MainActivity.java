package com.camellia.contactweather.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.AllContactsActivity;
import com.camellia.contactweather.contacts.DataBaseHelper;
import com.camellia.contactweather.webservice.ApiManager;
import com.camellia.contactweather.webservice.model.DataModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnOptionMenuItemClickListener {

    private FloatingActionButton floatingButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter myAdapter;
    private List<ContactData> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.myToolBar));
        setTitle("Contact Weather");

        recyclerView = findViewById(R.id.myRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ContactAdapter(contactList, this, this);
        recyclerView.setAdapter(myAdapter);

        floatingButton = findViewById(R.id.floatingButton);
        clickOnFloatingButton();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    // Scroll Down
                    if (floatingButton.isShown()) {
                        floatingButton.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!floatingButton.isShown()) {
                        floatingButton.show();
                    }
                }
            }
        });
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
        ArrayList<ContactData> list = db.readContacts();
        contactList.clear();
        contactList.addAll(list);
        myAdapter.notifyDataSetChanged();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (ContactData contactData : contactList) {
                    double latitude = contactData.getLatitude();
                    double longitude = contactData.getLongitude();

                    Response<DataModel> response = null;
                    try {
                        response = ApiManager.getInstance().getCurrentWeather(latitude, longitude).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (response != null) {
                        DataModel dataModel = response.body();
                        contactData.setDataModel(dataModel);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        }).start();
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

    @Override
    public void onOptionMenuItemClicked(View view, final int position) {
        //Display option menu

        final Context context = this;
        final ContactData contactData = contactList.get(position);
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                DataBaseHelper db = new DataBaseHelper(context);
                switch (menuItem.getItemId()) {

                    case R.id.menu_item_delete:
                        db.deleteContact(contactData.getPhoneNumber());
                        contactList.remove(position);
                        myAdapter.notifyDataSetChanged();
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_item_changeLocation:
                        Intent intent = new Intent(context, MapsActivity.class);
                        intent.putExtra("displayName", contactData.getDisplayName());
                        intent.putExtra("phone", contactData.getPhoneNumber());
                        intent.putExtra("longitude", contactData.getLongitude());
                        intent.putExtra("latitude", contactData.getLatitude());
                        intent.putExtra("isUpdate", true);

                        context.startActivity(intent);

                        Toast.makeText(context, "change location", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;

                }
                return false;
            }
        });
        popupMenu.show();
    }

}


