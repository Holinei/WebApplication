package lnu.asmoderos.webApplication.dao;

import lnu.asmoderos.webApplication.model.Category;
import lnu.asmoderos.webApplication.model.Group;

import java.util.List;

public interface ICategoryDAO {
    List<Category> getCategoryList();
}
