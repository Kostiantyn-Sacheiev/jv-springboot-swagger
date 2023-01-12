package mate.academy.springboot.swagger.service.impl;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.repository.ProductRepository;
import mate.academy.springboot.swagger.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        Product productFromDb = productRepository.findById(product.getId()).get();
        productFromDb.setId(product.getId());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }

    @Override
    public List<Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Product> getAllByPriceBetween(Pageable pageRequest,
                                              BigDecimal from,
                                              BigDecimal to) {
        return productRepository.getAllByPriceBetween(pageRequest, from, to);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
