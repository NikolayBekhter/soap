package ru.geekbrains.spring.ws.soap.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ws.soap.categories.Category;
import ru.geekbrains.spring.ws.soap.entities.CategoryEntity;
import ru.geekbrains.spring.ws.soap.repositories.CategoryRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private static final Function<CategoryEntity, Category> functionEntityToSoap = ce -> {
        Category c = new Category();
        c.setId(ce.getId());
        c.setTitle(ce.getTitle());
        return c;
    };

    public List<Category> getAllProducts() {
        return categoryRepository.findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

    public Category getByName(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }

}
