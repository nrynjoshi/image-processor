package com.joshi.imageserver.request;

import java.util.List;

public class ImageSaveRequest {

    private List<String> folderPath;
    private String base64Image;
    private String fileName;
    private String extension;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
