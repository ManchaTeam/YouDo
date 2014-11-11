package com.manchateam.youdo;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
	
//	Clase para las tareas
	
	public String tarea;
	public String descripcion;
	private List<String> subtareas = new ArrayList<String>();	
	
	public Tarea(String tarea) {
		super();
		this.tarea = tarea;
	}
	public Tarea() {
		
	}


	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public List<String> getSubtareas() {
		return subtareas;
	}
	public void setSubtareas(List<String> subtareas) {
		this.subtareas = subtareas;
	}
	@Override
	public String toString() {
		return "Tareas [tarea=" + tarea + ", descripcion=" + descripcion
				+ "]";
	}

}
