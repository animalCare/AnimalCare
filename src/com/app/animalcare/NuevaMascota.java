package com.app.animalcare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ShowToast")
public class NuevaMascota extends Activity {

	private TextView Output;
	private int estadoFoto=0; // foto por defecto
	private int year;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111; 
	private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/animalCare/";
    private File file = new File(ruta_fotos);
    private ImageView image;
    // por defecto sera la ruta de la iamgen por defecto
    private String fileRuta;
  	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_mascota);
				
		 /**
		  * Llenando Data Spinner genero
		  * 
		  */
		 Spinner sp = (Spinner)findViewById(R.id.comboGenero);
		 ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.genero, android.R.layout.simple_spinner_item);
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 sp.setAdapter(adapter);
		 
		 /**
		  * Obtener dia actual
		  */
		 TextView txtNacimiento = (TextView) findViewById(R.id.txtNacimiento);
		 Calendar c = Calendar.getInstance();
		 SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		 txtNacimiento.setText( formato.format(c.getTime()) );
		 
		 /**
		  * Llenando Data Spinner especie
		  * 
		  */
		 Spinner spEspecie = (Spinner)findViewById(R.id.comboEspecie);
		 ArrayAdapter adapterEspecie = ArrayAdapter.createFromResource(this, R.array.especie, android.R.layout.simple_spinner_item);
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spEspecie.setAdapter(adapterEspecie);
		 // ACCION SPINNER ESPECIE
		 spEspecie.setOnItemSelectedListener(new OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView<?> parent,
	    	            android.view.View v, int position, long id) {
	            	String[] items; 
	                // TODO Auto-generated method stub
	                Object item = parent.getItemAtPosition(position);
	                int asignado = 0;
	                if (item!=null) {
	                	//Toast.makeText(NuevaMascota.this, item.toString(), Toast.LENGTH_SHORT).show();
                		// crear adaptador para cargar razas
                		Spinner spRaza = (Spinner)findViewById(R.id.comboRaza);
                		 ArrayAdapter<String> adapterRaza = null;
		                if (item.equals("Perro"))
		                {
		                	items = getResources().getStringArray(R.array.perros);
		                	adapterRaza =new ArrayAdapter<String>(NuevaMascota.this, android.R.layout.simple_spinner_item, items);
		                	asignado = 1;
		                }
		                if (item.equals("Gato"))
		                {
		                	items = getResources().getStringArray(R.array.gatos);
		                	adapterRaza =new ArrayAdapter<String>(NuevaMascota.this, android.R.layout.simple_spinner_item, items);
		                	asignado=1;
		                }
		                if (item.equals("Ave"))
		                {
		                	items = getResources().getStringArray(R.array.Ave);
		                	adapterRaza =new ArrayAdapter<String>(NuevaMascota.this, android.R.layout.simple_spinner_item, items);
		                	asignado=1;
		                }
		                if (item.equals("Reptil"))
		                {
		                	items = getResources().getStringArray(R.array.Reptil);
		                	adapterRaza =new ArrayAdapter<String>(NuevaMascota.this, android.R.layout.simple_spinner_item, items);
		                	asignado=1;
		                }
		                if (item.equals("Roedor"))
		                {
		                	items = getResources().getStringArray(R.array.Roedor);
		                	adapterRaza =new ArrayAdapter<String>(NuevaMascota.this, android.R.layout.simple_spinner_item, items);
		                	asignado=1;
		                }
		                   
	                	if (asignado == 1){
		                adapterRaza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	               		spRaza.setAdapter(adapterRaza);}
	                }
	            }
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	            }
	        });
		 
		 /// prueba guardar en BD
		 Button guardar = (Button) findViewById(R.id.guardar);
		 guardar.setOnClickListener(new OnClickListener (){
		 public void onClick( View v){
			  DatabaseHandler db = new DatabaseHandler(NuevaMascota.this);
			  // referenciando
			  Spinner genero = (Spinner) findViewById(R.id.comboGenero);
			  Spinner especies = (Spinner) findViewById(R.id.comboEspecie);
			  Spinner raza = (Spinner) findViewById(R.id.comboRaza);
			  
			  TextView propietario = (TextView) findViewById(R.id.txtPropietario);
			  TextView nombre = (TextView) findViewById(R.id.txtNombre);
			  TextView apodo = (TextView) findViewById(R.id.txtApodo);
			  TextView nac = (TextView) findViewById(R.id.txtNacimiento);
			  String rutaImg = fileRuta;
			  if (estadoFoto==0)
			  {
				  rutaImg = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + 
						  "/animalCare/foto_default.jpg";
			  }
			  
			  db.addContact(new Mascotas(propietario.getText().toString(),
					  		nombre.getText().toString(), apodo.getText().toString(),
					  		genero.getSelectedItem().toString(), nac.getText().toString(),
					  		especies.getSelectedItem().toString(),
					  		raza.getSelectedItem().toString(),rutaImg));
			 
			 
		  }
		});
		 
		 Button lis= (Button) findViewById(R.id.lis);
		 lis.setOnClickListener(new OnClickListener (){
		 public void onClick( View v){
			 
			 Intent accion = new Intent(getApplicationContext(), Mostrar.class);
				//accion.putExtra("MIUSUARIO",campoUsuario.getText().toString());
				startActivity(accion);
			 
		  }
		});
		 
		Output = (TextView) findViewById(R.id.txtNacimiento);
		final Calendar c2 = Calendar.getInstance();
		year  = c2.get(Calendar.YEAR);
		month = c2.get(Calendar.MONTH);
		day   = c2.get(Calendar.DAY_OF_MONTH);
		// Mostrar fecha actual
		Output.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));
		// Boton para escuchar date picker dialog
		Output.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                // On button click show datepicker dialog 
				showDialog(DATE_PICKER_ID);
			}
		});
		
		/**
		 * Programando boton de toma foto
		 */
		 // creando directorio sino existe
		 file.mkdirs();
		 //copiando img default
		 Bitmap bm = BitmapFactory.decodeResource( getResources(), R.drawable.foto_default2);
		 String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/animalCare/";
		 File file = new File(extStorageDirectory, "foto_default.jpg");
		 FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			 try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 image= (ImageView) findViewById(R.id.imageViewFoto);
		 image.setOnClickListener(new OnClickListener (){
				@Override
				public void onClick(View v) {
					fileRuta = ruta_fotos + getCode() + ".jpg";
					File mi_foto = new File( fileRuta );
					try {
		                mi_foto.createNewFile();
		            } catch (IOException ex) {	            	
		            	Log.e("ERROR ", "Error:" + ex);
		            }       
		            //
		            Uri uri = Uri.fromFile( mi_foto );
		            //Abre la camara para tomar la foto
		            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
  		            //Guarda imagen
		            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		            //Retorna a la actividad
		            startActivityForResult(cameraIntent, 0);
				}
			});
	
	} // fin on create
	
	 	@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	super.onActivityResult(requestCode, resultCode, data);
	    	File imgFile = new  File(fileRuta);
            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView myImage = (ImageView) findViewById(R.id.imageViewFoto);
                myImage.setImageBitmap(myBitmap);
                estadoFoto = 1; // se cambio foto por defecto
            }
	 	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:
			return new DatePickerDialog(this, pickerListener, year, month,day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			
			year  = selectedYear;
			month = selectedMonth;
			day   = selectedDay;

			// Show selected date 
			Output.setText(new StringBuilder().append(month + 1)
					.append("-").append(day).append("-").append(year)
					.append(" "));
	
		   }
	    };
	    
		@SuppressLint("SimpleDateFormat")
		private String getCode()
		{
			DatabaseHandler db = new DatabaseHandler(this);
			int id = db.getUltimoID();
			id++;
			return "perfil_" + String.valueOf(id);		
			 
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			return true;
		}
}

