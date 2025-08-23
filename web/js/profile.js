document.addEventListener("DOMContentLoaded", () => {
  // پر کردن فرم از localStorage
  const userData = JSON.parse(localStorage.getItem("userData"));
  if (userData) {
    document.getElementById("firstname").value = userData.firstname;
    document.getElementById("lastname").value = userData.lastname;
    document.getElementById("email").value = userData.email;
  }

  // ارسال فرم به UserServlet
  const form = document.getElementById("profileForm");
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formData = new FormData(form);
    formData.append("action", "update");

    try {
      const response = await fetch("UserServlet", {
        method: "POST",
        body: formData
      });

      const result = await response.json();

      if (result.status === "success") {
        // بروزرسانی localStorage
        const updatedUser = result.user;
        localStorage.setItem("userData", JSON.stringify(updatedUser));
        localStorage.setItem("userFullname", updatedUser.firstname + " " + updatedUser.lastname);

        alert("تغییرات با موفقیت ذخیره شد ✅");
        window.location.href = "dashboard.html";
      } else {
        alert("خطا: " + result.message);
      }
    } catch (err) {
      console.error(err);
      alert("خطا در ارتباط با سرور!");
    }
  });
});
