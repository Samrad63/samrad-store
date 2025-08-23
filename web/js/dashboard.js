document.addEventListener("DOMContentLoaded", () => {
  // پر کردن نام کاربر از localStorage
  const userData = JSON.parse(localStorage.getItem("userData"));
  if (userData) {
    document.getElementById("username").innerText = userData.firstname + " " + userData.lastname;
  } else {
    // اگر کاربر وارد نشده، هدایت به صفحه ورود
    window.location.href = "login.html";
  }

  // دکمه خروج
  const logoutBtn = document.getElementById("logoutBtn");
  logoutBtn.addEventListener("click", () => {
    localStorage.removeItem("userData");
    localStorage.removeItem("userFullname");
    window.location.href = "login.html";
  });
});
