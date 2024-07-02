package com.exm.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exm.news.model.Product;
import com.exm.news.repository.ProductRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    
	private final ProductRepository productRepository;
    
    private ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product addProduct(Product product, MultipartFile file) throws IOException {
    	System.out.println("service product: ");
        product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return productRepository.save(product);
    }
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }
}