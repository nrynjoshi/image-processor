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

    ExceptionHandler(ImageUtil imageUtil) {
        this.imageUtil = imageUtil;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public void notFoundEx(HttpServletResponse response) {
        try {
            imageUtil.notFountPage(response);
        } catch (Exception x) {
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public @ResponseBody
    void mobileRetriveImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestFor = request.getRequestURI();
            response.setContentType("image/jpeg");
            String path = ImageConst.getDEFAULTFILE();
            if (requestFor.startsWith("/mobile/")) {
                 imageUtil.provideMobileImage(response, path);
            } else if (requestFor.startsWith("/medium/")) {
                 imageUtil.provideMediumImage(response, path);
            } else if (requestFor.startsWith("/banner/")) {
                 imageUtil.provideBannerImage(response, path);
            } else if (requestFor.startsWith("/thumbnail/")) {
                 imageUtil.provideThumbnailImage(response, path);
            } else if (requestFor.startsWith("/display/")) {
                 imageUtil.provideDisplayImage(response, path);
            } else if (requestFor.startsWith("/original/")) {
                 imageUtil.provideOrginalImage(response, path);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

}
