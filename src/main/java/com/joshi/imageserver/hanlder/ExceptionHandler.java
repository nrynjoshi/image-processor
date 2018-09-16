package com.joshi.imageserver.hanlder;

import com.joshi.imageserver.businesslogic.ImageCropUtil;
import com.joshi.imageserver.businesslogic.ImageService;
import com.joshi.imageserver.consts.ImageConst;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@ControllerAdvice
public class ExceptionHandler {
    private final ImageService imageService;
    private final Environment environment;

    ExceptionHandler(Environment environment, ImageService imageService) {
        this.imageService = imageService;
        this.environment = environment;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public byte[] notFoundEx(HttpServletResponse response) {
        try{
            String path =ImageConst.getDEFAULTFILE();
            prepareContentType(path, response);
            return imageService.provideImage(path);
        }catch (Exception x){
            return null;
        }

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public @ResponseBody
    byte[] mobileRetriveImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestFor = request.getRequestURI();
            String path =ImageConst.getDEFAULTFILE();
            if (requestFor.startsWith("mobile/")) {

                byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
                prepareContentType(path, response);
                return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
            } else if (requestFor.startsWith("medium/")) {

                byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
                prepareContentType(path, response);
                return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
            } else if (requestFor.startsWith("banner/")) {

                byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
                prepareContentType(path, response);
                return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
            } else if (requestFor.startsWith("thumbnail/")) {

                byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
                prepareContentType(path, response);
                return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
            } else if (requestFor.startsWith("display/")) {

                byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("display.width"), envirValue("display.height")).toByteArray();
                prepareContentType(path, response);
                return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("display.width"), envirValue("display.height")).toByteArray();
            } else if (requestFor.startsWith("original/")) {

                prepareContentType(path, response);
                return imageService.provideImage(path);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    private int envirValue(String type) {
        return Integer.parseInt(environment.getProperty("triplocator." + type));
    }

    private void prepareContentType(String filePath, HttpServletResponse response) {
        try {
            Path path = new File(ImageConst.getIMAGESAVEPATH() + filePath).toPath();
            String mimeType = Files.probeContentType(path);
            response.setContentType(mimeType);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
