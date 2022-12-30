package com.mutantstore.product.service.service;

import com.mutantstore.product.service.entity.Product;
import com.mutantstore.product.service.exception.ProductServiceCustomException;
import com.mutantstore.product.service.model.ProductRequest;
import com.mutantstore.product.service.model.ProductResponse;
import com.mutantstore.product.service.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product to database...");
            Product product = Product
                    .builder()
                    .productName(productRequest.getName())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .build();
            productRepository.save(product);
            log.info("Product Created...");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Getting Product for Id : {}",productId);

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(()->
                                new ProductServiceCustomException
                                        ("Product with given Id is not in Database : " + productId, "PRODUCT_NOT_FOUND"));
                ProductResponse productResponse = new ProductResponse();
                BeanUtils.copyProperties(product, productResponse);

        return productResponse;

    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {} for Id : {}",quantity, productId);

        Product product = productRepository.findById(productId).orElseThrow(()->new ProductServiceCustomException("" +
                "Product not found with the given Id : " +productId,
                "PRODUCT_NOT_FOUND"));
        if(product.getQuantity() < quantity){
            throw new ProductServiceCustomException("Requested product is out of stock",
                    "PRODUCT_OUT_OF_STOCK");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product quantity reduced successfully : 😊😊😊");

    }
}
