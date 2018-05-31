package com.halo.service.impl;

import com.halo.dao.ProductDao;
import com.halo.dto.Item;
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

    @Override
    public List<Item> getItems(Integer pageIndex,Integer pageCount) {
        Integer index=(pageIndex-1)*pageCount;
        List<Product> products = productDao.getItems(index,pageCount);
        List<Item> items = new ArrayList<>();
        Item item;
        for (Product product : products) {
            item = new Item(product.getId(), product.getImage(), product.getName(), product.getTitle(),
                    product.getPrice());
            items.add(item);
        }
        return items;
    }
}
