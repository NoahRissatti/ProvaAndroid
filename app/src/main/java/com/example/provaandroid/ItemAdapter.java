package com.example.provaandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.provaandroid.Repository.DatabaseHelper;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private DatabaseHelper databaseHelper;

    public ItemAdapter(Context context, List<Item> itemList, DatabaseHelper dbHelper) {
        super(context, 0, itemList);
        this.databaseHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Item item = getItem(position);

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.getText());
        String colorName = item.getOption();
        int textColor = ColorUtility.getColorByName(colorName);
        textView.setTextColor(textColor);

        return convertView;
    }

    public void removeItem(int position) {
        Item itemToRemove = getItem(position);
        if (itemToRemove != null) {
            String itemId = itemToRemove.getId();
            databaseHelper.deleteItem(itemId);

            remove(itemToRemove);
        }
    }
}

