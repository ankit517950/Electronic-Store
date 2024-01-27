package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;
import jdk.dynalink.linker.LinkerServices;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    //Create
    ProductDto create(ProductDto productDto);
    //update
    ProductDto update(ProductDto productDto,String proDuctId);
    //delete
    void delete(String productId);
    //getSingle
    ProductDto getSingle(String productId);
    //getAll
    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String soryDir);

    //getAll Live
    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String soryDir);
    //serach product
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String soryDir);

    //Create product with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);
    //update category of product
    ProductDto updateCategory(String productId,String categoryId);

    //
    PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);
}
