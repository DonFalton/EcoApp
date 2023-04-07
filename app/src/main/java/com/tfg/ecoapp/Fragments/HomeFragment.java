package com.tfg.ecoapp.Fragments;

//para la pantalla de inicio con fecha, hora, tiempo y consejos aleatorios.

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.myapp.R;

public class HomeFragment extends Fragment {

    private TextView dateTextView;
    private TextView timeTextView;
    private TextView weatherTextView;
    private TextView tipTextView;
    private ImageView motivationalImageView;

    public HomeFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateTextView = view.findViewById(R.id.dateTextView);
        timeTextView = view.findViewById(R.id.timeTextView);
        weatherTextView = view.findViewById(R.id.weatherTextView);
        tipTextView = view.findViewById(R.id.tipTextView);
        motivationalImageView = view.findViewById(R.id.motivationalImageView);

        // Aquí se puede actualizar los TextViews e ImageView con la información correspondiente
        // Por ejemplo, obtener la fecha, hora y tiempo actual y actualizar los TextViews
        // un consejo aleatorio y una imagen motivadora y actualizar
        // el tipTextView y el motivationalImageView, respectivamente
    }
}
