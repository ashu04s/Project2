// =======================================
// PRODUCT.JS
// =======================================

// =======================================
// CART
// =======================================

let cart = JSON.parse(localStorage.getItem("cart")) || [];


// =======================================
// WISHLIST
// =======================================

let wishlist = JSON.parse(localStorage.getItem("wishlist")) || [];


// =======================================
// SEARCH BOX
// =======================================

const searchInput = document.getElementById("inp");


// =======================================
// SORT DROPDOWN
// =======================================

const sortSelect = document.getElementById("sort");


// =======================================
// ALL PRODUCT CARDS
// =======================================

const cards = document.querySelectorAll(".product-card");


// =======================================
// SEARCH
// =======================================

if (searchInput) {

    searchInput.addEventListener("keyup", function () {

        const value = this.value.toLowerCase();

        cards.forEach(card => {

            const titleElement = card.querySelector(".product-title");

            if (!titleElement) {
                return;
            }

            const title = titleElement.innerText.toLowerCase();

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

                const priceElementA = a.querySelector(".price");
                const priceElementB = b.querySelector(".price");

                if (!priceElementA || !priceElementB) {
                    return 0;
                }

                const priceA = parseInt(
                    priceElementA.innerText.replace(/[^\d]/g, "")
                );

                const priceB = parseInt(
                    priceElementB.innerText.replace(/[^\d]/g, "")
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
// CATEGORY FILTER
// =======================================

const categoryRadio =
    document.querySelectorAll("input[name='category']");

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


// =======================================
// WISHLIST
// =======================================

document.querySelectorAll(".wishlist").forEach(btn => {

    btn.addEventListener("click", function (e) {

        e.preventDefault();

        const icon = this.querySelector("i");

        if (!icon) {
            return;
        }

        icon.classList.toggle("fa-regular");
        icon.classList.toggle("fa-solid");
        icon.classList.toggle("text-danger");

    });

});


// =======================================
// VIEW PRODUCT
// =======================================

document.querySelectorAll(".btn-outline-success").forEach(btn => {

    btn.addEventListener("click", function () {

        const card = this.closest(".product-card");

        if (!card) {
            return;
        }

        const nameElement = card.querySelector(".product-title");
        const priceElement = card.querySelector(".price");

        if (!nameElement || !priceElement) {
            return;
        }

        const name = nameElement.innerText;
        const price = priceElement.innerText;

        alert(
            "Product : " + name +
            "\nPrice : " + price
        );

    });

});


// =======================================
// ADD TO CART
// =======================================

document.querySelectorAll(".add-cart").forEach(button => {

    button.addEventListener("click", function () {

        const name = this.dataset.name;
        const price = Number(this.dataset.price);
        const image = this.dataset.image;

        // Check existing product
        let existingProduct = cart.find(
            item => item.name === name
        );

        if (existingProduct) {

            // Support both qty and quantity
            existingProduct.qty =
                Number(existingProduct.qty ?? existingProduct.quantity ?? 0) + 1;

            // Keep quantity also synchronized
            existingProduct.quantity = existingProduct.qty;

        } else {

            cart.push({

                name: name,
                price: price,
                image: image,

                qty: 1,
                quantity: 1

            });

        }


        // Save cart
        localStorage.setItem(
            "cart",
            JSON.stringify(cart)
        );


        // Update cart count
        updateCartCount();


        // Button animation
        this.innerHTML = "✔ Added";

        this.disabled = true;


        setTimeout(() => {

            this.innerHTML = "Cart";

            this.disabled = false;

        }, 1000);

    });

});


// =======================================
// UPDATE CART COUNT
// =======================================

function updateCartCount() {

    let cartData =
        JSON.parse(localStorage.getItem("cart")) || [];

    let total = 0;


    cartData.forEach(item => {

        // Support both qty and quantity
        const quantity =
            Number(item.qty ?? item.quantity ?? 0);

        // Prevent NaN
        if (!isNaN(quantity)) {

            total += quantity;

        }

    });


    const count =
        document.getElementById("count");


    if (count) {

        count.innerText = total;

    }

}


// =======================================
// LOAD CART COUNT WHEN PAGE OPENS
// =======================================

document.addEventListener("DOMContentLoaded", function () {

    updateCartCount();

});