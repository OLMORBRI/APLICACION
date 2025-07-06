package es.upv.etsit.trabajoaplicusa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.upv.etsit.trabajoaplicusa.adapters.SectionContentAdapter;
import es.upv.etsit.trabajoaplicusa.models.SectionContent;
import java.util.ArrayList;
import java.util.List;

public class ArtistasActivity extends AppCompatActivity implements SectionContentAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private TextView tvSectionTitle, tvSectionDescription;
    private RecyclerView recyclerViewContent;
    private SectionContentAdapter adapter;
    private String currentSection;
    private SearchView searchView;
    private List<SectionContent> originalContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistas);

        currentSection = getIntent().getStringExtra("section");

        initViews();
        setupToolbar();
        loadSectionContent();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        tvSectionTitle = findViewById(R.id.tituloArtistas);
        tvSectionDescription = findViewById(R.id.eslogan);
        recyclerViewContent = findViewById(R.id.recyclerViewContent);
        searchView = findViewById(R.id.searchViewArtists);

        recyclerViewContent.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            String title = getSectionTitle(currentSection);
            getSupportActionBar().setTitle(title);
        }
    }

    private String getSectionTitle(String section) {
        if (section == null) return "Artistas";

        switch (section) {
            case "mad_cool":
                return "Mad Cool Festival";
            case "primavera_sound":
                return "Primavera Sound";
            case "arenal_sound":
                return "Arenal Sound";
            case "viña_rock":
                return "Viña Rock";
            case "resurrection":
                return "Resurrection Fest";
            case "artistas":
                return "Todos los Artistas";
            default:
                return "Artistas";
        }
    }

    private void loadSectionContent() {
        List<SectionContent> contentList = createArtistsList(currentSection);
        originalContentList = new ArrayList<>(contentList);

        adapter = new SectionContentAdapter(contentList);
        adapter.setOnItemClickListener(this);
        recyclerViewContent.setAdapter(adapter);

        setupSearch();
        updateSectionDescription(currentSection);
    }

    private void updateSectionDescription(String section) {
        String description = getSectionDescription(section);
        tvSectionDescription.setText(description);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterArtists(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterArtists(newText);
                return true;
            }
        });
    }

    private String getSectionDescription(String section) {
        if (section == null) return "Descubre nuestros increíbles artistas";

        switch (section) {
            case "mad_cool":
                return "Los mejores artistas del Mad Cool Festival";
            case "primavera_sound":
                return "Lineup completo del Primavera Sound";
            case "arenal_sound":
                return "Artistas confirmados para Arenal Sound";
            case "viña_rock":
                return "Las estrellas que tocarán en Viña Rock";
            case "resurrection":
                return "El poder del metal en Resurrection Fest";
            case "artistas":
                return "Todos los artistas de nuestros festivales";
            default:
                return "Descubre nuestros increíbles artistas";
        }
    }

    private List<SectionContent> createArtistsList(String section) {
        List<SectionContent> artists = new ArrayList<>();

        if (section == null) section = "artistas";

        switch (section) {
            case "mad_cool":
                artists.add(new SectionContent("Arctic Monkeys", "Banda británica de rock alternativo", "drawable/arctic_monkeys"));
                artists.add(new SectionContent("Billie Eilish", "Cantante y compositora estadounidense", "drawable/billie_eilish"));
                artists.add(new SectionContent("The Killers", "Banda estadounidense de rock", "drawable/the_killers"));
                artists.add(new SectionContent("Dua Lipa", "Cantante británica de pop", "drawable/dua_lipa"));
                break;

            case "primavera_sound":
                artists.add(new SectionContent("Rosalía", "Cantante española de flamenco urbano", "drawable/rosalia"));
                artists.add(new SectionContent("Bad Bunny", "Reggaetonero puertorriqueño", "drawable/bad_bunny"));
                artists.add(new SectionContent("Tame Impala", "Proyecto musical australiano", "drawable/tame_impala"));
                artists.add(new SectionContent("Lorde", "Cantautora neozelandesa", "drawable/lorde"));
                break;

            case "arenal_sound":
                artists.add(new SectionContent("C. Tangana", "Rapero y cantante español", "drawable/c_tangana"));
                artists.add(new SectionContent("Nathy Peluso", "Cantante argentina", "drawable/nathy_peluso"));
                artists.add(new SectionContent("Vetusta Morla", "Banda española de rock", "drawable/vetusta_morla"));
                artists.add(new SectionContent("Love of Lesbian", "Banda catalana de indie pop", "drawable/love_of_lesbian"));
                break;

            case "viña_rock":
                artists.add(new SectionContent("Mägo de Oz", "Banda española de folk metal", "drawable/mago_de_oz"));
                artists.add(new SectionContent("Extremoduro", "Banda española de rock", "drawable/extremoduro"));
                artists.add(new SectionContent("Marea", "Banda española de rock", "drawable/marea"));
                artists.add(new SectionContent("Platero y Tú", "Banda española de rock", "drawable/platero_y_tu"));
                break;

            case "resurrection":
                artists.add(new SectionContent("Metallica", "Banda estadounidense de metal", "drawable/metallica"));
                artists.add(new SectionContent("Iron Maiden", "Banda británica de heavy metal", "drawable/iron_maiden"));
                artists.add(new SectionContent("Slipknot", "Banda estadounidense de metal", "drawable/slipknot"));
                artists.add(new SectionContent("System of a Down", "Banda estadounidense de metal alternativo", "drawable/system_of_a_down"));
                break;

            case "artistas":
            default:
                artists.add(new SectionContent("Arctic Monkeys", "Mad Cool Festival - Rock alternativo", "drawable/arctic_monkeys"));
                artists.add(new SectionContent("Rosalía", "Primavera Sound - Flamenco urbano", "drawable/rosalia"));
                artists.add(new SectionContent("C. Tangana", "Arenal Sound - Hip hop español", "drawable/c_tangana"));
                artists.add(new SectionContent("Mägo de Oz", "Viña Rock - Folk metal", "drawable/mago_de_oz"));
                artists.add(new SectionContent("Metallica", "Resurrection Fest - Heavy metal", "drawable/metallica"));
                artists.add(new SectionContent("Billie Eilish", "Mad Cool Festival - Pop alternativo", "drawable/billie_eilish"));
                artists.add(new SectionContent("Bad Bunny", "Primavera Sound - Reggaeton", "drawable/bad_bunny"));
                artists.add(new SectionContent("Vetusta Morla", "Arenal Sound - Rock español", "drawable/vetusta_morla"));
                break;
        }

        return artists;
    }

    private void filterArtists(String query) {
        if (originalContentList == null) return;

        List<SectionContent> filtered = new ArrayList<>();
        for (SectionContent sc : originalContentList) {
            if (sc.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(sc);
            }
        }
        adapter.updateData(filtered);
    }

    @Override
    public void onItemClick(SectionContent content, int position) {
        String artistName = content.getTitle();
        Toast.makeText(this, "Viendo eventos de: " + artistName, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, EventosActivity.class);
        intent.putExtra("artist_name", artistName);
        intent.putExtra("artist_description", content.getDescription());
        intent.putExtra("artist_image", content.getImageUrl());
        intent.putExtra("festival_section", currentSection);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}