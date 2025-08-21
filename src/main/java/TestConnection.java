import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            // بارگذاری Driver (نسخه جدید اوراکل نیاز نداره ولی بذاریم مطمئن‌تره)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // اطلاعات اتصال
            String url = "jdbc:oracle:thin:@//localhost:1521/orclpdb1";
            String user = "hesam";
            String password = "myjava123";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ اتصال موفق به دیتابیس!");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
