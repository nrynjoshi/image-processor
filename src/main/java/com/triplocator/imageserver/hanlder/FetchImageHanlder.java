package com.triplocator.imageserver.hanlder;

import com.triplocator.imageserver.businesslogic.ImageCropUtil;
import com.triplocator.imageserver.businesslogic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class FetchImageHanlder {

    @Autowired private ImageService imageService;
    @Autowired private ImageCropUtil imageCropUtil;

    private Environment environment;

    FetchImageHanlder(Environment environment){
        this.environment=environment;
    }

    @GetMapping(value = "mobile/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] mobileRetriveImage() throws IOException {
       String path="C:/Users/Narayan Joshi/Pictures/";
       //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("mobile.width"),envirValue("mobile.height")).toByteArray();
        return imageCropUtil.smartCrop(new ByteArrayInputStream(fitImage),envirValue("mobile.width"),envirValue("mobile.height")).toByteArray();
    }

    @GetMapping(value = "display/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] displayRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("display.width"),envirValue("display.height")).toByteArray();
        return imageCropUtil.smartCrop(new ByteArrayInputStream(fitImage),envirValue("display.width"),envirValue("display.height")).toByteArray();
    }

    @GetMapping(value = "thumbnail/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] thumbnailRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("thumbnail.width"),envirValue("thumbnail.height")).toByteArray();
        return imageCropUtil.smartCrop(new ByteArrayInputStream(fitImage),envirValue("thumbnail.width"),envirValue("thumbnail.height")).toByteArray();
    }

    @GetMapping(value = "banner/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] bannerRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("banner.width"),envirValue("banner.height")).toByteArray();
        return imageCropUtil.smartCrop(new ByteArrayInputStream(fitImage),envirValue("banner.width"),envirValue("banner.height")).toByteArray();
    }

    @GetMapping(value = "medium/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] mediumRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("medium.width"),envirValue("medium.height")).toByteArray();
        return imageCropUtil.smartCrop(new ByteArrayInputStream(fitImage),envirValue("medium.width"),envirValue("medium.height")).toByteArray();
    }

    @GetMapping(value = "v1/mobile/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] v1mobileRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("mobile.width"),envirValue("mobile.height")).toByteArray();
        return fitImage;
    }

    @GetMapping(value = "v1/display/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] v1displayRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("display.width"),envirValue("display.height")).toByteArray();
        return fitImage;
    }

    @GetMapping(value = "v1/thumbnail/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] v1thumbnailRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("thumbnail.width"),envirValue("thumbnail.height")).toByteArray();
        return fitImage;
    }

    @GetMapping(value = "v1/banner/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] v1bannerRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image

        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("banner.width"),envirValue("banner.height")).toByteArray();
        return fitImage;
    }

    @GetMapping(value = "v1/medium/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] v1mediumRetriveImage() throws IOException {
        String path="C:/Users/Narayan Joshi/Pictures/";
        //first scale as per screen fit then crop the image
        byte[] fitImage= imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),envirValue("medium.width"),envirValue("medium.height")).toByteArray();
        return fitImage;
    }

    private int envirValue(String type){
       return Integer.parseInt(environment.getProperty("triplocator."+type));
    }



}
