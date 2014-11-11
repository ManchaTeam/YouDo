package com.manchateam.youdo;

import java.util.ArrayList;
import java.util.List;

import com.manchateam.youdo.*;
import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Fragment.SavedState;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Main extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	* Fragment managing the behaviors, interactions and presentation of the
	* navigation drawer.
	*/
	//creo un popUpWindow
	private PopupWindow pwindo;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));


		ListView lv = (ListView) findViewById(R.id.listView);
		
		//Que hacer cuando un item de la lista principal es seleccionado
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int p = arg2;
				initiatePopupWindow(p);
			}
		});

		
		//SETEEA QUE HACER EN CUANDO UN ITEM ES LONG CLICKED
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				final int position = arg2;
				AlertDialog.Builder cartel = new AlertDialog.Builder(Main.this);
				cartel.setMessage("Seguro que desea eliminar?")
			       .setTitle("Eliminar");
				cartel.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
			        	   Tarea[] aux = ListaTareas.getTareas();
			        	   aux = eliminarTareaFromList(position,aux);
			        	   ListaTareas.setLista(aux);
			        	   refrescarLista();
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





	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	//Este metodo inica el popUpWindow
	private void initiatePopupWindow(int p) {
		try {
			
			// We need to get the instance of the LayoutInflater
			Display display = getWindowManager().getDefaultDisplay(); 

			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.ver_tarea_popup,(ViewGroup) findViewById(R.id.popup_element));
			pwindo = new PopupWindow(layout, display.getWidth()-100, display.getHeight()-200, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
			Tarea tarea = ListaTareas.getTareas()[p];
			TextView titulo = (TextView) pwindo.getContentView().findViewById(R.id.verTitulo);
			TextView desc = (TextView) pwindo.getContentView().findViewById(R.id.verDesc);
			ListView lv = (ListView) pwindo.getContentView().findViewById(R.id.verTaskList);
			titulo.append(" "+tarea.getTarea());
			desc.append(" "+tarea.getDescripcion());
			ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tarea.getSubtareas().toArray());
			lv.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void volver(View view){
		pwindo.dismiss();
	}
	
	public Tarea[] eliminarTareaFromList(int row, Tarea[] list){
		Tarea[] listaAux = new Tarea[list.length - 1];
		//List<Tarea> arrayAux = new ArrayList<Tarea>();


        if (list[row] != null) {
        	list[row]= null;
        }
        int cont = 0;
        //Ademas de eliminar la fila hay que eliminar la posicion del array y reiniciar.
        for (int i = 0; i < list.length; i++) {
            if(i!=row){
            	listaAux[cont] =list[i];
            	cont+=1;
            }
        }
        list = listaAux;
		ListView lv = (ListView) findViewById(R.id.listSubTask);

		return list;
	}
	
	// Le pasas una lista ARRAY DE STRINGS y le elimina el dato en la posicion indicada
	public List<String> eliminarStringFromList(int row, List<String> stringList){
		String[] listaAux = new String[stringList.size() - 1];
		List<String> arrayAux = new ArrayList<String>();


        if (stringList.get(row) != null) {
        	stringList.set(row, null);
        }
        //Ademas de eliminar la fila hay que eliminar la posicion del array y reiniciar.
        for (int i = 0; i < stringList.size(); i++) {
            if(i!=row){
            	arrayAux.add(stringList.get(i));
            }
        }
        stringList = arrayAux;
		ListView list = (ListView) findViewById(R.id.listSubTask);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList.toArray());
		list.setAdapter(adapter);
		return stringList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
	//Refresca la lista. Lo llama el navigationdrawer cuando se cierra NewTask
	
	public void refrescarLista() {
	// TODO Refrescar lista
		ListView lv = (ListView) findViewById(R.id.listView);
		
		Tarea[] list = ListaTareas.getTareas();
		List<String> nombres = new ArrayList<String>();
		
		for(int i = 0; i<list.length;i++) {
			nombres.add(list[i].getTarea());
		}
		
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres.toArray());
		lv.setAdapter(adapter);
		
	
	}





	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}
}

