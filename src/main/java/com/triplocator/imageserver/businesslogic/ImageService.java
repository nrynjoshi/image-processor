package com.triplocator.imageserver.businesslogic;

import com.triplocator.imageserver.consts.ImageConst;
import com.triplocator.imageserver.request.ImageSaveRequest;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageService {
    public String saveImage(ImageSaveRequest request) {
        File outputfile = new File(ImageConst.getIMAGESAVEPATH()+"saved.jpg");        try {
            // retrieve image
            BufferedImage bi = convertBase64ToBufferedImage(request.getBase64Image());

            ImageIO.write(bi, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("Write error for " + outputfile.getPath() +
                    ": " + e.getMessage());
        }
        return null;
    }

    public Boolean deleteImage(String path) {

        return false;
    }

    public byte[] provideImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("strawberry.jpg"));
           return convertBufferedImageToByte(img);
        } catch (IOException e) {
            System.out.println("asdf");
        }
        return null;
    }

    public Boolean checkImageNameExist() {
        return false;
    }
    private byte[] convertBufferedImageToByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
    private BufferedImage convertBase64ToBufferedImage(String base64File){
        BufferedImage image = null;  base64File=base64File.replace("data:image/png;base64","");
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64File);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

}


