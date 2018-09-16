package com.joshi.imageserver.hanlder;

import com.joshi.imageserver.businesslogic.ImageService;
import com.joshi.imageserver.request.ImageSaveRequest;
import com.joshi.imageserver.response.GeneralRespnse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class RequestHandler {

    final private ImageService imageService;

    @Value("image.manupulation.token")
    private String token;

    RequestHandler(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("image")
    @ResponseBody
    public GeneralRespnse saveImage(RequestEntity<ImageSaveRequest> dto, HttpServletRequest request) throws Exception {
        String finalPath = null;
        GeneralRespnse respnse = isImageManupulationAvailable(request);
        if (respnse.getError() != null) {
            return respnse;
        }
        try {

            finalPath = imageService.saveImage(dto.getBody());
        } catch (Exception x) {
            respnse.setMessage(x.getMessage());
            x.printStackTrace();

        }
        if (finalPath == null) {
            respnse.setStatus(500);
        } else {
            respnse.setImageurl(finalPath);
            respnse.setStatus(201);
        }
        return respnse;
    }

    @DeleteMapping("image")
    @ResponseBody
    public GeneralRespnse deleteImage(RequestEntity<String> path, HttpServletRequest request) {

        GeneralRespnse respnse = isImageManupulationAvailable(request);
        if (respnse.getError() != null) {
            return respnse;
        }
        try {
            imageService.deleteImage(path.getBody());
            respnse.setStatus(200);
            respnse.setMessage("success");
        } catch (Exception x) {
            respnse.setMessage(x.getMessage());
            respnse.setError(x.getCause().toString());
            respnse.setStatus(500);
            x.printStackTrace();
        }
        return respnse;
    }

    private GeneralRespnse isImageManupulationAvailable(HttpServletRequest request) {

        final String sendToken = request.getHeader("imgtoken");
        if (token.equalsIgnoreCase(sendToken)) {
            return new GeneralRespnse();
        }
        GeneralRespnse respnse = new GeneralRespnse();
        respnse.setError(" 401 Unauthorized");
        respnse.setMessage("The request requires user authentication");
        respnse.setStatus(401);
        return respnse;

    }


}
