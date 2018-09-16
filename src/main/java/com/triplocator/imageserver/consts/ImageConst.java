package com.triplocator.imageserver.consts;

public class ImageConst {

    private final static String IMAGESAVEPATH="/srv/triplocator/images/";
    private final static String VIRTUALPATHFORUSER ="/images/";
    private final static String DEFAULTFILE ="default/image.jpg";

    public static String getIMAGESAVEPATH() {
        return IMAGESAVEPATH;
    }

    public static String getVIRTUALPATHFORUSER() {
        return VIRTUALPATHFORUSER;
    }

    public static String getDEFAULTFILE() {
        return DEFAULTFILE;
    }
}
