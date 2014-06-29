package com.app.animalcare;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "MascotasManager";

	// Contacts table name
	private static final String TABLE_MASCOTAS = "mascotas";

	// Contacts Table Columns names
	private static final String KEY_ID ="idMascota";
	private static final String KEY_PROPIETARIO = "propietario";
	private static final String KEY_NOMBRE = "nombre";
	private static final String KEY_APODO = "apodo";
	private static final String KEY_GENERO = "genero";
	private static final String KEY_FECHANACIMIENTO = "fechaNacimiento";
	private static final String KEY_ESPECIE = "especie";
	private static final String KEY_RAZA = "raza";
	private static final String KEY_FOTO = "foto";
	
	

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MASCOTAS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_PROPIETARIO + " TEXT,"
				+ KEY_NOMBRE + " TEXT," +
				KEY_APODO + " TEXT," +
				KEY_GENERO + " TEXT," +
				KEY_FECHANACIMIENTO + " TEXT," +
				KEY_ESPECIE + " TEXT," +
				KEY_RAZA + " TEXT," +
				KEY_FOTO + " TEXT" +
				")";
		Log.d("QR: ", CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addContact(Mascotas pets) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PROPIETARIO, pets.get_propietario()); 
		values.put(KEY_NOMBRE, pets.get_nombre());
		values.put(KEY_APODO, pets.get_apodo());
		values.put(KEY_GENERO, pets.get_genero());
		values.put(KEY_FECHANACIMIENTO, pets.get_fechaNacimiento());
		values.put(KEY_ESPECIE, pets.get_especie());
		values.put(KEY_RAZA, pets.get_raza());
		values.put(KEY_FOTO, pets.get_foto());
				// Inserting Row
		db.insert(TABLE_MASCOTAS, null, values);
		db.close(); // Closing database connection
	}
	
	// borrando una mascota
	public void deleteContact(Mascotas PET) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_MASCOTAS, KEY_ID + " = ?",
					new String[] { String.valueOf(PET.get_idMascota()) });
			db.close();
	}
	
	public List<Mascotas> getAllPET() {
		List<Mascotas> listaMascota = new ArrayList<Mascotas>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_MASCOTAS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Mascotas pet = new Mascotas();
				pet.set_idMascota(Integer.parseInt(cursor.getString(0)));
				pet.set_propietario( cursor.getString(1));
				pet.set_nombre(cursor.getString(2));
				pet.set_apodo( cursor.getString(3));
				pet.set_genero( cursor.getString(4));
				pet.set_fechaNacimiento( cursor.getString(5) );
				pet.set_especie( cursor.getString(6));
				pet.set_raza( cursor.getString(7));
				pet.set_foto( cursor.getString(8));
				listaMascota.add(pet);
			} while (cursor.moveToNext());
		}

		return listaMascota;
	}
	
	public int getUltimoID()
	{
		int id=0;
		String selectQuery = "SELECT  idMascota FROM " + TABLE_MASCOTAS + " order by idMascota DESC limit 1";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
			id = Integer.parseInt(cursor.getString(0));
		}
		Log.d("ID::::",String.valueOf(id));
		return id;
		

	}
	
	
	/*
	
	
	

	// Obtener obtener mascota
	Mascotas getMascota(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_MASCOTAS, new String[] 
				{ KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Mascotas pet = new Mascotas(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
		// return contact
		return pet;
	}
	
	// Getting All Contacts


	// Updating single contact
	public int updateContact(Mascotas contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	
	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
*/
}
