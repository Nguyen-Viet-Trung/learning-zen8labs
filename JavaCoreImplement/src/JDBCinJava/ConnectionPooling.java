package JDBCinJava;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPooling {
    HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydatabase");
        config.setUsername("root");
        config.setPassword("password");

        // Tạo DataSource từ cấu hình
        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Kết nối thành công!");
            // Thực hiện các thao tác với cơ sở dữ liệu ở đây
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
