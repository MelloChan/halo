package com.halo.service.impl;

import com.halo.dao.OrderDao;
import com.halo.dao.ProductDao;
import com.halo.dto.FirstPageInfoDTO;
import com.halo.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SAIKAII
 * @date 2018/6/17
 */
@Service
public class FirstPageServiceImpl implements FirstPageService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public FirstPageInfoDTO getFirstPageInfo() {
        return new FirstPageInfoDTO(
                orderDao.getNumOfOrder(),
                orderDao.getNumOfNoHandledOrder(),
                productDao.getNumOfZeroStock(),
                orderDao.getTotalTurnOver(),
                orderDao.getMeiZuSales(),
                orderDao.getMeiLanSales());
    }

}
