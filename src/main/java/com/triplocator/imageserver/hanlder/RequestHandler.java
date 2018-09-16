package com.triplocator.imageserver.hanlder;

import com.triplocator.imageserver.businesslogic.ImageService;
import com.triplocator.imageserver.request.ImageSaveRequest;
import com.triplocator.imageserver.response.GeneralRespnse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RequestHandler {

    @Autowired private ImageService imageService;



    @PostMapping("image")
    public GeneralRespnse saveImage(RequestEntity<ImageSaveRequest> request){
        String finalPath=null;
        GeneralRespnse  respnse=new GeneralRespnse();
        try{

            finalPath= imageService.saveImage(request.getBody());
        }catch (Exception x){
            respnse.setMessage(x.getMessage());
            //respnse.setError(x.getCause().toString());
            x.printStackTrace();
        }
       if(finalPath==null){
           respnse.setStatus(500);
       }else{
           respnse.setImageurl(finalPath);
           respnse.setStatus(200);
       }
        return respnse;
    }

    @DeleteMapping("image")
    public GeneralRespnse deleteImage(RequestEntity<String> path){
        GeneralRespnse  respnse=new GeneralRespnse();
        try{
             imageService.deleteImage(path.getBody());
            respnse.setStatus(200);
            respnse.setMessage("success");
        }catch (Exception x){
            respnse.setMessage(x.getMessage());
            respnse.setError(x.getCause().toString());
            respnse.setStatus(500);
            x.printStackTrace();
        }
        return respnse;
    }

    @GetMapping("/image/check/occurance")
    public GeneralRespnse retriveImage(RequestEntity<String> path){
        GeneralRespnse  respnse=new GeneralRespnse();
        try{
            //imageService.checkImageNameExist(path.getBody());
            respnse.setMessage("success");
            respnse.setStatus(200);
        }catch (Exception x){
            respnse.setMessage(x.getMessage());
            respnse.setError(x.getCause().toString());
            respnse.setStatus(404);
            x.printStackTrace();
        }
        return respnse;
    }



}
