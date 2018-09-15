package com.triplocator.imageserver.request;

import java.util.List;

public class ImageSaveRequest {

    private List<String> folderPath;
    private String base64Image;
    private Boolean isImage;

    public Boolean getImage() {
        return isImage;
    }

    public void setImage(Boolean image) {
        isImage = image;
    }

    public List<String> getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(List<String> folderPath) {
        this.folderPath = folderPath;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
