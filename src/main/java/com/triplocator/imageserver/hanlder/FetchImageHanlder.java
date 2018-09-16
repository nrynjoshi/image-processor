package com.triplocator.imageserver.hanlder;

import com.triplocator.imageserver.businesslogic.ImageCropUtil;
import com.triplocator.imageserver.businesslogic.ImageService;
import com.triplocator.imageserver.response.GeneralRespnse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = {"mobile","display","thumbnails","banner","medium"})
public class FetchImageHanlder {

    @Autowired private ImageService imageService;
    @Autowired private ImageCropUtil imageCropUtil;

    @GetMapping(value = "/test",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] retriveImage(@RequestParam("w") int w,@RequestParam("h") int h) throws IOException {
       String path="C:/Users/Narayan Joshi/Pictures/";
        return imageCropUtil.fit(new ByteArrayInputStream(imageService.provideImage(path)),w,h).toByteArray();
        //return imageService.provideImage(path);


    }



}
