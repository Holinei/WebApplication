package lnu.asmoderos.webApplication.services;

import lnu.asmoderos.webApplication.model.News;

import java.util.List;

public interface INewsService {
    void addNews(News news);

    News getNewsByTitle(String title);

    List<News> getNewsList();

    News getNewsById(int id);

    void deleteNewsById(int id);
}
