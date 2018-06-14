package com.halo.service.impl;

import com.halo.dao.ProductDao;
import com.halo.dto.ItemInfoDTO;
import com.halo.service.BackstageProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author SAIKAII
 * @date 2018/6/12
 */
@Service
public class BackstageProductManageServiceImpl implements BackstageProductManageService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ItemInfoDTO> getItemsForBackstage(Integer pageIndex, Integer pageCount) {
        pageIndex = (pageIndex-1) * pageCount;
        return productDao.getItemsInfo(pageIndex, pageCount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Integer id) {
        productDao.deleteProductById(id);
        productDao.deleteProductDetailById(id);
        productDao.deleteProductImageById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMultiProducts(ArrayList<Integer> idList) {
        productDao.deleteMultiProductsById(idList);
        productDao.deleteMultiProductDetailsById(idList);
        productDao.deleteMultiProductImagesById(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductInfoByPid(Integer pid, String name, Integer num, Integer price) {
        Integer stock = productDao.getStockById(pid);
        if((stock + num) < 0){
            throw new RuntimeException("库存不足");
        }else {
            productDao.updateProductInfoById(pid, name, num, price);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ItemInfoDTO> getItemsForBackstageByTypeAndName(String type, String name, Integer pageIndex, Integer pageCount) {
        pageIndex = (pageIndex - 1) * pageCount;
        return productDao.getItemsInfoByTypeAndName(type, name, pageIndex, pageCount);
    }

}
