package com.example.finalprojectgroup6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        //Declaring variables
        private ArrayList<CustomAdapterModel> customAdapterModels;
        private final Context context;
        private static final DecimalFormat df2 = new DecimalFormat("#.##");

    public CustomAdapter(ArrayList<CustomAdapterModel> currencyRVModelArrayList, Context context) {
            this.customAdapterModels = currencyRVModelArrayList;
            this.context = context;
        }

        public void filterList(ArrayList<CustomAdapterModel> filteredList){
            customAdapterModels = filteredList;
            notifyDataSetChanged();
        }

        //ViewHolder for CardView view properties
        @NonNull
        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false);
            return new ViewHolder(view);

        }

        //Binding values to ViewHolder
        @Override
        public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
            CustomAdapterModel customAdapterModel = customAdapterModels.get(position);
            holder.currencyNameTV.setText(customAdapterModel.getName());
            holder.symbolTV.setText(customAdapterModel.getSymbol());
            holder.rateTV.setText("$" + df2.format(customAdapterModel.getPrice()));
        }

        //Getting count of array list
        @Override
        public int getItemCount() {
            return customAdapterModels.size();
        }

        //Connecting widgets by ID
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView currencyNameTV, symbolTV, rateTV;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                currencyNameTV = itemView.findViewById(R.id.currencyName);
                symbolTV = itemView.findViewById(R.id.currencyFullName);
                rateTV = itemView.findViewById(R.id.currencyPrice);
            }
        }
    }
