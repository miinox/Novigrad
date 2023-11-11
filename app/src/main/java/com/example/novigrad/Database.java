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
        String qry1 = "create table users(username TEXT PRIMARY KEY, email TEXT, password TEXT, role TEXT)"; // création de la table
        SQLiteDatabase.execSQL(qry1);

        String qry2 = "create table service(serviceName TEXT PRIMARY KEY,  description TEXT)"; // creation de deuxieme table
        SQLiteDatabase.execSQL(qry2);

        // créer une base de données pour la succursale
        String qry3 = "CREATE TABLE succursales(nom TEXT PRIMARY KEY, lieu TEXT)";
        SQLiteDatabase.execSQL(qry3);
         // créer une base pour les jours ouvrables
        String qry4 = "CREATE TABLE jours_ouvrables(nom TEXT PRIMARY KEY, lieu TEXT)";
//        SQLiteDatabase.execSQL(qry4);
//        String qry5 = "CREATE TABLE heures_travail (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "succursale_id INTEGER," +  // Clé étrangère pour lier aux succursales
//                "heure TEXT," +
//                "FOREIGN KEY (succursale_id) REFERENCES succursales(id)" +
//                ");";
//        SQLiteDatabase.execSQL(qry5);

        //register("admin", "admin@uottawa.ca", "1234&Abcd", 2); // créer directement l'administrateur
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // mise à jour de la table

    }


    /************************************* Profil *************************************/
    /**
     * Enregistrer Profil dans la base de données
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
    /************************************* SERVICES *************************************/
    public void removeService(String name) {
        String str[] = {name};
        SQLiteDatabase db = getWritableDatabase();
        db.delete("service","serviceName=?", str);
        db.close();
    }

    public void addService(String nom, String description) {
        ContentValues cv = new ContentValues();
        cv.put("serviceName", nom);
        cv.put("description", description);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("service", null, cv);
        db.close();
    }
    public ArrayList<String> getServiceData() {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {  };

        Cursor c = db.query("service", null, null, null, null, null, null);

        if(c.moveToFirst()) {
            do {
                String name = c.getString(0); // username
                String description = c.getString(1); // email
                arr.add(name + "$" + description); // ajouter dans le tab
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arr;
    }
    /************************************* SUCCURSALES *************************************/

    private void addHeuresOuvertures(Succursale succursale, long succursaleId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues heuresValues = new ContentValues();
        heuresValues.put("succursale_id", succursaleId);
        heuresValues.put("heure", succursale.getHeuresO());
        db.insert("heures_travail", null, heuresValues);
    }
    /**
     * Ajouter une succursale
     * @param succursale
     * @return
     */
    private void addSuccursaleHelper(Succursale succursale) {
        ContentValues cv = new ContentValues();
        cv.put("nom", succursale.getNomSuc());
        cv.put("lieu", succursale.getAdresseSuc());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("succursales", null, cv);
        db.close();
    }

    public ArrayList<String> getJoursOuvrablesData(String nom) {
//        ArrayList<String> arr = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String[] selectionArgs = {  };
//
//        Cursor c = db.query("jours_ouvrables", "jour", nom, null, null, null, null);
//
//        if(c.moveToFirst()) {
//            do {
//                String jour = c.getString(1); // email
//                arr.add(jour); // ajouter dans le tab
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return arr;
        return null;
    }

    public void removeSuccursaleHelper(String nom) {
        String str[] = {nom};
        SQLiteDatabase db = getWritableDatabase();
        db.delete("succursales","nom=?", str);
        db.close();
    }

    private void addJoursOuvrables(Succursale succursale) {
        SQLiteDatabase db = getWritableDatabase();
        String place = succursale.getNomSuc();
        for (String jour : succursale.getJoursOuvrables()) {
            ContentValues joursValues = new ContentValues();
            joursValues.put("nom", place);
            joursValues.put("jour", jour);
            db.insert("jours_ouvrables", null, joursValues);
        }
    }

    public void addSuccursale(Succursale succursale) {
        addSuccursaleHelper(succursale);
        addJoursOuvrables(succursale);
//        addHeuresOuvertures(succursale, ID);
    }

    public ArrayList<String> getSuccData() {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("succursales", null, null, null, null, null, null);

        if(c.moveToFirst()) {
            do {
                String nom = c.getString(0); // username
                String lieu = c.getString(1); // email
                arr.add(nom + "$" + lieu); // ajouter dans le tab
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arr;
    }
}
