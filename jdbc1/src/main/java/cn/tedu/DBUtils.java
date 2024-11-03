package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接管理工具类。
 * 因为是静态方法，可以直接 “类名.方法名调用”
 */
public class DBUtils {
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?" +
                        "characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                "root", "12345678");
    }

}
