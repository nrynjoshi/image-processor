package com.joshi.imageserver.util;

import com.joshi.imageserver.businesslogic.ImageCropUtil;
import com.joshi.imageserver.businesslogic.ImageService;
import com.joshi.imageserver.consts.ImageConst;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
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

    public void provideOrginalImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage= imageService.provideImage(path);
        prepareFile(fitImage,response,path);
    }

    public void provideMobileImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
        fitImage= ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
        prepareFile(fitImage,response,path);
    }

    public void provideDisplayImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("display.width"), envirValue("display.height")).toByteArray();
        fitImage= ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("display.width"), envirValue("display.height")).toByteArray();
        prepareFile(fitImage,response,path);
    }

    public void provideThumbnailImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
        fitImage= ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
        prepareFile(fitImage,response,path);
    }

    public void provideBannerImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);

        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
        fitImage= ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
        prepareFile(fitImage,response,path);
    }

    public void provideMediumImage( HttpServletResponse response,String path) throws Exception {
        prepareContentType(path, response);
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
        fitImage= ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
        prepareFile(fitImage,response,path);
    }


    public  void notFountPage(HttpServletResponse response) throws Exception {
        String path =ImageConst.getDEFAULTFILE();
        prepareContentType(path, response);
        byte[] fitImage= imageService.provideImage(path);
        prepareFile(fitImage,response,path);
    }


    private int envirValue(String type) {
        return Integer.parseInt(environment.getProperty("triplocator." + type));
    }

    private void prepareContentType(String filePath, HttpServletResponse response) throws Exception {

    }

    private void prepareFile(byte[] file, HttpServletResponse response,String filePath) throws Exception {

        try {
            Path path = new File(ImageConst.getIMAGESAVEPATH() + filePath).toPath();
            String mimeType = Files.probeContentType(path);

            ServletOutputStream servletOutputStream=response.getOutputStream();
            servletOutputStream.write(file);
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "attachment; filename="+filePath);
            servletOutputStream.flush();

        } catch (Exception x) {
            x.printStackTrace();
            response.setContentType("image/jpeg");
            throw  new Exception("not found ");
        }

    }

}
