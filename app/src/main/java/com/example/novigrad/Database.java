package com.example.novigrad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) { // la table n'existe pas encore. On la crée
        String qry1 = "create table users(username TEXT, email TEXT, password TEXT, role TEXT)"; // création de la table
        SQLiteDatabase.execSQL(qry1);


        //register("admin", "admin@uottawa.ca", "1234&Abcd", 2); // créer directement l'administrateur
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // mise à jour de la table

    }

    /**
     * Enregistrer dans la base de donnéees
     * @param username
     * @param email
     * @param password
     * @param role
     */
    public void register(String username, String email, String password, String role) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", role);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    /**
     * Rechercher user et password dans la batabase
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        String [] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if(c.moveToFirst()) {
            return true;
        }
        return false;
    }

    /**
     * Obtenir tous les comptes true = employés, false = clients
     * @param role
     * @return
     */
    public ArrayList<String> getRegisterData(String role) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = "role=?";
        String[] selectionArgs = { role };

        Cursor c = db.query("users", null, selection, selectionArgs, null, null, null);

        if(c.moveToFirst()) {
            do {
                String username = c.getString(0); // username
                String email = c.getString(1); // email
                //String password = c.getString(2); // password
                arr.add(username + "$" + email); // ajouter dans le tab
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arr;
    }

    /**
     * Supprimer un utilisateur de la base de données
     * @param username
     */
    public void removeProfil(String username) {
        String str[] = {username};
        SQLiteDatabase db = getWritableDatabase();
        db.delete("users","username=?", str);
        db.close();
    }
}
