package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.upv.etsit.trabajoaplicusa.adapters.SectionContentAdapter;
import es.upv.etsit.trabajoaplicusa.models.SectionContent;
import java.util.ArrayList;
import java.util.List;

public class EventosActivity extends AppCompatActivity implements SectionContentAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private ImageView ivArtistImage;
    private TextView tvArtistName, tvArtistDescription, tvEventsTitle;
    private RecyclerView recyclerViewEvents;
    private SectionContentAdapter adapter;

    private String artistName;
    private String artistDescription;
    private String artistImage;
    private String festivalSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        getIntentData();
        initViews();
        setupToolbar();
        loadArtistInfo();
        loadArtistEvents();
    }

    private void getIntentData() {
        artistName = getIntent().getStringExtra("artist_name");
        artistDescription = getIntent().getStringExtra("artist_description");
        artistImage = getIntent().getStringExtra("artist_image");
        festivalSection = getIntent().getStringExtra("festival_section");
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        ivArtistImage = findViewById(R.id.ivArtistImage);
        tvArtistName = findViewById(R.id.tvArtistName);
        tvArtistDescription = findViewById(R.id.tvArtistDescription);
        tvEventsTitle = findViewById(R.id.tvEventsTitle);
        recyclerViewEvents = findViewById(R.id.recyclerViewEvents);

        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(artistName != null ? artistName : "Eventos del Artista");
        }
    }

    private void loadArtistInfo() {
        if (artistName != null) {
            tvArtistName.setText(artistName);
        }

        if (artistDescription != null) {
            tvArtistDescription.setText(artistDescription);
        }

        if (artistImage != null && !artistImage.isEmpty()) {
            ivArtistImage.setVisibility(ImageView.VISIBLE);
            if (artistImage.startsWith("drawable/")) {
                String drawableName = artistImage.replace("drawable/", "");
                int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                if (resourceId != 0) {
                    ivArtistImage.setImageResource(resourceId);
                } else {
                    ivArtistImage.setImageResource(R.drawable.ic_artista);
                }
            } else {
                ivArtistImage.setImageResource(R.drawable.ic_artista);
            }
        } else {
            ivArtistImage.setVisibility(ImageView.GONE);
        }
    }

    private void loadArtistEvents() {
        List<SectionContent> eventsList = createEventsList(artistName, festivalSection);

        adapter = new SectionContentAdapter(eventsList);
        adapter.setOnItemClickListener(this);
        recyclerViewEvents.setAdapter(adapter);

        tvEventsTitle.setText("Eventos de " + (artistName != null ? artistName : "este artista"));
    }

    private List<SectionContent> createEventsList(String artist, String festival) {
        List<SectionContent> events = new ArrayList<>();

        if (artist == null) artist = "Artista";

        switch (festival != null ? festival : "artistas") {
            case "mad_cool":
                events.add(new SectionContent(
                        "Mad Cool Festival 2025",
                        "Actuación principal en el escenario Madrid\nFecha: 15 de Julio 2025\nHora: 22:00",
                        "drawable/mad_cool",
                        "https://madcoolfestival.es/"
                ));
                break;

            case "primavera_sound":
                events.add(new SectionContent(
                        "Primavera Sound 2025",
                        "Concierto en el escenario Parc del Fòrum\nFecha: 3 de Junio 2025\nHora: 23:30",
                        "drawable/primavera_sound",
                        "https://www.primaverasound.com/"
                ));
                break;

            case "arenal_sound":
                events.add(new SectionContent(
                        "Arenal Sound 2025",
                        "Headliner en la playa de Burriana\nFecha: 30 de Julio 2025\nHora: 01:00",
                        "drawable/arenal_sound",
                        "https://www.arenalsound.com/"
                ));
                break;

            case "viña_rock":
                events.add(new SectionContent(
                        "Viña Rock 2025",
                        "Concierto en el escenario principal\nFecha: 1 de Mayo 2025\nHora: 22:30",
                        "drawable/vina_rock",
                        "https://www.vinarock.com/"
                ));
                break;

            case "resurrection":
                events.add(new SectionContent(
                        "Resurrection Fest 2025",
                        "Actuación épica en el escenario principal\nFecha: 25 de Junio 2025\nHora: 23:00",
                        "drawable/resurrection",
                        "https://www.resurrectionfest.es/"
                ));
                break;

            default:
                events.add(new SectionContent(
                        "Concierto Principal",
                        "Actuación principal del artista\nFecha: Por confirmar\nHora: Por confirmar",
                        "drawable/concert_stage"
                ));
                break;
        }

        return events;
    }

    @Override
    public void onItemClick(SectionContent content, int position) {
        if (content.getUrl() != null && !content.getUrl().isEmpty()) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(content.getUrl()));
            startActivity(browserIntent);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
