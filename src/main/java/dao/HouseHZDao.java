package dao;
import model.HouseDetailEntity;
import model.HouseHZEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plankton
 * @date 2018/06/04
 * @version 1.0.0
 * Data Access模块，用于存储、获取数据
 */
public class HouseHZDao implements Cloneable {

    //数据库驱动
    private static final String dbClassName = "org.mariadb.jdbc.Driver";

    //数据库的连接URL
    private static final String CONNECTION = "jdbc:mariadb://localhost:3306/househz?user=root&password=LEON4ns";

    //数据库的用户名
    private static final String USER = "root";

    //数据库的密码
    private static final String PASS = "LEON4ns";

    private Connection conn = null;
    private Statement stmt = null;

    //HouseHZDao类构造方法，用于启动数据库的连接
    public HouseHZDao() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECTION);
            stmt = conn.createStatement();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 产生HouseHZDao类的实例并返回
     * @return HouseHZDao
     */
    public static HouseHZDao getOne() {
        try {
            HouseHZDao hzDao = new HouseHZDao();
            return (HouseHZDao) hzDao.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取房源的信息
     * @return List<HouseHZEntity>
     */
    public List<HouseHZEntity> getHouseInfo() {
        List<HouseHZEntity> houseInfoList = new ArrayList<HouseHZEntity>();
        try {
            String sql = "SELECT * FROM `test3househz`";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HouseHZEntity houseInfo = new HouseHZEntity();
                houseInfo.setHouseName(rs.getString("HouseName"));
                houseInfo.setAddress(rs.getString("Address"));
                houseInfo.setPrice(rs.getString("price"));
                houseInfoList.add(houseInfo);
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseInfoList;
    }

    /**
     * 将转化好的数据写入数据库
     * @param houseDetailEntity HouseDetailEntity类
     * @return
     */
    public int addHouseDetial(HouseDetailEntity houseDetailEntity) {
        return 0;
    }

    /**
     * 将爬取的数据写入数据库
     * @param houseHZEntity HouseHZEntitty类
     * @return int
     */
    public void addHouseInfo(HouseHZEntity houseHZEntity) {
        try {
            String sql = "INSERT INTO `test2househz` (`HouseName`, `Address`, `price`) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, houseHZEntity.getHouseName());
            ps.setString(2, houseHZEntity.getAddress());
            ps.setString(3, houseHZEntity.getPrice());
            ps.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
