package com.mutantstore.product.service.controller;

import com.mutantstore.product.service.model.ProductRequest;
import com.mutantstore.product.service.model.ProductResponse;
import com.mutantstore.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Create product
    @PostMapping()
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);

        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId){
        ProductResponse productResponse =
            productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    //Reduce quantity
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
           @PathVariable ("id") long productId,
           @RequestParam long quantity
    ){
        productService.reduceQuantity(productId, quantity);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
