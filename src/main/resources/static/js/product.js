// =======================================
// PRODUCT.JS PART 1
// =======================================

// Cart
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Wishlist
let wishlist = JSON.parse(localStorage.getItem("wishlist")) || [];

// Search Box
const searchInput = document.getElementById("inp");

// Sort Dropdown
const sortSelect = document.getElementById("sort");

// All Product Cards
const cards = document.querySelectorAll(".product-card");


// =======================================
// SEARCH
// =======================================

if (searchInput) {

    searchInput.addEventListener("keyup", function () {

        const value = this.value.toLowerCase();

        cards.forEach(card => {

            const title = card.querySelector(".product-title")
                              .innerText
                              .toLowerCase();

            if (title.includes(value)) {

                card.parentElement.style.display = "block";

            } else {

                card.parentElement.style.display = "none";

            }

        });

    });

}


// =======================================
// SORTING
// =======================================

if (sortSelect) {

    sortSelect.addEventListener("change", function () {

        document.querySelectorAll(".row.g-4").forEach(row => {

            let products = Array.from(row.children);

            products.sort((a, b) => {

                const priceA = parseInt(
                    a.querySelector(".price").innerText.replace(/[^\d]/g, "")
                );

                const priceB = parseInt(
                    b.querySelector(".price").innerText.replace(/[^\d]/g, "")
                );

                if (this.value === "low") {

                    return priceA - priceB;

                }

                if (this.value === "high") {

                    return priceB - priceA;

                }

                return 0;

            });

            products.forEach(product => {

                row.appendChild(product);

            });

        });

    });

}
// =======================================
// PART 2
// CATEGORY FILTER
// WISHLIST
// VIEW PRODUCT
// =======================================


// ---------------- CATEGORY FILTER ----------------

const categoryRadio = document.querySelectorAll("input[name='category']");

categoryRadio.forEach(radio => {

    radio.addEventListener("change", function () {

        const category = this.value;

        document.querySelectorAll(".product-card").forEach(card => {

            if (category === "all") {

                card.parentElement.style.display = "block";

            }

            else if (card.dataset.category === category) {

                card.parentElement.style.display = "block";

            }

            else {

                card.parentElement.style.display = "none";

            }

        });

    });

});



// ---------------- WISHLIST ----------------

document.querySelectorAll(".wishlist").forEach(btn => {

    btn.addEventListener("click", function (e) {

        e.preventDefault();

        const icon = this.querySelector("i");

        icon.classList.toggle("fa-regular");
        icon.classList.toggle("fa-solid");
        icon.classList.toggle("text-danger");

    });

});



// ---------------- VIEW PRODUCT ----------------

document.querySelectorAll(".btn-outline-success").forEach(btn => {

    btn.addEventListener("click", function () {

        const card = this.closest(".product-card");

        const name = card.querySelector(".product-title").innerText;

        const price = card.querySelector(".price").innerText;

        alert(
            "Product : " + name +
            "\nPrice : " + price
        );

    });

});
// Add To Cart
document.querySelectorAll(".add-cart").forEach(button => {

    button.addEventListener("click", function () {

        const name = this.dataset.name;
        const price = parseFloat(this.dataset.price);
        const image = this.dataset.image;

        // Check Existing Product
        let existingProduct = cart.find(item => item.name === name);

        if (existingProduct) {

            existingProduct.qty++;

        } else {

            cart.push({
                name: name,
                price: price,
                image: image,
                qty: 1
            });

        }

        // Save
        localStorage.setItem("cart", JSON.stringify(cart));

        // Update Count
        updateCartCount();

        // Button Animation
        this.innerHTML = "✔ Added";
        this.disabled = true;

        setTimeout(() => {

            this.innerHTML = "Cart";
            this.disabled = false;

        }, 1000);

    });

});


// ======================================
// UPDATE CART COUNT
// ======================================

function updateCartCount() {

    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    let total = 0;

    cart.forEach(item => {

        total += item.qty;

    });

    let count = document.getElementById("count");

    if (count) {

        count.innerHTML = total;

    }

}

// Load Count On Page Open
updateCartCount();