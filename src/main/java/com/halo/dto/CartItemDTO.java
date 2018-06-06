package com.halo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author MelloChan
 * @date 2018/6/2
 */
public class    CartItemDTO implements Serializable {
    private static final long serialVersionUID = 6966010392610826703L;
    @Min(1)
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String title;
    @Min(1)
    private Integer price;
    private String version;
    private String nettype;
    @NotEmpty
    private String color;
    private String rom;
    @Min(1)
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNettype() {
        return nettype;
    }

    public void setNettype(String nettype) {
        this.nettype = nettype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", version='" + version + '\'' +
                ", nettype='" + nettype + '\'' +
                ", color='" + color + '\'' +
                ", rom='" + rom + '\'' +
                ", amount=" + amount +
                '}';
    }
}
