package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.IGroupDAO;
import lnu.asmoderos.webApplication.dao.INewsDAO;
import lnu.asmoderos.webApplication.dao.IUserDAO;
import lnu.asmoderos.webApplication.model.News;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.INewsService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class NewsServiceImpl implements INewsService {
    @Autowired
    INewsDAO newsDAO;

    public NewsServiceImpl(INewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public void addNews(News news) {
        News newsFromDb = this.newsDAO.getNewsByTitle(news.getTitle());
        if (newsFromDb == null) {
            this.newsDAO.saveNews(news);
        }
    }

    @Override
    public News getNewsByTitle(String title) {
        return this.newsDAO.getNewsByTitle(title);
    }

    @Override
    public List<News> getNewsList() {
        return newsDAO.getNewsList();
    }

    @Override
    public News getNewsById(int id) {
        return this.newsDAO.getNewsById(id);
    }

    @Override
    public void deleteNewsById(int id) {
        this.newsDAO.deleteNewsById(id);
    }
}
