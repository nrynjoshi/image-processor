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
    void orginalRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/original/", "");
         imageUtil.provideOrginalImage(response,path);
    }

    @GetMapping(value = "mobile/**")
    public @ResponseBody
    void mobileRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/mobile/", "");
         imageUtil.provideMobileImage(response,path);
    }


    @GetMapping(value = "display/**")
    public @ResponseBody
    void displayRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/display/", "");
         imageUtil.provideDisplayImage(response,path);
    }

    @GetMapping(value = "thumbnail/**")
    public @ResponseBody
    void thumbnailRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/thumbnail/", "");
         imageUtil.provideThumbnailImage(response,path);
    }

    @GetMapping(value = "banner/**")
    public @ResponseBody
    void bannerRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/banner/", "");
         imageUtil.provideBannerImage(response,path);
    }

    @GetMapping(value = "medium/**")
    public @ResponseBody
    void mediumRetriveImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getRequestURI().replaceFirst("/medium/", "");
         imageUtil.provideMediumImage(response,path);
    }


}
