package com.example.cmpp264_workshop8_group1;

import java.io.Serializable;
import java.text.DateFormat;

public class Package implements Serializable {
    private Integer PackageId;
    private String PkgName;
    private String PkgStartDate;
    private String PkgEndDate;
    private String PkgDesc;
    private String PkgBasePrice;
    private String PkgAgencyCommission;
    private String imgURL;

    public String getImgURL() {
        return imgURL;
    }
    public Package(Integer packageId, String pkgName, String pkgStartDate, String pkgEndDate, String pkgDesc, String pkgBasePrice, String pkgAgencyCommission) {
        this.PackageId = packageId;
        this.PkgName = pkgName;
        this.PkgStartDate = pkgStartDate;
        this.PkgEndDate = pkgEndDate;
        this.PkgDesc = pkgDesc;
        this.PkgBasePrice = pkgBasePrice;
        this.PkgAgencyCommission = pkgAgencyCommission;
    }

    public Integer getPackageId() {
        return PackageId;
    }

    public void setPackageId(Integer packageId) {
        this.PackageId = packageId;
    }

    public String getPkgName() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        this.PkgName = pkgName;
    }

    public String getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(String pkgStartDate) {
        this.PkgStartDate = pkgStartDate;
    }

    public String getPkgEndDate() {
        return PkgEndDate;
    }

    public void setPkgEndDate(String pkgEndDate) {
        this.PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.PkgDesc = pkgDesc;
    }

    public String getPkgBasePrice() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(String pkgBasePrice) {
        this.PkgBasePrice = pkgBasePrice;
    }

    public String getPkgAgencyCommission() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(String pkgAgencyCommission) {
        this.PkgAgencyCommission = pkgAgencyCommission;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


    @Override
    public String toString() {
        return PkgName +  "\n" + "" + "\n" + PkgDesc;
    }
}
