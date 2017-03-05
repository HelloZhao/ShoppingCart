package com.bwie.shoppingcart.bean;

/**
 * Created by lenovo on 2017/2/21.
 */

public class Bean {
    private String shopName;
    private int shopPrice;
    private int shopNum;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public int getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(int shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Bean( String shopName, int shopNum, int shopPrice) {
        this.shopName = shopName;
        this.shopNum = shopNum;
        this.shopPrice = shopPrice;
    }
}
