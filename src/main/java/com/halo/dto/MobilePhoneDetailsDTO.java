package com.halo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 手机类商品详情页面传输对象
 *
 * @author MelloChan
 * @date 2018/5/16
 */
public class MobilePhoneDetailsDTO implements Serializable {
    private static final long serialVersionUID = 5604644590202148848L;

    private CommonItemDetailsDTO common;
    private List<String> version;
    private List<String> nettype;
    private List<Map<String, Object>> rom;

    public MobilePhoneDetailsDTO() {
    }

    public MobilePhoneDetailsDTO(CommonItemDetailsDTO common, List<String> version, List<String> nettype, List<Map<String, Object>> rom) {
        this.common = common;
        this.version = version;
        this.nettype = nettype;
        this.rom = rom;
    }

    public CommonItemDetailsDTO getCommon() {
        return common;
    }

    public void setCommon(CommonItemDetailsDTO common) {
        this.common = common;
    }

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

    public List<Map<String, Object>> getRom() {
        return rom;
    }

    public void setRom(List<Map<String, Object>> rom) {
        this.rom = rom;
    }

    @Override
    public String toString() {
        return "MobilePhoneDetailsDTO{" +
                "common=" + common +
                ", version=" + version +
                ", nettype=" + nettype +
                ", rom=" + rom +
                '}';
    }
}
