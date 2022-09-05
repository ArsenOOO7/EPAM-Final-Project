package com.arsen.epam.internet.shop.entity.image.mime;

/**
 * ImageContentType enum
 *
 * @author Arsen Sydoryk
 */
public enum ImageContentType {

    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpg"),
    PNG("png", "image/png"),
    MP4("mp4", "video/mp4"),
    UNDEFINED("", "");

    private String extension;
    private String contentType;

    ImageContentType(String extension, String contentType){
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension(){
        return extension;
    }
    public String getContentType(){
        return contentType;
    }

    /**
     *
     * @param requestType from request
     * @return ImageContentType instance
     */
    public static ImageContentType getContentType(String requestType){
        ImageContentType imageContentType = ImageContentType.UNDEFINED;
        for(ImageContentType type: ImageContentType.values()){
            if(type.contentType.equals(requestType)){
                imageContentType = type;
            }
        }
        return imageContentType;
    }

}