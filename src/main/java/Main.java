import model.Product;
import repository.ProductDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1",
                    "HESAM",
                    "myjava123"
            );

            ProductDAO dao = new ProductDAO(connection);

            // گرفتن همه محصولات
            List<Product> products = dao.getAllProducts();
            for (Product p : products) {
                System.out.println(p.getId() + " | " + p.getName() + " | " + p.getPrice() + " | " + p.getCategory());
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
