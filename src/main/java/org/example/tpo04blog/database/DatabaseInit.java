package org.example.tpo04blog.database;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
public class DatabaseInit implements ApplicationRunner {

    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbct;

    public DatabaseInit(DataSource dataSource, ResourceLoader resourceLoader, JdbcTemplate jdbct) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
        this.jdbct = jdbct;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] tables = {"USERS", "ROLES", "BLOGS", "ARTICLES", "USER_ROLES"};

        String[][] tData = {
                {"USERS", "classpath:inserts/users.sql", "Users data initialized."},
                {"ROLES", "classpath:inserts/roles.sql", "Roles data initialized."},
                {"BLOGS", "classpath:inserts/blogs.sql", "Blogs data initialized."},
                {"ARTICLES", "classpath:inserts/articles.sql", "Articles data initialized."},
                {"USER_ROLES", "classpath:inserts/user_roles.sql", "User_Roles data initialized."}
        };

        boolean isSchemaMissing = true;
        for (String table : tables) {
            if (isTableExist(table)) {
                isSchemaMissing = false;
                break;
            }
        }
        if (isSchemaMissing) {
            runSql("classpath:schema.sql");
            System.out.println("Schema initialized.");
        }

        for (String[] data : tData) {
            String tableName = data[0];
            String scriptPath = data[1];
            String message = data[2];
            if (isTableEmpty(tableName)) {
                runSql(scriptPath);
                System.out.println(message);
            }
        }
    }

    private boolean isTableExist(String tableName) {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";
        Integer count = jdbct.queryForObject(sql, Integer.class, tableName.toUpperCase());
        return count != null && count > 0;
    }

    private boolean isTableEmpty(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        Integer count = jdbct.queryForObject(sql, Integer.class);
        return count == null || count == 0;
    }

    private void runSql(String scriptPath) {
        Resource sql = resourceLoader.getResource(scriptPath);
        ResourceDatabasePopulator rdp = new ResourceDatabasePopulator(sql);
        rdp.execute(dataSource);
    }
}
