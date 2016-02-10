package com.newfeds.walletheaven.helperclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.newfeds.walletheaven.core.Keyholder;
import com.newfeds.walletheaven.core.WebCredStructure;

import java.util.ArrayList;

/**
 * Created by Nonimux on 12/10/2015.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME = "store";

    private static final String TABLE_USER = "user";
    private static final String TABLE_WEBSITES = "websites";

    private static final String WEBSITES_FIELD_ID = "id";
    private static final String WEBSITES_FIELD_URL = "url";
    private static final String WEBSITES_FIELD_USER = "username";
    private static final String WEBSITES_FIELD_PASSWORD = "password";

    private Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE "+ TABLE_USER +"(id INTEGER PRIMARY KEY, password TEXT)";
        String CREATE_TABLE_WEBSITES = "CREATE TABLE " + TABLE_WEBSITES + "("+ WEBSITES_FIELD_ID +" INTEGER PRIMARY KEY, "+
                WEBSITES_FIELD_URL + " TEXT, "+ WEBSITES_FIELD_USER + " TEXT, "+ WEBSITES_FIELD_PASSWORD + " TEXT);";
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_WEBSITES);
        L.Log("Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        L.Log("Updating Table");
        String DROP_TABLE_USER = "DROP TABLE IF EXISTS "+ TABLE_USER;
        String DROP_TABLE_WEBSITES = "DROP TABLE IF EXISTS " + TABLE_WEBSITES;
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_WEBSITES);
        onCreate(db);
    }

    public boolean InsertPassword(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        db.insert(TABLE_USER, null, contentValues);
        L.Log("Inserted : " + password);
        return true;
    }

    public int checkMasterPasswordCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query  = "Select * from " + TABLE_USER;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public String getMasterPasswordHalfHash(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{"password"}, null, null, null, null, null, "1");
        cursor.moveToFirst();
        String pass = cursor.getString(cursor.getColumnIndex("password"));
        return pass;
    }

    public boolean checkIfDataExits(String table_name, String field_name, String value){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ table_name + " where "+ field_name + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{value});
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void deleteWebCredentials(String id){
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_WEBSITES, WEBSITES_FIELD_ID+"=?", new String[]{id});


            db.close();
            L.Log("Deleted item from the list");

        }catch (Exception e){
            L.Log(e.getMessage());
        }
    }

    public void editWebCredentials(String id, String url, String username, String password){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String cryptKey = Keyholder.masterkey;
            String cryptIv = OtherHelper.mySubString(Keyholder.masterkey, 0, 16);

            String cryptedPassword = CryptoHelper.encrypt(cryptKey, cryptIv, password);

            ContentValues cv = new ContentValues();
            cv.put(WEBSITES_FIELD_URL, url);
            cv.put(WEBSITES_FIELD_USER, username);
            cv.put(WEBSITES_FIELD_PASSWORD, cryptedPassword);

            db.update(TABLE_WEBSITES, cv, WEBSITES_FIELD_ID + "=?", new String[]{id});
            db.close();
            L.Log("updated");
        }catch (Exception e){
            L.Log(e.getMessage());
        }
    }

    public void insertWebCredentials(String url, String username, String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String cryptKey = Keyholder.masterkey;
            String cryptIv = OtherHelper.mySubString(Keyholder.masterkey, 0, 16);

            String cryptedPassword = CryptoHelper.encrypt(cryptKey, cryptIv, password);

            ContentValues cv = new ContentValues();
            cv.put(WEBSITES_FIELD_URL, url);
            cv.put(WEBSITES_FIELD_USER, username);
            cv.put(WEBSITES_FIELD_PASSWORD, cryptedPassword);

            db.insert(TABLE_WEBSITES, null, cv);
            db.close();
        }catch (Exception e){
            L.Log(e.getMessage());
        }
    }

    public ArrayList<WebCredStructure> takeOutWebCredentials(){
        ArrayList<WebCredStructure> webCredStructures = new ArrayList<>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();

            String decryptkey = Keyholder.masterkey;
            String decryptIv = OtherHelper.mySubString(Keyholder.masterkey,0,16);
            L.Log(String.valueOf(decryptIv.length()));
            Cursor res = db.query(TABLE_WEBSITES, null,null,null,null,null,null,null);

            res.moveToFirst();

            while(res.isAfterLast()!= true){
                String wId = res.getString(res.getColumnIndex(WEBSITES_FIELD_ID));
                String wUrl = res.getString(res.getColumnIndex(WEBSITES_FIELD_URL));
                String wUser = res.getString(res.getColumnIndex(WEBSITES_FIELD_USER));
                String wPass = res.getString(res.getColumnIndex(WEBSITES_FIELD_PASSWORD));

                String dPass = CryptoHelper.decrypt(decryptkey, decryptIv, wPass);

                webCredStructures.add(new WebCredStructure(wId,wUrl,wUser,dPass));

                res.moveToNext();
            }
            db.close();
        }catch (Exception e){
            L.Log(e.getMessage());
        }
        return webCredStructures;
    }

}
