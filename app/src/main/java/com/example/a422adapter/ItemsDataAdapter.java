package com.example.a422adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataAdapter extends BaseAdapter {


    private List<ItemData> items;
    private LayoutInflater inflater;

    ItemsDataAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }


    private void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return items.size();
    }

    // Т
    @Override
    public ItemData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int finalPusition = position;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.sample_list, parent, false);
        }

        ItemData itemData = items.get(position);

        ImageView image = view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        Button button = view.findViewById(R.id.btn);

        image.setImageDrawable(itemData.getImage());
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(finalPusition);
            }
        });
        return view;
    }


    void showItemData(int position) {
        ItemData itemData = getItem(position);
        Toast.makeText(inflater.getContext(),
                "Title: " + itemData.getTitle() + "\n" +
                        "Subtitle: " + itemData.getSubtitle() + "\n" +
                        "Button: " + itemData.getButton(),
                Toast.LENGTH_SHORT).show();
    }
}
