package com.example.lab4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab4.R;
import com.example.lab4.network.ApiService;
import com.example.lab4.network.ApiClient;
import com.example.lab4.adapters.LocationAdapter;
import com.example.lab4.models.Location;
import com.example.lab4.models.LocationResponse; // Asegúrate de tener esta clase

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    private RecyclerView recyclerView;
    private LocationAdapter adapter;
    private EditText etSearchLocation;
    private Button btnSearch;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewLocation);
        etSearchLocation = root.findViewById(R.id.etSearchLocation);
        btnSearch = root.findViewById(R.id.btnSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa ApiService
        apiService = ApiClient.getClient().create(ApiService.class);

        btnSearch.setOnClickListener(v -> {
            String query = etSearchLocation.getText().toString();
            fetchLocationData(query);
        });

        return root;
    }

    public void fetchLocationData(String query) {
        apiService.getLocationData("ec24b1c6dd8a4d528c1205500250305", query)
                .enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Location> locationList = response.body();

                            if (!locationList.isEmpty()) {
                                // Usamos el adaptador para mostrar todas las ubicaciones
                                LocationAdapter adapter = new LocationAdapter(locationList, LocationFragment.this);

                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "No locations found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Location>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }






}
