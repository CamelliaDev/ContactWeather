package com.camellia.contactweather.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.camellia.contactweather.main.ContactData;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper instance;

    private static final String DB_NAME = "contactsDataBase";
    private static final int DB_VERSION = 3;

    private static final String CONTACT_TABLE = "contactsTable";
    private static final String CONTACT_NAME = "name";
    private static final String CONTACT_PHONE = "phone";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String AVATAR = "avatar";
    private static final String CITY = "city";

    private DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlContacts = "CREATE TABLE contactsTable(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, " +
                "phone VARCHAR not null unique, " +
                "latitude DOUBLE, " +
                "longitude DOUBLE, " +
                "city VARCHAR, " +
                "avatar VARCHAR " +
                ")";
        sqLiteDatabase.execSQL(sqlContacts);
    }

    public void addContact(String name, String phone, double latitude, double longitude, String avatar) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, name);
        contentValues.put(CONTACT_PHONE, phone);
        contentValues.put(LATITUDE, latitude);
        contentValues.put(LONGITUDE, longitude);
        contentValues.put(AVATAR, avatar);

        db.insertWithOnConflict(CONTACT_TABLE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void updateContactCity(String phone, String city) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY, city);

        db.update(CONTACT_TABLE, contentValues, CONTACT_PHONE + "=?", new String[]{phone});
        db.close();
    }

    public void updateContactLocation(String phone, double latitude, double longitude, String avatar) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LATITUDE, latitude);
        contentValues.put(LONGITUDE, longitude);
        contentValues.put(AVATAR, avatar);

        db.update(CONTACT_TABLE, contentValues, CONTACT_PHONE + "=?", new String[]{phone});
        db.close();
    }

    public void deleteContact(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + CONTACT_TABLE + " WHERE " + CONTACT_PHONE + "= '" + phone + "'");
        db.close();
    }

    public ArrayList<ContactData> readContacts() {
        ArrayList<ContactData> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CONTACT_TABLE, null, null, null, null, null, CONTACT_NAME + " ASC");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ContactData contactData = new ContactData();

                String name = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
                double latitude = cursor.getDouble(cursor.getColumnIndex(LATITUDE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(LONGITUDE));
                String avatar = cursor.getString(cursor.getColumnIndex(AVATAR));
                String city = cursor.getString(cursor.getColumnIndex(CITY));

                contactData.setCity(city);
                contactData.setDisplayName(name);
                contactData.setPhoneNumber(phone);
                contactData.setLatitude(latitude);
                contactData.setLongitude(longitude);
                if (avatar != null) {
                    contactData.setAvatar(Uri.parse(avatar));
                }
                list.add(contactData);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlContacts = "DROP TABLE IF EXISTS contactsTable";
        sqLiteDatabase.execSQL(sqlContacts);
        onCreate(sqLiteDatabase);

    }
}
