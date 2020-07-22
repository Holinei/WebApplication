package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.ICategoryDAO;
import lnu.asmoderos.webApplication.dao.IGroupDAO;
import lnu.asmoderos.webApplication.model.Category;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.services.ICategoryService;
import lnu.asmoderos.webApplication.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryDAO categoryDAO;

    public CategoryServiceImpl(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }
}
