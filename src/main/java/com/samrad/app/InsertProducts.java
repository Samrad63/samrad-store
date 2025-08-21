//import model.Product;
//import repository.ProductDAO;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.ArrayList;
//import java.util.List;
//
//public class InsertProducts {
//    public static void main(String[] args) {
//        try {
//            // اتصال به دیتابیس
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1",
//                    "HESAM",
//                    "myjava123"
//            );
//            ProductDAO dao = new ProductDAO(connection);
//
//            // آماده کردن لیست محصولات
//            List<Product> products = new ArrayList<>();
//            products.add(new Product(0, "پیراهن مردانه جوتی جینز", 1649000, "images/product1.jpg", "male", "پیراهن"));
//            products.add(new Product(0, "پیراهن مردانه بالون Balloon", 890000, "images/product2.jpg", "male", "پیراهن"));
//            products.add(new Product(0, "پولوشرت مردانه جوتی جینز", 1209500, "images/product3.jpg", "male", "تی‌شرت"));
//            products.add(new Product(0, "تیشرت مردانه جوتی جینز", 140000, "images/product4.jpg", "male", "تی‌شرت"));
//            products.add(new Product(0, "کت لینن مردانه جوتی جینز", 3024500, "images/product5.jpg", "male", "کت"));
//            products.add(new Product(0, "کت مردانه فریولی Feriolli", 5995000, "images/product6.jpg", "male", "کت"));
//            products.add(new Product(0, "جوراب ساق کوتاه مردانه کوتون", 1350000, "images/product7.jpg", "male", "جوراب"));
//            products.add(new Product(0, "جوراب ساق کوتاه مردانه کوتون", 987500, "images/product8.jpg", "male", "جوراب"));
//            products.add(new Product(0, "جوراب ساق کوتاه مردانه کوتون", 765000, "images/product9.jpg", "male", "جوراب"));
//            products.add(new Product(0, "شلوار زنانه جوتی جینز", 1649500, "images/product10.jpg", "female", "شلوار"));
//            products.add(new Product(0, "شلوار زنانه مالینو Malino", 4089000, "images/product11.jpg", "female", "شلوار"));
//            products.add(new Product(0, "شلوار اسلش زنانه جین وست", 1649500, "images/product12.jpg", "female", "شلوار"));
//            products.add(new Product(0, "تیشرت زنانه جوتی جینز", 1374500, "images/product13.jpg", "female", "تی‌شرت"));
//            products.add(new Product(0, "تیشرت زنانه واسا", 500000, "images/product14.jpg", "female", "تی‌شرت"));
//            products.add(new Product(0, "تیشرت زنانه جین وست", 1374000, "images/product15.jpg", "female", "تی‌شرت"));
//            products.add(new Product(0, "تیشرت زنانه جینز", 934500, "images/product16.jpg", "female", "تی‌شرت"));
//            products.add(new Product(0, "مانتو زنانه جوتی جینز", 2199500, "images/product17.jpg", "female", "مانتو"));
//            products.add(new Product(0, "مانتو زنانه ریس Rees", 1133400, "images/product18.jpg", "female", "مانتو"));
//            products.add(new Product(0, "مانتو زنانه مانگ Mong", 2534400, "images/product19.jpg", "female", "مانتو"));
//            products.add(new Product(0, "مانتو تابستانی جوتی جینز", 879400, "images/product20.jpg", "female", "مانتو"));
//            products.add(new Product(0, "کفش چرم طبیعی مردانه بالون", 3950000, "images/product21.jpg", "male", "کفش"));
//            products.add(new Product(0, "کفش چرم طبیعی مردانه ایران طب", 3301700, "images/product22.jpg", "male", "کفش"));
//            products.add(new Product(0, "کفش مردانه چرم طبیعی شیفر", 3455000, "images/product23.jpg", "male", "کفش"));
//            products.add(new Product(0, "کفش پاشنه دار چرم طبیعی زنانه شیفر", 3995000, "images/product24.jpg", "female", "کفش"));
//            products.add(new Product(0, "صندل زنانه اورز Orez", 2723000, "images/product25.jpg", "female", "کفش"));
//            products.add(new Product(0, "کفش زنانه اورز Orez", 2659300, "images/product26.jpg", "female", "کفش"));
//            products.add(new Product(0, "کیف دستی زنانه سولا Sola", 1552000, "images/product27.jpg", "female", "کیف"));
//            products.add(new Product(0, "کیف دستی زنانه صاد Saad", 2040000, "images/product28.jpg", "female", "کیف"));
//            products.add(new Product(0, "کیف زنانه بیسراک Bisrak", 1144000, "images/product29.jpg", "female", "کیف"));
//            products.add(new Product(0, "کیف زنانه باترفلای Butterfly", 2331000, "images/product30.jpg", "female", "کیف"));
//
//            // Insert همه محصولات
//            for (Product p : products) {
//                dao.addProduct(p);
//            }
//
//            System.out.println("تمام محصولات با موفقیت اضافه شدند!");
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
