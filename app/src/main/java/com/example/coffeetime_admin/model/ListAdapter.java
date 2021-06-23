package com.example.coffeetime_admin.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime_admin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Product> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Product> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Product> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton iconImageView;
        TextView name, stock, category, price;

        public  ViewHolder(View itemView){
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.name);
            stock = itemView.findViewById(R.id.stock);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
        }

        public void bindData(final Product item){
            Picasso.get().load(item.getPhotoURI()).into(iconImageView);
            name.setText(item.getName());
            stock.setText("Stock: " + item.getStock());
            category.setText("Categoria: " + item.getCategory());
            price.setText("Precio: " + item.getPrice());
        }
    }
}