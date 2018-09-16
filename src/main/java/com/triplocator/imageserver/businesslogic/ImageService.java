package com.triplocator.imageserver.businesslogic;

import com.triplocator.imageserver.consts.ImageConst;
import com.triplocator.imageserver.request.ImageSaveRequest;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;



@Component
public class ImageService {
    public String saveImage(ImageSaveRequest request) {
        File outputfile = new File(ImageConst.getIMAGESAVEPATH() + "save.jpg");
        try {
            // retrieve image
//            byte[] name = Base64.getEncoder().encode(request.getBase64Image().replace("data:image/png;base64","").getBytes());
//            byte[] decodedImg = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
            File directory = new File(ImageConst.getIMAGESAVEPATH());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedImage bi = decodeToImage(request.getBase64Image());  // retrieve image
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Write error for " + outputfile.getPath() +
                    ": " + e.getMessage());
        }
        return null;
    }

    public Boolean deleteImage(String path) {
        try {
            File file = new File(path);
            if (file.delete()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public byte[] provideImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path + "abc.jpg"));
            return convertBufferedImageToByte(img);
        } catch (IOException e) {
            System.out.println("asdf");
        }
        return null;
    }

    public Boolean checkImageNameExist() {
        return false;
    }

    public byte[] convertBufferedImageToByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }


    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

}


