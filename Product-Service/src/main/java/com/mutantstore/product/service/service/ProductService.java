package com.mutantstore.product.service.service;

import com.mutantstore.product.service.model.ProductRequest;
import com.mutantstore.product.service.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);
}
