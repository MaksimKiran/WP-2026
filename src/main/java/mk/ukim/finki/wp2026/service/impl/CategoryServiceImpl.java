package mk.ukim.finki.wp2026.service.impl;

import mk.ukim.finki.wp2026.model.Category;
import mk.ukim.finki.wp2026.repository.CategoryRepository;
import mk.ukim.finki.wp2026.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || description == null || name.isBlank() || description.isBlank()) {
            throw new IllegalArgumentException();
        }
        return categoryRepository.save(new Category(name, description));
    }

    @Override
    public Category update(String name, String description) {
        return categoryRepository.save(new Category(name, description));
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
