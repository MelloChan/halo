package com.halo.service.impl;

import com.halo.dao.UserAddressDao;
import com.halo.dto.AddressDTO;
import com.halo.entity.UserAddress;
import com.halo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressDao userAddressDao;
    @Override
    public List<AddressDTO> getByUId(Integer userId) {
        List<UserAddress>userAddresses=userAddressDao.getByUId(userId);
        List<AddressDTO>addressDTOS=new ArrayList<>();
        AddressDTO addressDTO;
        for (UserAddress userAddress:userAddresses) {
            addressDTO=new AddressDTO();
            addressDTO.setId(userAddress.getId());
            addressDTO.setName(userAddress.getUserName());
            addressDTO.setPhone(userAddress.getUserPhone());
            addressDTO.setAddress(userAddress.getUserAddress());
            addressDTOS.add(addressDTO);
        }
        return addressDTOS;
    }
}
