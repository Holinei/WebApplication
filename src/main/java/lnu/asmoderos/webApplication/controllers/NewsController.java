package lnu.asmoderos.webApplication.controllers;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.model.News;
import lnu.asmoderos.webApplication.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    INewsService newsService;

    @Resource(name = "sessionObject")
    SessionObject sessionObject;


    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String showNews(Model model) {
        List<News> list = newsService.getNewsList();
        model.addAttribute("newsList", list);
        if (sessionObject.getRole() == 0) {
            return "admin/news";
        }
        if (sessionObject.getRole() == 1) {
            return "student/news";
        }
        if (sessionObject.getRole() == 2) {
            return "user/news";
        }
        return "news";
    }


    @RequestMapping(value = "/news-post", method = RequestMethod.GET)
    public String showNewsPost(@RequestParam(name = "id", required = false) int id, Model model) {
        model.addAttribute("news", newsService.getNewsById(id));
        if (sessionObject.getRole() == 0) {
            return "admin/news-post";
        }
        if (sessionObject.getRole() == 1) {
            return "student/news-post";
        }
        if (sessionObject.getRole() == 2) {
            return "user/news-post";
        }
        return "news-post";
    }

    @RequestMapping(value = "/news-post", method = RequestMethod.POST)
    public String postNewsPost(@RequestParam(name = "id", required = false) int id, Model model) {
        model.addAttribute("news", newsService.getNewsById(id));
        if (sessionObject.getRole() == 0) {
            return "admin/news-post";
        }
        if (sessionObject.getRole() == 1) {
            return "student/news-post";
        }
        if (sessionObject.getRole() == 2) {
            return "user/news-post";
        }
        return "news-post";
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.GET)
    public String showUp(Model model) {
        if (sessionObject.getRole() == 0) {
            model.addAttribute("newsModel", new News());
            return "addNews";
        }
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadSingleFile(@ModelAttribute News news, @RequestParam("file") MultipartFile file, Model model) {

        if (news.getTitle().isEmpty() || news.getAuthor().isEmpty() || news.getText().isEmpty() || file.isEmpty()) {
            model.addAttribute("newsModel", new News());
            return "addNews";
        }

        String pathName = "src\\main\\resources\\static\\img\\News\\";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(pathName);
                if (!dir.exists())
                    dir.mkdirs();

                String pathNameFile = dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename();
                File uploadFile = new File(pathNameFile);
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(uploadFile));
                outputStream.write(bytes);
                outputStream.close();
                newsService.addNews(convertNewsFromPage(news, file.getOriginalFilename()));

            } catch (Exception e) {
                e.getMessage();
            }
        }
        List<News> list = newsService.getNewsList();
        model.addAttribute("newsList", list);
        return "admin/news";
    }


    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam(name = "id", required = false) int id, Model model) {

        newsService.deleteNewsById(id);
        List<News> list = newsService.getNewsList();
        model.addAttribute("newsList", list);
        return "admin/news";

    }


    private News convertNewsFromPage(News newsFromPage, String imgPath) {
        News news = new News();
        news.setAuthor(newsFromPage.getAuthor());
        news.setTitle(newsFromPage.getTitle());
        news.setText(newsFromPage.getText());
        news.setImage(imgPath);
        news.setDate(new Date());
        news.setForStudents(false);
        return news;
    }

}
