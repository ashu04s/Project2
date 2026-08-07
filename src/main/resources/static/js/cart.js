// ======================================
// CART.JS PART 1
// LOAD CART & DISPLAY PRODUCTS
// ======================================

// Get Cart Data
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// HTML Elements
const cartItems = document.getElementById("cart-items");
const totalPrice = document.getElementById("total-price");

// Display Cart
function displayCart() {

    if (!cartItems) return;

    cartItems.innerHTML = "";

    let grandTotal = 0;

    // Empty Cart
    if (cart.length === 0) {

        cartItems.innerHTML = `
            <div class="text-center py-5">
                <h3>Your Cart is Empty</h3>

                <a href="/products"
                   class="btn btn-success mt-3">

                    Continue Shopping

                </a>

            </div>
        `;

        if (totalPrice) {
            totalPrice.innerHTML = "₹0";
        }

        return;
    }

    // Show Products
    cart.forEach((item, index) => {

        let total = item.price * item.qty;

        grandTotal += total;

        cartItems.innerHTML += `

<div class="card shadow-sm mb-4">

    <div class="row g-0">

        <div class="col-md-3">

            <img src="${item.image}"
                 class="img-fluid rounded-start"
                 alt="${item.name}">

        </div>

        <div class="col-md-9">

            <div class="card-body">

                <h5 class="fw-bold">
                    ${item.name}
                </h5>

                <p class="text-success fw-bold">
                    ₹${item.price}
                </p>

                <!-- Quantity Buttons -->

                <div class="d-flex align-items-center mt-3">

                    <button
                        class="btn btn-outline-success btn-sm"
                        onclick="decreaseQty(${index})">

                        <i class="fa-solid fa-minus"></i>

                    </button>

                    <span class="mx-3 fw-bold">

                        ${item.qty}

                    </span>

                    <button
                        class="btn btn-outline-success btn-sm"
                        onclick="increaseQty(${index})">

                        <i class="fa-solid fa-plus"></i>

                    </button>

                    <button
                        class="btn btn-danger btn-sm ms-4"
                        onclick="removeProduct(${index})">

                        <i class="fa-solid fa-trash"></i>

                    </button>

                </div>

                <h5 class="text-success mt-3">

                    Total : ₹${total}

                </h5>

            </div>

        </div>

    </div>

</div>

`;

    });

    if (totalPrice) {

        totalPrice.innerHTML = "₹" + grandTotal;

    }

}
// ======================================
// CART.JS PART 2
// QUANTITY + REMOVE + SAVE
// ======================================

// Increase Quantity
function increaseQty(index) {

    cart[index].qty++;

    saveCart();

}

// Decrease Quantity
function decreaseQty(index) {

    if (cart[index].qty > 1) {

        cart[index].qty--;

    } else {

        removeProduct(index);

        return;

    }

    saveCart();

}

// Remove Product
function removeProduct(index) {

    cart.splice(index, 1);

    saveCart();

}

// Save Cart
function saveCart() {

    localStorage.setItem("cart", JSON.stringify(cart));

    displayCart();

    updateCartCount();

}
// ======================================
// CART.JS PART 3
// CART COUNT + CLEAR CART + LOAD
// ======================================

// Update Header Cart Count
function updateCartCount() {

    let total = 0;

    cart.forEach(item => {

        total += item.qty;

    });

    const count = document.getElementById("count");

    if (count) {

        count.innerHTML = total;

    }

}

// Clear Cart
function clearCart() {

    if (confirm("Are you sure you want to clear your cart?")) {

        cart = [];

        localStorage.removeItem("cart");

        displayCart();

        updateCartCount();

    }

}

// Load Cart Automatically
window.addEventListener("load", function () {

    displayCart();

    updateCartCount();

});