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
        System.out.println(" 17. Assign author to article");
        System.out.println(" 18. Search users by email containing a keyword");
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
            System.err.println("An error occurred: " + ex.getMessage());
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
        System.out.println("User with email '" + email + "' added.");
    }

    private void addNewBlog() {
        String blogName = scanAndValidateString("Enter blog name: ");
        Blog blog = new Blog();
        blog.setName(blogName);
        compoundController.addBlog(blog);
        System.out.println("Blog '" + blogName + "' added.");
    }

    private void addNewRole() {
        String roleName = scanAndValidateString("Enter role name: ");
        Role role = new Role();
        role.setName(roleName);
        compoundController.addRole(role);
        System.out.println("Role '" + roleName + "' added.");
    }

    private void addNewArticle() {
        String title = scanAndValidateString("Enter article title: ");
        Long authorId = scanLong("Enter author ID: ");
        Long blogId = scanLong("Enter blog ID: ");
        compoundController.addArticle(title, authorId, blogId);
        System.out.println("Article '" + title + "' added and associated with Author ID " + authorId + " and Blog ID " + blogId + ".");
    }


    private void searchUserById() {
        Long id = scanLong("Enter user ID to search: ");
        User foundUser = compoundController.searchUser(id);
        if (foundUser != null) {
            System.out.println(foundUser);
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    private void searchBlogById() {
        Long id = scanLong("Enter blog ID to search: ");
        Blog foundBlog = compoundController.searchBlog(id);
        if (foundBlog != null) {
            System.out.println(foundBlog);
        } else {
            System.out.println("Blog with ID " + id + " not found.");
        }
    }

    private void searchRoleById() {
        Long id = scanLong("Enter role ID to search: ");
        Role foundRole = compoundController.searchRole(id);
        if (foundRole != null) {
            System.out.println(foundRole);
        } else {
            System.out.println("Role with ID " + id + " not found.");
        }
    }

    private void searchArticleById() {
        Long id = scanLong("Enter article ID to search: ");
        Article foundArticle = compoundController.searchArticle(id);
    }

    private void deleteUserById() {
        Long id = scanLong("Enter user ID to delete: ");
        compoundController.deleteUser(id);
        System.out.println("Attempted to delete user with ID " + id + ".");
    }

    private void deleteBlogById() {
        Long id = scanLong("Enter blog ID to delete: ");
        compoundController.deleteBlog(id);
        System.out.println("Attempted to delete blog with ID " + id + ".");
    }

    private void deleteRoleById() {
        Long id = scanLong("Enter role ID to delete: ");
        compoundController.deleteRole(id);
        System.out.println("Attempted to delete role with ID " + id + ".");
    }

    private void deleteArticleById() {
        Long id = scanLong("Enter article ID to delete: ");
        compoundController.deleteArticle(id);
        System.out.println("Attempted to delete article with ID " + id + ".");
    }

    private void assignRoleToUser() {
        Long userId = scanLong("Enter user ID: ");
        Long roleId = scanLong("Enter role ID to assign: ");
        compoundController.assignRoleToUser(userId, roleId);
        System.out.println("Attempted to assign Role ID " + roleId + " to User ID " + userId + ".");
    }

    private void setBlogManager() {
        Long blogId = scanLong("Enter blog ID: ");
        Long userId = scanLong("Enter manager user ID: ");
        compoundController.setBlogManager(blogId, userId);
        System.out.println("Attempted to set User ID " + userId + " as manager for Blog ID " + blogId + ".");
    }

    private void setBlogArticles() {
        Long blogId = scanLong("Enter blog ID to assign articles to: ");
        Set<Long> articleIds = new HashSet<>();
        System.out.println("|INFO| Enter article IDs to assign to the blog." +
                "\nType \"-1\" when finished.");

        while (true) {
            Long articleId = scanLong("Enter article ID (or -1 to finish): ");
            if (articleId == -1) {
                break;
            }
            if (articleId < 0) {
                System.out.println("Invalid ID. Please enter a positive ID or -1.");
                continue;
            }
            articleIds.add(articleId);
        }

        if (!articleIds.isEmpty()) {
            compoundController.setBlogArticles(blogId, articleIds);
            System.out.println("Attempted to associate " + articleIds.size() + " articles with Blog ID " + blogId + ".");
        } else {
            System.out.println("No article IDs were entered to assign.");
        }
    }


    private void assignAuthorToArticle() {
        Long articleId = scanLong("Enter article ID: ");
        Long newAuthorId = scanLong("Enter new author ID: ");
        compoundController.assignAuthorToArticle(articleId, newAuthorId);
        System.out.println("Attempted to assign User ID " + newAuthorId + " as author for Article ID " + articleId + ".");
    }


    private void searchUsersByEmail() {
        String keyword = scanAndValidateString("Enter email keyword to search: ");
        List<User> users = compoundController.findUsersByEmailContaining(keyword);
        if (users == null || users.isEmpty()) {
            System.out.println("No users found with the email keyword: '" + keyword + "'.");
        } else {
            System.out.println("--- Users found containing keyword '" + keyword + "' ---");
            users.forEach(System.out::println);
            System.out.println("--------------------------------------------------");
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
            String input = scanner.nextLine().trim();
            try {
                number = Long.parseLong(input);
            } catch (NumberFormatException e) {
                if (prompt.contains("-1 to finish") && input.equals("-1")) {
                    return -1L;
                }
                System.out.println("Invalid number format. Please enter a valid whole number.");
            }
        }
        return number;
    }
}
