
package org.example.tpo04blog.controllers;

import org.example.tpo04blog.controllers.implementations.ArticleController;
import org.example.tpo04blog.controllers.implementations.BlogController;
import org.example.tpo04blog.controllers.implementations.RoleController;
import org.example.tpo04blog.controllers.implementations.UserController;
import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.entities.Role;
import org.example.tpo04blog.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public class CompoundController {
    private final UserController userController;
    private final BlogController blogController;
    private final RoleController roleController;
    private final ArticleController articleController;

    public CompoundController(UserController userController,
                              BlogController blogController,
                              RoleController roleController,
                              ArticleController articleController) {
        this.userController = userController;
        this.blogController = blogController;
        this.roleController = roleController;
        this.articleController = articleController;
    }

    public void viewAllData() {
        System.out.println("---- Users ----");
        userController.viewAll();
        System.out.println("---- Blogs ----");
        blogController.viewAll();
        System.out.println("---- Roles ----");
        roleController.viewAll();
        System.out.println("---- Articles ----");
        articleController.viewAll();
    }

    public void addUser(User user) {
        userController.add(user);
    }

    public void addBlog(Blog blog) {
        blogController.add(blog);
    }

    public void addRole(Role role) {
        roleController.add(role);
    }

    public void addArticle(Article article) {
        articleController.add(article);
    }

    public User searchUser(Long id) {
        return userController.searchById(id);
    }

    public Blog searchBlog(Long id) {
        return blogController.searchById(id);
    }

    public Role searchRole(Long id) {
        return roleController.searchById(id);
    }

    public Article searchArticle(Long id) {
        Article article = articleController.searchById(id);
        System.out.println(article);
        return article;
    }

    public void deleteUser(Long id) {
        userController.delete(id);
    }

    public void deleteBlog(Long id) {
        blogController.delete(id);
    }

    public void deleteRole(Long id) {
        roleController.delete(id);
    }

    public void deleteArticle(Long id) {
        articleController.delete(id);
    }

    public void updateUser(User user) {
        userController.update(user.getId(), user);
    }

    public void updateBlog(Blog blog) {
        blogController.update(blog.getId(), blog);
    }

    public void updateArticle(Article article) {
        articleController.update(article.getId(), article);
    }

    public List<User> findUsersByEmailContaining(String keyword) {
        return userController.findUsersByEmailContaining(keyword);
    }
}
