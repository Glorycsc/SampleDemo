package com.glory.mybanner;

/**
 * Create by glorizz on 2018/9/3
 * Describe:
 */
public class UploadThumbModelOkHttp {


    @Override
    public String toString() {
        return "UploadThumbModel{" +
                "pictureHeight=" + pictureHeight +
                ", code='" + code + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", pictureWidth=" + pictureWidth +
                ", desc='" + desc + '\'' +
                '}';
    }

    /**
     * pictureHeight : 2208
     * code : 1
     * fileUrl : /static/user/headImg/20180903/c763c751-e91a-40e3-949c-f2b5cc64745a.png
     * pictureWidth : 1242
     * desc : 上传成功
     */


    private int pictureHeight;
    private String code;
    private String fileUrl;
    private int pictureWidth;
    private String desc;

    public int getPictureHeight() {
        return pictureHeight;
    }

    public void setPictureHeight(int pictureHeight) {
        this.pictureHeight = pictureHeight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getPictureWidth() {
        return pictureWidth;
    }

    public void setPictureWidth(int pictureWidth) {
        this.pictureWidth = pictureWidth;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
