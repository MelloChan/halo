package com.halo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 手机类商品详情页面传输对象
 * @author MelloChan
 * @date 2018/5/16
 */
public class MobilePhoneDetailsDTO extends CommonItemDetailsDTO implements Serializable{
    private static final long serialVersionUID = 5604644590202148848L;

    private List<String> version;
    private List<String>nettype;
    private List<String>rom;

    public List<String> getVersion() {
        return version;
    }

    public void setVersion(List<String> version) {
        this.version = version;
    }

    public List<String> getNettype() {
        return nettype;
    }

    public void setNettype(List<String> nettype) {
        this.nettype = nettype;
    }

    public List<String> getRom() {
        return rom;
    }

    public void setRom(List<String> rom) {
        this.rom = rom;
    }

    @Override
    public String toString() {
        return "MobilePhoneDetailsDTO{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", version=" + version +
                ", price=" + price +
                ", nettype=" + nettype +
                ", color=" + color +
                ", rom=" + rom +
                ", imgUrl=" + imgUrl +
                '}';
    }
}
