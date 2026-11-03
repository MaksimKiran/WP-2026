package mk.ukim.finki.wp2026.repository.impl;

import mk.ukim.finki.wp2026.bootstrap.DataHolder;
import mk.ukim.finki.wp2026.model.Category;
import mk.ukim.finki.wp2026.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Category save(Category category) {
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));
        DataHolder.categories.add(category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return DataHolder.categories;
    }

    @Override
    public Optional<Category> findByName(String name) {
        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Category> search(String text) {
        String t = text.toLowerCase();
        return DataHolder.categories.stream()
                .filter(c -> c.getName().toLowerCase().equals(t) || c.getDescription().toLowerCase().equals(t))
                .toList();
    }

    @Override
    public void delete(String name) {
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }
}
