package com.joshi.imageserver.businesslogic;

import com.joshi.imageserver.consts.ImageConst;
import com.joshi.imageserver.request.ImageSaveRequest;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;



@Component
public class ImageService {
    public String saveImage(ImageSaveRequest request) throws Exception {
        StringBuilder createAPath=new StringBuilder();
        for(String path:request.getFolderPath()){
            createAPath.append(path);
            createAPath.append("/");
        }
        createAPath.append( request.getFileName());
        createAPath.append(".");
        createAPath.append(request.getExtension());
        File outputfile = new File(ImageConst.getIMAGESAVEPATH()+createAPath.toString());
        try {
            File directory = new File(ImageConst.getIMAGESAVEPATH()+createAPath);
            if (!directory.exists()) {
               if(directory.mkdirs()){
                   System.out.print("folder created successfully");
               }
            }
            String base64Img=request.getBase64Image().split(",")[1];
            BufferedImage bi = decodeToImage(base64Img);  // retrieve image
            ImageIO.write(bi, "png", outputfile);
            return createAPath.toString();
        } catch (IOException e) {
            System.out.println("Write error for " + outputfile.getPath() +
                    ": " + e.getMessage());
            throw new Exception("error");
        }

    }

    public Boolean deleteImage(String path) throws Exception {
        try {
            File file = new File(ImageConst.getIMAGESAVEPATH()+path);
            if (file.delete()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("error");
        }
        return false;
    }

    public byte[] provideImage(String path) throws Exception {
        BufferedImage img ;
        try {
            img = ImageIO.read(new File(ImageConst.getIMAGESAVEPATH()+path));
            return convertBufferedImageToByte(img);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("asdf");
            throw new Exception("error");
        }

    }



    private byte[] convertBufferedImageToByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }


    private BufferedImage decodeToImage(String imageString) throws Exception {

        BufferedImage image ;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("error");
        }
        return image;
    }



}


