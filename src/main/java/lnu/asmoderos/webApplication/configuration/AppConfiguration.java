package lnu.asmoderos.webApplication.configuration;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.dao.*;
import lnu.asmoderos.webApplication.dao.impl.*;
import lnu.asmoderos.webApplication.services.*;
import lnu.asmoderos.webApplication.services.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.MultipartConfigElement;

@Configuration
public class AppConfiguration {

    @Bean
    public SessionFactory hibernateSessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

    @Bean
    public IUserDAO userDAO(SessionFactory hibernateSessionFactory) {
        return new UserDAOImpl(hibernateSessionFactory);
    }

    @Bean
    public IGroupDAO groupDAO(SessionFactory hibernateSessionFactory) {
        return new GroupDAOImpl(hibernateSessionFactory);
    }

    @Bean
    public ICourseDAO courseDAO(SessionFactory hibernateSessionFactory) {
        return new CourseDAOImpl(hibernateSessionFactory);
    }

    @Bean
    public ICategoryDAO categoryDAO(SessionFactory hibernateSessionFactory) {
        return new CategoryDAOImpl(hibernateSessionFactory);
    }

    @Bean
    public INewsDAO newsDAO(SessionFactory hibernateSessionFactory) {
        return new NewsDAOImpl(hibernateSessionFactory);
    }

    @Bean
    public IGroupService groupService(IGroupDAO groupDAO) {
        return new GroupServiceImpl(groupDAO);
    }


    @Bean
    public IUserService userService(IUserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    @Bean
    public INewsService newsService(INewsDAO newsDAO) {
        return new NewsServiceImpl(newsDAO);
    }

    @Bean
    public ICourseService courseService(ICourseDAO courseDAO) {
        return new CourseServiceImpl(courseDAO);
    }

    @Bean
    public IAuthenticationService authenticationService(IUserDAO userDAO) {
        return new AuthenticationServiceImpl(userDAO);
    }

    @Bean
    public ICategoryService categoryService(ICategoryDAO categoryDAO) {
        return new CategoryServiceImpl(categoryDAO);
    }

    @Bean
    public IRegisterService registerService(IUserDAO userDAO) {
        return new RegisterServiceImpl(userDAO);
    }

    @Bean
    @SessionScope
    public SessionObject sessionObject() {
        return new SessionObject();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("13MB"));
        factory.setMaxRequestSize(DataSize.parse("13MB"));
        return factory.createMultipartConfig();
    }
}
