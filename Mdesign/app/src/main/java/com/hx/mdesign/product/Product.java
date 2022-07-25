package com.hx.mdesign.product;

/**
 * @author: Hx
 * @date: 2022年03月04日 16:01
 */
public class Product {

    private String carName;
    private int imageId;

    public Product(String carName, int imageId) {
        this.carName = carName;
        this.imageId = imageId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
