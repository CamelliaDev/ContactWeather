package com.camellia.contactweather.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.main.MapsActivity;
import com.camellia.contactweather.main.OnItemClickListener;
import com.camellia.contactweather.main.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class AllContactsActivity extends AppCompatActivity implements OnItemClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    private RecyclerView recyclerView;
    private List<AllContactData> allContactList = new ArrayList<>();
    private AllContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        setSupportActionBar((Toolbar) findViewById(R.id.myToolBar));
        setTitle("Contacts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        managePermission();

        recyclerView = findViewById(R.id.myRecyclerView);
        adapter = new AllContactAdapter(allContactList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_item_search:
                Toast.makeText(this, "search clicked", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            default:
                return false;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar_all_contact, menu);
        return true;
    }

    private void managePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            readAndShowContacts();
        }
    }

    private void readAndShowContacts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AllContactData> list = getAllContacts();
                allContactList.clear();
                allContactList.addAll(list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readAndShowContacts();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS)) {
                        new AlertDialog.Builder(this).
                                setTitle("Read Contacts permission").
                                setMessage("You need to grant read contacts permission to use read" +
                                        " contacts feature. Retry and grant it !").show();
                    } else {
                        new AlertDialog.Builder(this).
                                setTitle("Read Contacts permission denied").
                                setMessage("You denied read contacts permission." +
                                        " So, the feature will be disabled. To enable it" +
                                        ", go on settings and " +
                                        "grant read contacts for the application").show();
                    }
                }
                break;
        }
    }

    private List<AllContactData> getAllContacts() {
        List<AllContactData> allContacts = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                AllContactData contact = null;
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
                    Uri photo = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    contact = new AllContactData();
                    contact.setDisplayName(name);
                    contact.setAvatar(photo);

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor != null && phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.setPhoneNumber(phoneNumber);
                    }

                    if (phoneCursor != null) {
                        phoneCursor.close();
                    }
                }
                if (contact != null) {
                    allContacts.add(contact);
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return allContacts;
    }

    @Override
    public void onItemClicked(int position) {
        // TODO

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("displayName", allContactList.get(position).getDisplayName());
        intent.putExtra("phone", allContactList.get(position).getPhoneNumber());
        intent.putExtra("avatar",allContactList.get(position).getAvatar().toString());
        intent.putExtra("isUpdate", false);

        startActivity(intent);
        finish();

    }
}

