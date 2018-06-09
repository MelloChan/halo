package com.halo.service.impl;

import com.halo.dao.BrandDao;
import com.halo.dao.CategoryDao;
import com.halo.dao.TypeDao;
import com.halo.dto.CategoryDTO;
import com.halo.dto.KindDTO;
import com.halo.entity.Brand;
import com.halo.entity.Category;
import com.halo.entity.Type;
import com.halo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/9
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private TypeDao typeDao;

    @Override
    public List<CategoryDTO> getCate() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<Category> categories = categoryDao.getCate();
        List<Brand> brands = brandDao.getBrand();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategories(categories);
        categoryDTO.setBrands(brands);
        categoryDTOS.add(categoryDTO);
        return categoryDTOS;
    }

    @Override
    public List<KindDTO> getCateByCateId(Integer cateId) {
        List<KindDTO> kindDTOs = new ArrayList<>();
        List<Type> types = typeDao.getTypeByCateId(cateId);
        List<Brand> brands = brandDao.getBrandByCateId(cateId);
        KindDTO kindDTO = new KindDTO();
        kindDTO.setTypes(types);
        kindDTO.setBrands(brands);
        kindDTOs.add(kindDTO);
        return kindDTOs;
    }


}
