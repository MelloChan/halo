package com.halo.service.impl;

import com.halo.dao.ProductDao;
import com.halo.dao.ProductDetailDao;
import com.halo.dao.ProductSpecificationDao;
import com.halo.dto.ItemDTO;
import com.halo.dto.ItemDetailDTO;
import com.halo.entity.Product;
import com.halo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductSpecificationDao productSpecificationDao;
    @Autowired
    private ProductDetailDao productDetailDao;

    @Override
    public List<ItemDTO> getItems(Integer pageIndex,Integer pageCount) {
        Integer index=(pageIndex-1)*pageCount;
        List<Product> products = productDao.getItems(index,pageCount);
        List<ItemDTO> items = new ArrayList<>();
        ItemDTO item;
        for (Product product : products) {
            item = new ItemDTO(product.getId(), product.getImage(), product.getName(), product.getTitle(),
                    product.getPrice());
            items.add(item);
        }
        return items;
    }

    @Override
    public ItemDetailDTO getItemDetailByProId(String proId) {
        return new ItemDetailDTO(productSpecificationDao.getSpecificationByProId(proId),productDetailDao.getDescriptionByProId(proId));
    }
}
