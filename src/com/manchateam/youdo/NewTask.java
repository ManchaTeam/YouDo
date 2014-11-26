package com.manchateam.youdo;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewTask extends ActionBarActivity {

	private String[] subtareas = new String[0];
	private List<String> nombres = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
        
		//Limpia el focus en los edit text y setea el focus en el layout general
		EditText et = (EditText) findViewById(R.id.txbDesc);
		et.clearFocus();
		
		et = (EditText) findViewById(R.id.txbTitulo);
		et.clearFocus();
		
		RelativeLayout generalLayout = (RelativeLayout) findViewById(R.id.linearLayout_focus);
		generalLayout.requestFocus();
		
		ListView lv = (ListView) findViewById(R.id.listSubTask);

		
		//SETEEA QUE HACER EN CUANDO UN ITEM ES LONG CLICKED
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				final int position = arg2;
				AlertDialog.Builder cartel = new AlertDialog.Builder(NewTask.this);
				cartel.setMessage("Seguro que desea eliminar?")
			       .setTitle("Eliminar");
				cartel.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
			        	   eliminarSubTask(position);
			           }
			       });
				cartel.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User cancelled the dialog

			           }
			       });
			
				AlertDialog dialog = cartel.create();
				dialog.show();
				return false;
			}
        }); 
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/*
	METODOS PARA EL PROGRAMA
	*/
	
	//creo un popUpWindow
	private PopupWindow pwindo;
	//Este metodo inica el popUpWindow
	private void initiatePopupWindow() {
		try {
			// We need to get the instance of the LayoutInflater
			LayoutInflater inflater = (LayoutInflater) this
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.popup,(ViewGroup) findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, 300, 370, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Este metodo elimina la subtarea seleccionada por long click
	public void eliminarSubTask(int row){
		String[] listaAux = new String[nombres.size() - 1];
		List<String> arrayAux = new ArrayList<String>();


        if (nombres.get(row) != null) {
        	nombres.set(row, null);
        }
        //Ademas de eliminar la fila hay que eliminar la posicion del array y reiniciar.
        for (int i = 0; i < nombres.size(); i++) {
            if(i!=row){
            	arrayAux.add(nombres.get(i));
            }
        }
        nombres = arrayAux;
		ListView list = (ListView) findViewById(R.id.listSubTask);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres.toArray());
		list.setAdapter(adapter);

	}
	//Este metodo guarda la subtarea en la lista y la muestra en la ListView 
	@SuppressLint("NewApi")
	public void guardarSubTask(View view){
		EditText we = (EditText) pwindo.getContentView().findViewById(R.id.txbSubTarea);		
		String texto = we.getText().toString();
		ListView list = (ListView) findViewById(R.id.listSubTask);

		//if que no guarda cosas en blanco
		if (texto.trim().isEmpty()){
			pwindo.dismiss();}
		else {
	
			nombres.add(texto);
			
			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres.toArray());
			list.setAdapter(adapter);
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(we.getWindowToken(), 0);
			//cierra el popUpWindow
			pwindo.dismiss();
		}
	}
	
	
	//Esto se ejecuta cuando aprenten el boton nueva tarea
	public void addSubTask(View view){
		
		initiatePopupWindow();

		
	}
	
	//terminar las clases de tareas y hacer que las guarde
	public void guardar (View view){
		//TODO terminar las clases de tareas y hacer que las guarde
		EditText desc = (EditText) findViewById(R.id.txbDesc);
		EditText titulo = (EditText) findViewById(R.id.txbTitulo);

		Tarea aux = new Tarea();
		aux.setDescripcion(desc.getText().toString());
		aux.setTarea(titulo.getText().toString());
		aux.setSubtareas(nombres);
		ListaTareas.addTarea(aux);
		
		finish();
		
	}

	

}
