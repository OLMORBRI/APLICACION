package es.upv.etsit.trabajoaplicusa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventosActivity extends AppCompatActivity {

    ListView listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        listaEventos = findViewById(R.id.listaEventos);

        // Lista vacía (por ahora)
        ArrayList<String> eventos = new ArrayList<>();

        // Adapter vacío para evitar errores
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                eventos
        );

        listaEventos.setAdapter(adaptador);
    }
}