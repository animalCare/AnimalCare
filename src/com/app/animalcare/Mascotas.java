package com.app.animalcare;

public class Mascotas {
	
	// Miembros privados 
	int _idMascota;
	String _propietario;
	String _nombre;
	String _apodo;
	String _genero;
	String _fechaNacimiento;
	String _especie;
	String _raza;
	String _foto;
	
	
	//_idMascota,_propietario,_nombre,_apodo,	_genero,_fechaNacimiento,_especie,_raza
	
	// Empty constructor
	public Mascotas(){
		
	}
	// constructor
	public Mascotas(int idMascota,String propietario,String nombre, String apodo, String genero, 
			String fechaNacimiento, String especie, String raza,String foto){
		this._idMascota = idMascota;
		this._propietario = propietario;
		this._nombre = nombre;
		this._apodo = apodo;
		this._genero = genero;
		this._fechaNacimiento = fechaNacimiento;
		this._especie = especie;
		this._raza = raza;
		this._foto = foto;
	}
	
	// constructor
	public Mascotas(String propietario,String nombre, String apodo, String genero, 
			String fechaNacimiento, String especie, String raza,String foto){
		this._propietario = propietario;
		this._nombre = nombre;
		this._apodo = apodo;
		this._genero = genero;
		this._fechaNacimiento = fechaNacimiento;
		this._especie = especie;
		this._raza = raza;
		this._foto = foto;
	}
	
	public int get_idMascota() {
		return _idMascota;
	}
	public void set_idMascota(int _idMascota) {
		this._idMascota = _idMascota;
	}
	public String get_propietario() {
		return _propietario;
	}
	public void set_propietario(String _propietario) {
		this._propietario = _propietario;
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}
	public String get_apodo() {
		return _apodo;
	}
	public void set_apodo(String _apodo) {
		this._apodo = _apodo;
	}
	public String get_genero() {
		return _genero;
	}
	public void set_genero(String _genero) {
		this._genero = _genero;
	}
	public String get_fechaNacimiento() {
		return _fechaNacimiento;
	}
	public void set_fechaNacimiento(String _fechaNacimiento) {
		this._fechaNacimiento = _fechaNacimiento;
	}
	public String get_especie() {
		return _especie;
	}
	public void set_especie(String _especie) {
		this._especie = _especie;
	}
	public String get_raza() {
		return _raza;
	}
	public void set_raza(String _raza) {
		this._raza = _raza;
	}
	public String get_foto() {
		return _foto;
	}
	public void set_foto(String _foto) {
		this._foto = _foto;
	}
	
}
