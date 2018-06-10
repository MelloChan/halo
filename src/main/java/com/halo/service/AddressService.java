package com.halo.service;

import com.halo.dto.AddressDTO;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
public interface AddressService {
    List<AddressDTO>getByUId(Integer userId);
}
