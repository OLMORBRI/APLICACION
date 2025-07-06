package es.upv.etsit.trabajoaplicusa.models;

public class SectionContent {
    private String title;
    private String description;
    private String imageUrl;
    private String url;
    private String extraInfo;

    public SectionContent(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public SectionContent(String title, String description, String imageUrl, String url) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public SectionContent(String title, String description, String imageUrl, String url, String extraInfo) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.url = url;
        this.extraInfo = extraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}