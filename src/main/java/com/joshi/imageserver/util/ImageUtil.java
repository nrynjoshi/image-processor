package com.joshi.imageserver.util;

import com.joshi.imageserver.businesslogic.ImageCropUtil;
import com.joshi.imageserver.businesslogic.ImageService;
import com.joshi.imageserver.consts.ImageConst;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ImageUtil {

    private final Environment environment;
    private final ImageService imageService;

    ImageUtil(Environment environment, ImageService imageService) {
        this.environment = environment;
        this.imageService=imageService;
    }

    public byte[] provideOrginalImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        return imageService.provideImage(path);
    }

    public byte[] provideMobileImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();

    }

    public byte[] provideDisplayImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("display.width"), envirValue("display.height")).toByteArray();
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("display.width"), envirValue("display.height")).toByteArray();

    }

    public byte[] provideThumbnailImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();

    }

    public byte[] provideBannerImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("banner.width"), envirValue("banner.height")).toByteArray();

    }

    public byte[] provideMediumImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
    }


    public  byte[] notFountPage(HttpServletResponse response) throws Exception {
        String path =ImageConst.getDEFAULTFILE();
        prepareContentType(path, response);
        return imageService.provideImage(path);
    }


    private int envirValue(String type) {
        return Integer.parseInt(environment.getProperty("triplocator." + type));
    }

    private void prepareContentType(String filePath, HttpServletResponse response) throws Exception {
        try {
            Path path = new File(ImageConst.getIMAGESAVEPATH() + filePath).toPath();
            String mimeType = Files.probeContentType(path);
            response.setContentType(mimeType);
        } catch (Exception x) {
            x.printStackTrace();
            response.setContentType("image/jpeg");
            throw  new Exception("not found ");
        }
    }

}
