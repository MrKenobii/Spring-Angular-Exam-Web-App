package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.exam.Category;
import com.mrkenobii.examserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category updateCategory(Category category){
        Category category1 = categoryRepository.findById(category.getId())
                .orElseThrow(RuntimeException::new);
        category1.setDescription(category.getDescription());
        category1.setTitle(category.getTitle());
        category1.setQuizzes(category.getQuizzes());
        return categoryRepository.save(category1);
    }
    public Set<Category> getCategories(){
        return new LinkedHashSet<>(categoryRepository.findAll());
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
