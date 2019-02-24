package view;

import model.Category;
import service.CategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateCategoryView {

    private String categoryName;

    private String categoryOrderingNumber;

    @Inject
    private CategoryService categoryService;

    public String saveCategory() {
        categoryService.saveCategory(new Category(Integer.parseInt(this.categoryOrderingNumber), this.categoryName));
        clear();
        return "/createCategory.xhtml?faces-redirect=true";
    }

    private void clear() {
        this.categoryName = null;
        this.categoryOrderingNumber = null;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryOrderingNumber() {
        return categoryOrderingNumber;
    }

    public void setCategoryOrderingNumber(String categoryOrderingNumber) {
        this.categoryOrderingNumber = categoryOrderingNumber;
    }
}
