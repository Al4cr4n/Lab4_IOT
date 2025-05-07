package com.example.lab4;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIngresar;
    private TextView tvNombreApp;
    private TextView tvElaboradoPor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar = findViewById(R.id.btnIngresar);
        tvNombreApp = findViewById(R.id.tvNombreApp);
        tvElaboradoPor = findViewById(R.id.tvElaboradoPor);

        tvNombreApp.setText("Tele-Weather");

        // Validar conexión a Internet
        if (!isConnected()) {
            showNoConnectionDialog();
        }

        btnIngresar.setOnClickListener(v -> {
            // Redirigir a la AppActivity
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            startActivity(intent);
        });
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showNoConnectionDialog() {
        new AlertDialog.Builder(this)
                .setMessage("No hay conexión a Internet. Dirígete a los ajustes.")
                .setCancelable(false)
                .setPositiveButton("Configuración", (dialog, id) -> {
                    startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                })
                .create()
                .show();
    }
}
