package com.bs.pro.bean;

import com.bs.pro.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
@Slf4j
public class DBUtil {
    static Connection conn = null;
    static Statement stmt = null;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Constants.K3_DBURL, Constants.K3_DBUSER, Constants.K3_DBPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //打开连接
    static  {
        //加载驱动
        try {
            Class.forName(Constants.K3_DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //关闭连接
    public static void close(Connection conn) {
        try {
            if (stmt != null)
                stmt.close();
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        Statement st = null;
        Connection con = null;
        try {
            try {
                if (rs != null) {
                    st = rs.getStatement();
                    rs.close();
                }
            } finally {
                try {
                    if (st != null) {
                        con = st.getConnection();
                        st.close();
                    }
                } finally {
                    if (con != null) {
                        con.close();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获得查询的数据集
    //select * from student where name='' and sex=''
    public static ResultSet executeQuery(String sql) {
        Connection conn = getConnection();
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("K3ServiceImpl capacityCharts exception:{}",e);
        }
        return null;
    }

}
