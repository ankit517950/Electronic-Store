package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.entities.Category;

public interface CategoryService {
    //Create
     CategoryDto create(CategoryDto categoryDto);

    //Update
    CategoryDto update(CategoryDto categoryDto, String categoryId);
    //Delete
    void delete(String categoryId);
    //getAll
    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
    //get single category details
    CategoryDto get(String categoryId);
    //Serach


}
