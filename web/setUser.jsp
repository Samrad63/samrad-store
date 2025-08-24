<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.samrad.model.User" %>
<%
    // از سشن کاربر رو بگیر
    User user = (User) session.getAttribute("user");

    // اگر کاربر لاگین نکرده بود، برش گردون به لاگین
    if (user == null) {
        response.sendRedirect("login.html?error=1");
        return;
    }

    // مقادیر امن برای قراردادن در جاوااسکریپت (جلوگیری از null)
    String firstname = user.getFirstname() != null ? user.getFirstname() : "";
    String lastname  = user.getLastname()  != null ? user.getLastname()  : "";
    String email     = user.getEmail()     != null ? user.getEmail()     : "";
%>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <title>در حال ورود...</title>
</head>
<body>
<script>
    // اطلاعات کاربر را در localStorage ذخیره کن
    localStorage.setItem("userData", JSON.stringify({
        firstname: "<%= firstname %>",
        lastname: "<%= lastname %>",
        email: "<%= email %>"
    }));
    localStorage.setItem("userFullname", "<%= firstname %> <%= lastname %>");

    // سپس برو به داشبورد
    window.location.href = "dashboard.html";
</script>
</body>
</html>
