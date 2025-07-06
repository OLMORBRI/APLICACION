package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.upv.etsit.trabajoaplicusa.adapters.SectionContentAdapter;
import es.upv.etsit.trabajoaplicusa.models.SectionContent;
import java.util.ArrayList;
import java.util.List;

public class EntradasActivity extends AppCompatActivity implements SectionContentAdapter.OnItemClickListener {

    private RecyclerView recyclerViewEntradas;
    private SectionContentAdapter adapter;
    private List<SectionContent> listaEntradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        initViews();
        setupRecyclerView();
        cargarEntradas();
    }

    private void initViews() {
        recyclerViewEntradas = findViewById(R.id.recyclerViewEntradas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Entradas Disponibles");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupRecyclerView() {
        listaEntradas = new ArrayList<>();

        adapter = new SectionContentAdapter(listaEntradas, this, "entradas");
        recyclerViewEntradas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEntradas.setAdapter(adapter);
    }

    private void cargarEntradas() {

        listaEntradas.add(new SectionContent(
                "Festival Internacional de Benicàssim",
                "Del 18 al 21 de Julio 2025 • Benicàssim, Castellón",
                "drawable/fib_festival", // o URL si tienes
                "https://www.fiberfib.com/entradas",
                "Desde 89€"
        ));

        listaEntradas.add(new SectionContent(
                "Mad Cool Festival",
                "Del 10 al 12 de Julio 2025 • Madrid",
                "drawable/madcool_festival",
                "https://www.madcoolfestival.es/entradas",
                "Desde 120€"
        ));

        listaEntradas.add(new SectionContent(
                "Primavera Sound",
                "Del 5 al 7 de Junio 2025 • Barcelona",
                "drawable/primavera_festival",
                "https://www.primaverasound.com/es/entradas",
                "Desde 150€"
        ));

        listaEntradas.add(new SectionContent(
                "Resurrection Fest",
                "Del 25 al 28 de Junio 2025 • Viveiro, Lugo",
                "drawable/resurrection_festival",
                "https://www.resurrectionfest.es/entradas",
                "Desde 95€"
        ));

        listaEntradas.add(new SectionContent(
                "Arenal Sound",
                "Del 30 de Julio al 3 de Agosto 2025 • Burriana, Castellón",
                "drawable/arenal_festival",
                "https://www.arenalsound.com/entradas",
                "Desde 75€"
        ));

        listaEntradas.add(new SectionContent(
                "Viña Rock",
                "Del 1 al 3 de Mayo 2025 • Villarrobledo, Albacete",
                "drawable/vina_rock_festival",
                "https://www.vinarock.com/entradas",
                "Desde 65€"
        ));

        adapter.updateData(listaEntradas);
    }

    @Override
    public void onItemClick(SectionContent content, int position) {
        if (content.getUrl() != null && !content.getUrl().isEmpty()) {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(content.getUrl()));
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Error al abrir el enlace de compra", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No hay enlace de compra disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}