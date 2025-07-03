package es.upv.etsit.trabajoaplicusa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import es.upv.etsit.trabajoaplicusa.R;
import es.upv.etsit.trabajoaplicusa.models.SectionContent;
import java.util.List;

public class SectionContentAdapter extends RecyclerView.Adapter<SectionContentAdapter.ViewHolder> {

    private List<SectionContent> contentList;

    public SectionContentAdapter(List<SectionContent> contentList) {
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionContent content = contentList.get(position);
        holder.bind(content);
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvContentTitle);
            tvDescription = itemView.findViewById(R.id.tvContentDescription);
            ivImage = itemView.findViewById(R.id.ivContentImage);
        }

        public void bind(SectionContent content) {
            tvTitle.setText(content.getTitle());
            tvDescription.setText(content.getDescription());

            // Si hay imagen, mostrarla (usar Glide o similar)
            if (content.getImageUrl() != null && !content.getImageUrl().isEmpty()) {
                ivImage.setVisibility(View.VISIBLE);
                // Glide.with(itemView.getContext()).load(content.getImageUrl()).into(ivImage);
            } else {
                ivImage.setVisibility(View.GONE);
            }
        }
    }
}