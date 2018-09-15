package com.triplocator.imageserver.businesslogic;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class ImageProcessingService {

    public static void reduceImageSize(BufferedImage image ) throws IOException {

        



    }

    /**
     * x - the X coordinate of the upper-left corner of the specified rectangular region
     * y - the Y coordinate of the upper-left corner of the specified rectangular region
     * width - the width of the specified rectangular region
     * height - the height of the specified rectangular region
     * **/
    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height){
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
        return croppedImage;
    }




}
