package com.halo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/22
 */
public class AddressListDTO implements Serializable {
    private static final long serialVersionUID = -7498455334054980350L;

    private List<Address>addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "AddressListDTO{" +
                "addresses=" + addresses +
                '}';
    }
}
