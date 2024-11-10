package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接管理工具类。
 * 因为是静态方法，可以直接 “类名.方法名调用”
 */
public class DBUtils {

    private static DruidDataSource dds;
    static {
        //        创建连接池对象
        dds = new DruidDataSource();
//        设置数据库连接信息
        dds.setUrl("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dds.setUsername("root");
        dds.setPassword("12345678");
//        设置初始连接数量
        dds.setInitialSize(3);
//        设置最大连接量
        dds.setMaxActive(5);
    }

    public static Connection getConn() throws SQLException {

//        从对象池里获取连接
        Connection conn = dds.getConnection();
        System.out.println(conn);
        return conn;
    }

}
