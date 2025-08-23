let products = [];
const container = document.getElementById("product-list");
const genderFilter = document.getElementById("genderFilter");
const categoryFilter = document.getElementById("categoryFilter");
const priceFilter = document.getElementById("priceFilter");
const priceValue = document.getElementById("priceValue");
const searchInput = document.getElementById("searchInput");
const cart = JSON.parse(localStorage.getItem("cart")) || [];

// دریافت محصولات از سرور
fetch("products")
  .then(res => res.json())
  .then(data => {
    // مسیر تصاویر: اگر فقط اسم فایل باشه، images/ اضافه میشه
    products = data.map(p => ({
      ...p,
      image: p.image.startsWith("images/") ? p.image : `images/${p.image}`
    }));
    renderProducts(products);
  })
  .catch(err => console.error("خطا در دریافت محصولات:", err));

function renderProducts(list) {
  container.innerHTML = "";
  list.forEach(p => {
    container.innerHTML += `
      <div class="col-md-4">
        <div class="card mb-4">
          <img src="${p.image}" class="card-img-top" alt="${p.name}">
          <div class="card-body">
            <h5 class="card-title">${p.name}</h5>
            <p class="card-text">${p.price.toLocaleString()} تومان</p>
            <button class="btn btn-primary" onclick="addToCart(${p.id})">افزودن به سبد خرید</button>
          </div>
        </div>
      </div>`;
  });
}

function addToCart(productId) {
  const existing = cart.find(p => p.id === productId);
  if (existing) existing.qty += 1;
  else {
    const product = products.find(p => p.id === productId);
    cart.push({ ...product, qty: 1 });
  }
  localStorage.setItem("cart", JSON.stringify(cart));
  alert("محصول به سبد خرید اضافه شد!");
}

function applyFilters() {
  const gender = genderFilter.value;
  const category = categoryFilter.value;
  const maxPrice = parseInt(priceFilter.value);
  const searchQuery = searchInput.value.toLowerCase();

  const filtered = products.filter(p => 
    (gender === "all" || p.gender === gender) &&
    (category === "all" || p.category === category) &&
    p.price <= maxPrice &&
    p.name.toLowerCase().includes(searchQuery)
  );

  renderProducts(filtered);
}

// بروزرسانی قیمت و فیلترها
priceFilter.addEventListener("input", () => {
  priceValue.textContent = `حداکثر قیمت: ${(+priceFilter.value).toLocaleString()} تومان`;
  applyFilters();
});
genderFilter.addEventListener("change", applyFilters);
categoryFilter.addEventListener("change", applyFilters);
searchInput.addEventListener("input", applyFilters);
