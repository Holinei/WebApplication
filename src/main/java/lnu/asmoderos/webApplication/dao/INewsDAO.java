package lnu.asmoderos.webApplication.dao;

import lnu.asmoderos.webApplication.model.News;

import java.util.List;

public interface INewsDAO {
    void saveNews(News news);

    News getNewsByTitle(String title);

    List<News> getNewsList();

    News getNewsById(int id);

    void deleteNewsById(int id);
}
