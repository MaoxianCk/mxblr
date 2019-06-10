package com.mxblr.data.vo;

import javax.validation.constraints.NotNull;

/**
 * @author Kny
 * 2019/06/07
 */
public class ImageVO {
    private Integer imageId;

    private String name;

    @NotNull(message = "图片Base64字符串不能为空")
    private String image;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
