package com.joshi.imageserver.hanlder;


import com.joshi.imageserver.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class FetchImageHanlder {

    private final ImageUtil imageUtil;


    FetchImageHanlder(ImageUtil imageUtil) {
        this.imageUtil = imageUtil;

    }

    @GetMapping(value = "original/**")
    public @ResponseBody
    byte[] orginalRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/original/", "");
        return imageUtil.provideOrginalImage(response,path);
    }

    @GetMapping(value = "mobile/**")
    public @ResponseBody
    byte[] mobileRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/mobile/", "");
        return imageUtil.provideMobileImage(response,path);
    }


    @GetMapping(value = "display/**")
    public @ResponseBody
    byte[] displayRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/display/", "");
        return imageUtil.provideDisplayImage(response,path);
    }

    @GetMapping(value = "thumbnail/**")
    public @ResponseBody
    byte[] thumbnailRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/thumbnail/", "");
        return imageUtil.provideThumbnailImage(response,path);
    }

    @GetMapping(value = "banner/**")
    public @ResponseBody
    byte[] bannerRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/banner/", "");
        return imageUtil.provideBannerImage(response,path);
    }

    @GetMapping(value = "medium/**")
    public @ResponseBody
    byte[] mediumRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/medium/", "");
        return imageUtil.provideMediumImage(response,path);
    }


}
