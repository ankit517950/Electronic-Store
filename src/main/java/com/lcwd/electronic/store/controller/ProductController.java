package com.lcwd.electronic.store.controller;

import com.lcwd.electronic.store.dtos.*;
import com.lcwd.electronic.store.repositiories.ProductRepository;
import com.lcwd.electronic.store.services.FileService;
import com.lcwd.electronic.store.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${product.image.path}")
    private String imagePath;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    //Create Product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.create(productDto);

        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    //Update Product
    public ResponseEntity<ProductDto> update(@PathVariable String productId,@RequestBody ProductDto productDto)
    {
        ProductDto update = productService.update(productDto, productId);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    //Delete
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId)
    {
        productService.delete(productId);
        ApiResponseMessage productDeletedSuccessfully = ApiResponseMessage.builder().message("Product deleted successfully").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(productDeletedSuccessfully,HttpStatus.OK);
    }
    //Get Single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        ProductDto productGet = productService.getSingle(productId);

        return new ResponseEntity<>(productGet, HttpStatus.OK);
    }
    //get All
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
      PageableResponse<ProductDto> pageableResponse=  productService.getAll(pageNumber,pageSize,sortBy,sortDir);
      return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }
    //Get all live
    //products/live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        PageableResponse<ProductDto> pageableResponse=  productService.getAllLive(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //Search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        PageableResponse<ProductDto> pageableResponse=  productService.searchByTitle(query,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //Upload Image
    @PostMapping("/image/{productId}")
   public ResponseEntity<ImageResponse> uploadImage(
           @PathVariable String productId,
           @RequestParam("productImage")MultipartFile image
           ) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);
       ProductDto productDto = productService.getSingle(productId);
        productDto.setProductImageName(fileName);
        ProductDto update = productService.update(productDto, productId);
        ImageResponse imageUploadSuccessfully = ImageResponse.builder().imageName(update.getProductImageName()).message("Image upload successfully").status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(imageUploadSuccessfully,HttpStatus.CREATED);
    }
    //Serve Image
    @GetMapping("/image/{productId}")
    public void serveUserImage(@PathVariable String productId, HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.getSingle(productId);
        logger.info("User image name:{}",productDto.getProductImageName());
        InputStream resource = fileService.getResource(imagePath,productDto.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
