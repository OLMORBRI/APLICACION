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
    private OnItemClickListener listener;
    private String contentType; // "artistas", "entradas", etc.

    public interface OnItemClickListener {
        void onItemClick(SectionContent content, int position);
    }

    public SectionContentAdapter(List<SectionContent> contentList) {
        this.contentList = contentList;
        this.contentType = "default";
    }

    public SectionContentAdapter(List<SectionContent> contentList, String contentType) {
        this.contentList = contentList;
        this.contentType = contentType;
    }

    public SectionContentAdapter(List<SectionContent> contentList, OnItemClickListener listener, String contentType) {
        this.contentList = contentList;
        this.listener = listener;
        this.contentType = contentType;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        return contentList != null ? contentList.size() : 0;
    }

    public void updateData(List<SectionContent> newContentList) {
        this.contentList = newContentList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvExtraInfo;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvContentTitle);
            tvDescription = itemView.findViewById(R.id.tvContentDescription);
            tvExtraInfo = itemView.findViewById(R.id.tvContentExtraInfo); // TextView adicional para precio, género, etc.
            ivImage = itemView.findViewById(R.id.ivContentImage);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(contentList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void bind(SectionContent content) {
            tvTitle.setText(content.getTitle());
            tvDescription.setText(content.getDescription());

            if (tvExtraInfo != null) {
                if (content.getExtraInfo() != null && !content.getExtraInfo().isEmpty()) {
                    tvExtraInfo.setVisibility(View.VISIBLE);

                    switch (contentType) {
                        case "entradas":
                            tvExtraInfo.setText(content.getExtraInfo()); // Precio
                            tvExtraInfo.setTextColor(itemView.getContext().getResources().getColor(R.color.accent, null));
                            break;
                        case "artistas":
                            tvExtraInfo.setText(content.getExtraInfo()); // Género musical
                            tvExtraInfo.setTextColor(itemView.getContext().getResources().getColor(R.color.text_secondary, null));
                            break;
                        default:
                            tvExtraInfo.setText(content.getExtraInfo());
                            break;
                    }
                } else {
                    tvExtraInfo.setVisibility(View.GONE);
                }
            }

            if (content.getImageUrl() != null && !content.getImageUrl().isEmpty()) {
                ivImage.setVisibility(View.VISIBLE);

                if (content.getImageUrl().startsWith("drawable/")) {
                    String drawableName = content.getImageUrl().replace("drawable/", "");
                    int resourceId = itemView.getContext().getResources()
                            .getIdentifier(drawableName, "drawable", itemView.getContext().getPackageName());
                    if (resourceId != 0) {
                        ivImage.setImageResource(resourceId);
                    } else {
                        setDefaultImage();
                    }
                } else {
                    setDefaultImage();
                }
            } else {
                setDefaultImage();
            }
        }

        private void setDefaultImage() {
            switch (contentType) {
                case "entradas":
                    ivImage.setImageResource(R.drawable.ic_festival);
                    break;
                case "artistas":
                    ivImage.setImageResource(R.drawable.ic_artista);
                    break;
                default:
                    ivImage.setImageResource(R.drawable.ic_default);
                    break;
            }
        }
    }
}
