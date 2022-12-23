package ru.geekbrains.spring.ws.soap.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ws.soap.entities.ProductEntity;
import ru.geekbrains.spring.ws.soap.products.Product;
import ru.geekbrains.spring.ws.soap.repositories.ProductRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private static final Function<ProductEntity, Product> functionEntityToSoap = pe -> {
        Product p = new Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setCost(pe.getCost());
        p.setCategoryTitle(pe.getCategoryEntity().getTitle());
        return p;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

    public Product getByName(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
