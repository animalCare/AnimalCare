package com.app.animalcare;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Mostrar extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar);
		  DatabaseHandler db = new DatabaseHandler(this);
		 List<Mascotas> contacts = db.getAllPET();
	        ArrayList<String> nombre = new ArrayList<String>(); 
	 
	        for (Mascotas cn : contacts) {
	        	nombre.add(cn.get_nombre()+ "id: " + cn.get_idMascota());
	        	ListView listaEstudiante = (ListView) findViewById(R.id.lista);
	        	listaEstudiante.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombre));
	        	Log.d("Datos: ", cn.get_propietario().toString() + "," + cn.get_nombre().toString() + ","
	        			+ cn.get_apodo().toString() + "," + cn.get_fechaNacimiento().toString() + ","
	        			+ cn.get_foto().toString() + "," + cn.get_genero().toString() + "," + 
	        			cn.get_idMascota() + "," + cn.get_raza().toString() + "," + cn.get_especie().toString());
	        }
	        int x =  db.getUltimoID();
	}
}
