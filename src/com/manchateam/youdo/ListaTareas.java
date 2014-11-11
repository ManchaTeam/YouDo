package com.manchateam.youdo;

public abstract class ListaTareas {
	private static Tarea[] tareas = new Tarea[0];
		
	public static boolean addTarea(Tarea p){
		Tarea[] favAux = new Tarea[tareas.length + 1];
        for (int i = 0; i < tareas.length; i++) {
            favAux[i] = tareas[i];
        }
        favAux[favAux.length-1] = p;
        tareas=favAux;
        return true;
    }
	
	public static void setLista(Tarea[] s){
		tareas = s;
	}
    
    public static Tarea[] getTareas(){
        return tareas;
    }
	
}
