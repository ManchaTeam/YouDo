package com.manchateam.youdo;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
	
//	Clase para las tareas
	
	public String tarea;
	public String descripcion;
	private List<String> subtareas = new ArrayList<String>();	
	private boolean[] subTasksDone = new boolean[0]; 
	
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
		setSubTasksDone() ;
	}
	
	public void eliminarSubTarea(int posicion){
		List<String> aux = new ArrayList<String>();	
		int cont =0;
		for (int i = 0; i<subtareas.size();i++){
			if(i!=posicion){
				aux.add(subtareas.get(i));
			}
		}
		subtareas = aux;
		eliminarSubTaskBoolean(posicion);
	}
	
	public void eliminarSubTaskBoolean(int posicion){
		boolean[] aux = new boolean[subTasksDone.length-1];
		int cont =0;
		for (int i = 0; i<subTasksDone.length;i++){
			if(i!=posicion){
				aux[cont]=subTasksDone[i];
				cont++;
			}
		}
		subTasksDone = aux;
	}
	
	public void setSubTaskDone(int posicion){
		subTasksDone[posicion]= true;
	}
	public void setSubTaskUndo(int posicion){
		subTasksDone[posicion]= false;
	}
	
	public int getDonePercent(){
		int porcentaje;
		int cantDone=0;
		if(subTasksDone.length != 0){
		for(int i = 0;i< subTasksDone.length;i++){
			if(subTasksDone[i]){
				cantDone++;
			}
		}
		porcentaje = cantDone * 100 / subTasksDone.length;
		return porcentaje;
		}
		else{
			return 0;
		}
	}
	
	public boolean[] getSubTasksDone() {
		return subTasksDone;
	}
	public void setSubTasksDone() {
		boolean[] aux = new boolean[subtareas.size()];
		for(int i=0;i<subTasksDone.length;i++){
			aux[i]=subTasksDone[i];
		}
		subTasksDone = aux;
	}
	
	
	@Override
	public String toString() {
		return "Tareas [tarea=" + tarea + ", descripcion=" + descripcion
				+ "]";
	}
	
	

}
