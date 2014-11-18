package com.manchateam.youdo;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
 
public class AdapterCheckBox extends ArrayAdapter<ModelCheckBox> {
 
        private final Context context;
        private final ArrayList<ModelCheckBox> modelsArrayList;
 
        public AdapterCheckBox(Context context, ArrayList<ModelCheckBox> modelsArrayList) {
 
            super(context, R.layout.adapter_checkbox, modelsArrayList);
 
            this.context = context;
            this.modelsArrayList = modelsArrayList;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
 
            View rowView = null;
            if(!modelsArrayList.get(position).isGroupHeader()){
                rowView = inflater.inflate(R.layout.adapter_checkbox, parent, false);
 
                // 3. Get icon,title & counter views from the rowView
                //ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon); 
                TextView titleView = (TextView) rowView.findViewById(R.id.item_subtarea);
                CheckBox counterView = (CheckBox) rowView.findViewById(R.id.checkBox1);
 
                // 4. Set the text for textView 
               // imgView.setImageResource(modelsArrayList.get(position).getIcon());
                titleView.setText(modelsArrayList.get(position).getTitle());
                counterView.setChecked(modelsArrayList.get(position).getCounter());
            }
            else{
            		/* POR AHORA NADA
                    rowView = inflater.inflate(R.layout.group_header_item, parent, false);
                    TextView titleView = (TextView) rowView.findViewById(R.id.header);
                    titleView.setText(modelsArrayList.get(position).getTitle());
            	 	*/
            }
 
            // 5. retrn rowView
            return rowView;
        }
}