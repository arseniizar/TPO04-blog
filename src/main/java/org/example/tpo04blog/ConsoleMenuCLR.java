package org.example.tpo04blog;

import org.example.tpo04blog.controllers.CompoundController;
import org.example.tpo04blog.entities.Article;
import org.example.tpo04blog.entities.Blog;
import org.example.tpo04blog.entities.Role;
import org.example.tpo04blog.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsoleMenuCLR implements CommandLineRunner {

    private final CompoundController compoundController;
    private final Scanner scanner;

    public ConsoleMenuCLR(CompoundController compoundController, Scanner scanner) {
        this.compoundController = compoundController;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int option = scanOption();
            exit = processOption(option);
        }
    }

    private void displayMenu() {
        System.out.println("====================================");
        System.out.println("          APPLICATION MENU          ");
        System.out.println("====================================");
        System.out.println("  1. View all data");
        System.out.println("  2. Add new user");
        System.out.println("  3. Add new blog");
        System.out.println("  4. Add new role");
        System.out.println("  5. Add new article");
        System.out.println("  6. Search user by ID");
        System.out.println("  7. Search blog by ID");
        System.out.println("  8. Search role by ID");
        System.out.println("  9. Search article by ID");
        System.out.println(" 10. Delete user by ID");
        System.out.println(" 11. Delete blog by ID");
        System.out.println(" 12. Delete role by ID");
        System.out.println(" 13. Delete article by ID");
        System.out.println(" 14. Assign role to user");
        System.out.println(" 15. Set blog manager");
        System.out.println(" 16. Set blog articles");
        System.out.println(" 17. Assign role to user");
        System.out.println(" 18. Search users by email containing a keyword: ");
        System.out.println("  0. Exit");
        System.out.println("====================================");
    }

    private int scanOption() {
        System.out.print("Select an option: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    private boolean processOption(int option) {
        try {
            switch (option) {
                case 1:
                    viewAllData();
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    addNewBlog();
                    break;
                case 4:
                    addNewRole();
                    break;
                case 5:
                    addNewArticle();
                    break;
                case 6:
                    searchUserById();
                    break;
                case 7:
                    searchBlogById();
                    break;
                case 8:
                    searchRoleById();
                    break;
                case 9:
                    searchArticleById();
                    break;
                case 10:
                    deleteUserById();
                    break;
                case 11:
                    deleteBlogById();
                    break;
                case 12:
                    deleteRoleById();
                    break;
                case 13:
                    deleteArticleById();
                    break;
                case 14:
                    assignRoleToUser();
                    break;
                case 15:
                    setBlogManager();
                    break;
                case 16:
                    setBlogArticles();
                    break;
                case 17:
                    assignAuthorToArticle();
                    break;
                case 18:
                    searchUsersByEmail();
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    return true;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        System.out.println();
        return false;
    }

    private void viewAllData() {
        compoundController.viewAllData();
    }

    private void addNewUser() {
        String email = scanAndValidateString("Enter user email: ");
        User user = new User();
        user.setEmail(email);
        compoundController.addUser(user);
    }

    private void addNewBlog() {
        String blogName = scanAndValidateString("Enter blog name: ");
        Blog blog = new Blog();
        blog.setName(blogName);
        compoundController.addBlog(blog);
    }

    private void addNewRole() {
        String roleName = scanAndValidateString("Enter role name: ");
        Role role = new Role();
        role.setName(roleName);
        compoundController.addRole(role);
    }

    private void addNewArticle() {
        String title = scanAndValidateString("Enter article title: ");
        Long authorId = scanLong("Enter author ID: ");
        Long blogId = scanLong("Enter blog ID: ");
        Article article = new Article();
        article.setTitle(title);
        User author = compoundController.searchUser(authorId);
        Blog blog = compoundController.searchBlog(blogId);
        if (author == null) {
            System.out.println("Author with ID " + authorId + " not found.");
            return;
        }
        if (blog == null) {
            System.out.println("Blog with ID " + blogId + " not found.");
            return;
        }
        article.setAuthor(author);
        article.setBlog(blog);
        compoundController.addArticle(article);
    }

    private void searchUserById() {
        Long id = scanLong("Enter user ID to search: ");
        User foundUser = compoundController.searchUser(id);
        System.out.println(foundUser);
    }

    private void searchBlogById() {
        Long id = scanLong("Enter blog ID to search: ");
        Blog foundBlog = compoundController.searchBlog(id);
        System.out.println(foundBlog);
    }

    private void searchRoleById() {
        Long id = scanLong("Enter role ID to search: ");
        Role foundRole = compoundController.searchRole(id);
        System.out.println(foundRole);
    }

    private void searchArticleById() {
        Long id = scanLong("Enter article ID to search: ");
        Article foundArticle = compoundController.searchArticle(id);
        System.out.println(foundArticle);
    }

    private void deleteUserById() {
        Long id = scanLong("Enter user ID to delete: ");
        compoundController.deleteUser(id);
        System.out.println("User with ID " + id + " deleted.");
    }

    private void deleteBlogById() {
        Long id = scanLong("Enter blog ID to delete: ");
        compoundController.deleteBlog(id);
        System.out.println("Deleted blog with ID " + id);
    }

    private void deleteRoleById() {
        Long id = scanLong("Enter role ID to delete: ");
        compoundController.deleteRole(id);
        System.out.println("Deleted role with ID " + id);
    }

    private void deleteArticleById() {
        Long id = scanLong("Enter article ID to delete: ");
        compoundController.deleteArticle(id);
        System.out.println("Deleted article with ID " + id);
    }

    private void assignRoleToUser() {
        Long userId = scanLong("Enter user ID: ");
        User user = compoundController.searchUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        Long roleId = scanLong("Enter role ID to assign: ");
        Role role = compoundController.searchRole(roleId);
        if (role == null) {
            System.out.println("Role not found.");
            return;
        }
        user.getRoles().add(role);
        compoundController.updateUser(user);
        System.out.println("Role '" + role.getName() + "' assigned to user with email '" + user.getEmail() + "'.");
    }

    private void setBlogManager() {
        Long blogId = scanLong("Enter blog ID: ");
        Blog blog = compoundController.searchBlog(blogId);
        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }
        Long userId = scanLong("Enter manager user ID: ");
        User manager = compoundController.searchUser(userId);
        if (manager == null) {
            System.out.println("User not found.");
            return;
        }
        blog.setManager(manager);
        compoundController.updateBlog(blog);
        System.out.println("User '" + manager.getEmail() + "' is now the manager of blog '" + blog.getName() + "'.");
    }

    private void setBlogArticles() {
        Long blogId = scanLong("Enter blog ID: ");
        Blog blog = compoundController.searchBlog(blogId);
        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }

        Set<Article> articles = new HashSet<>();
        System.out.println("|INFO| Right now you are able to set multiple articles to one blog" +
                "\nif you want to go back, type \"-1\"");
        while (true) {
            Long articleId = scanLong("Enter article ID: ");
            if (articleId == -1) {
                break;
            }
            Article article = compoundController.searchArticle(articleId);
            if (article == null) {
                System.out.println("Article not found.");
                continue;
            }
            article.setBlog(blog);
            articles.add(article);
        }
        if (!articles.isEmpty()) {
            System.out.println(articles);
            blog.setArticles(articles);
            compoundController.updateBlog(blog);
            System.out.println("Articles are successfully set.");
        } else {
            System.out.println("There are no articles to set.");
        }
    }

    private void assignAuthorToArticle() {
        Long articleId = scanLong("Enter article ID: ");
        Article article = compoundController.searchArticle(articleId);
        if (article == null) {
            System.out.println("Article not found.");
            return;
        }
        Long newAuthorId = scanLong("Enter new author ID: ");
        User newAuthor = compoundController.searchUser(newAuthorId);
        if (newAuthor == null) {
            System.out.println("User not found.");
            return;
        }
        article.setAuthor(newAuthor);
        Set<Article> updatedUserArticles = newAuthor.getArticles();
        updatedUserArticles.add(article);
        compoundController.updateArticle(article);
        compoundController.updateUser(newAuthor);
        System.out.println("Article with ID " + articleId + " now has author '" + newAuthor.getEmail() + "'.");
    }

    private void searchUsersByEmail() {
        String keyword = scanAndValidateString("Enter email keyword to search: ");
        List<User> users = compoundController.findUsersByEmailContaining(keyword);
        if (users.isEmpty()) {
            System.out.println("No users found with the given keyword.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private String scanAndValidateString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private Long scanLong(String prompt) {
        Long number = null;
        while (number == null) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                number = Long.parseLong(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
        return number;
    }
}
