package com.triplocator.imageserver.hanlder;

import com.triplocator.imageserver.businesslogic.ImageCropUtil;
import com.triplocator.imageserver.businesslogic.ImageService;
import com.triplocator.imageserver.consts.ImageConst;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class FetchImageHanlder {

    private final ImageService imageService;

    private final Environment environment;

    FetchImageHanlder(Environment environment, ImageService imageService) {
        this.imageService = imageService;

        this.environment = environment;
    }

    @GetMapping(value = "orginal/**")
    public @ResponseBody
    byte[] orginalRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("orginal/", "");
        prepareContentType(path, response);
        return imageService.provideImage(path);
    }

    @GetMapping(value = "mobile/**")
    public @ResponseBody
    byte[] mobileRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("mobile/", "");
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
        prepareContentType(path, response);
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("mobile.width"), envirValue("mobile.height")).toByteArray();
    }



    @GetMapping(value = "display/**")
    public @ResponseBody
    byte[] displayRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("display/", "");
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("display.width"), envirValue("display.height")).toByteArray();
        prepareContentType(path, response);
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("display.width"), envirValue("display.height")).toByteArray();
    }

    @GetMapping(value = "thumbnail/**")
    public @ResponseBody
    byte[] thumbnailRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("thumbnail/", "");
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
        prepareContentType(path, response);
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("thumbnail.width"), envirValue("thumbnail.height")).toByteArray();
    }

    @GetMapping(value = "banner/**")
    public @ResponseBody
    byte[] bannerRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("banner/", "");
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
        prepareContentType(path, response);
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("banner.width"), envirValue("banner.height")).toByteArray();
    }

    @GetMapping(value = "medium/**")
    public @ResponseBody
    byte[] mediumRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("medium/", "");
        byte[] fitImage = ImageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
        prepareContentType(path, response);
        return ImageCropUtil.smartCrop(new ByteArrayInputStream(fitImage), envirValue("medium.width"), envirValue("medium.height")).toByteArray();
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
