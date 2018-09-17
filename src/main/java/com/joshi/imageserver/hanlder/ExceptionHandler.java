package com.joshi.imageserver.hanlder;

import com.joshi.imageserver.consts.ImageConst;
import com.joshi.imageserver.util.ImageUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@ControllerAdvice
public class ExceptionHandler {
    private final ImageUtil imageUtil;

    ExceptionHandler( ImageUtil imageUtil) {
        this.imageUtil = imageUtil;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public byte[] notFoundEx(HttpServletResponse response) {
        try{
            return imageUtil.notFountPage(response);
        }catch (Exception x){
            return null;
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public @ResponseBody
    byte[] mobileRetriveImage(HttpServletRequest request,HttpServletResponse response) {
        try {
            String requestFor = request.getRequestURI();
            response.setContentType("image/jpeg");
            String path=ImageConst.getDEFAULTFILE();
            if (requestFor.startsWith("/mobile/")) {
               return imageUtil.provideMobileImage(response,path);
            } else if (requestFor.startsWith("/medium/")) {
                return imageUtil.provideMediumImage(response,path);
            } else if (requestFor.startsWith("/banner/")) {
                return imageUtil.provideBannerImage(response,path);
            } else if (requestFor.startsWith("/thumbnail/")) {
                return imageUtil.provideThumbnailImage(response,path);
            } else if (requestFor.startsWith("/display/")) {
                return imageUtil.provideDisplayImage(response,path);
            } else if (requestFor.startsWith("/original/")) {
                return imageUtil.provideOrginalImage(response,path);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

}
