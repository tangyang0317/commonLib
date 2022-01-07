package com.bqj.mall.library.commonlib.view.sku.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wuhenzhizao on 2017/3/6.
 */
public class Sku implements Parcelable {

  private String goodsId;
  private String mainGoodsId;
  private Double price;
  private int inventory;

  private List<SkuAttribute> attr;


  public Sku() {
  }

  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }

  public String getMainGoodsId() {
    return mainGoodsId;
  }

  public void setMainGoodsId(String mainGoodsId) {
    this.mainGoodsId = mainGoodsId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getInventory() {
    return inventory;
  }

  public void setInventory(int inventory) {
    this.inventory = inventory;
  }

  public List<SkuAttribute> getAttr() {
    return attr;
  }

  public void setAttr(List<SkuAttribute> attr) {
    this.attr = attr;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.goodsId);
    dest.writeString(this.mainGoodsId);
    dest.writeInt(this.inventory);
    dest.writeDouble(this.price);
    dest.writeTypedList(this.attr);
  }

  protected Sku(Parcel in) {
    this.goodsId = in.readString();
    this.mainGoodsId = in.readString();
    this.inventory = in.readInt();
    this.price = in.readDouble();
    this.attr = in.createTypedArrayList(SkuAttribute.CREATOR);
  }

  public static final Creator<Sku> CREATOR = new Creator<Sku>() {
    @Override
    public Sku createFromParcel(Parcel source) {
      return new Sku(source);
    }

    @Override
    public Sku[] newArray(int size) {
      return new Sku[size];
    }
  };
}
