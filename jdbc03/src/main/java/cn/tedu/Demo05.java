package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo05 {
    public static void main(String[] args) throws SQLException {
//        创建连接池对象
        DruidDataSource dds = new DruidDataSource();
//        设置数据库连接信息
        dds.setUrl("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dds.setUsername("root");
        dds.setPassword("12345678");
//        设置初始连接数量
        dds.setInitialSize(3);
//        设置最大连接量
        dds.setMaxActive(5);
//        从对象池里获取连接
        Connection conn = dds.getConnection();
        System.out.println(conn);

    }

}
