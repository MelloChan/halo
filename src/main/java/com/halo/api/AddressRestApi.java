package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.dto.AddressDTO;
import com.halo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/6/12
 */
@RestController
@RequestMapping("/api/halo/addresses")
@Validated
public class AddressRestApi extends BaseController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public Map<String, Object> getAddressById(@RequestAttribute("uid") Integer uid) {
        return rtnParam(0, ImmutableMap.of("address", addressService.getByUId(uid)));
    }

    @PostMapping("/")
    public Map<String, Object> insertAddressInfo(@RequestAttribute("uid") Integer uid, @Valid @RequestBody AddressDTO addressDTO) {
        return rtnParam(0, ImmutableMap.of("id", addressService.insertAddressInfo(uid, addressDTO)));
    }

    @PutMapping("/")
    public Map<String, Object> updateAddressInfoById(@RequestAttribute("uid") Integer uid, @Valid @RequestBody AddressDTO addressDTO) {
        addressService.updateAddressInfoById(uid, addressDTO);
        return rtnParam(0, ImmutableMap.of("msg", true));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteAddressInfoById(@RequestAttribute("uid") Integer uid, @PathVariable("id") Integer id) {
        addressService.deleteAddressInfoById(uid, id);
        return rtnParam(0, ImmutableMap.of("msg", true));
    }
}
