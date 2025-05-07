package com.example.lab4.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.R;
import com.example.lab4.models.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> locationList;
    private Fragment context;

    public LocationAdapter(List<Location> locationList, Fragment context) {
        this.locationList = locationList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = locationList.get(position);

        // Set ID
        holder.idTextView.setText("ID: " + location.getId());

        // Set Name
        holder.nameTextView.setText("Name: " + location.getName());

        // Set Region
        holder.regionTextView.setText("Region: " + location.getRegion());

        // Set Country
        holder.countryTextView.setText("Country: " + location.getCountry());

        // Set Latitude
        holder.latTextView.setText("Lat: " + location.getLat());

        // Set Longitude
        holder.lonTextView.setText("Lon: " + location.getLon());

        // Set URL
        holder.urlTextView.setText("URL: " + location.getUrl());


    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView, regionTextView, countryTextView, latTextView, lonTextView, urlTextView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Inicializar los TextViews
            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            regionTextView = itemView.findViewById(R.id.regionTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            latTextView = itemView.findViewById(R.id.latTextView);
            lonTextView = itemView.findViewById(R.id.lonTextView);
            urlTextView = itemView.findViewById(R.id.urlTextView);

            // Inicializar el CardView
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
