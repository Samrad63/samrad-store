document.addEventListener("DOMContentLoaded", () => {
  // گرفتن اطلاعات کاربر
  const userData = JSON.parse(localStorage.getItem("userData"));

  if (userData) {
    const username = document.getElementById("username");
    if (username) {
      username.innerText = userData.firstname + " " + userData.lastname;
    }
  } else {
    // اگر وارد نشده بود
    window.location.href = "login.html";
    return;
  }

  // دکمه خروج
  const logoutBtn = document.getElementById("logoutBtn");
  if (logoutBtn) {
    logoutBtn.addEventListener("click", () => {
      localStorage.removeItem("userData");
      window.location.href = "login.html";
    });
  }
});
