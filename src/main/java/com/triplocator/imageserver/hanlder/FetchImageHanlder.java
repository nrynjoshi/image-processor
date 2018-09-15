package com.triplocator.imageserver.hanlder;

import com.triplocator.imageserver.response.GeneralRespnse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"mobile","display","thumbnails","banner","medium"})
public class FetchImageHanlder {

    @GetMapping("/**")
    public GeneralRespnse retriveImage(){

        return null;
    }

}
