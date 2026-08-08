// ======================================
// CART.JS
// ======================================


// ======================================
// GET CART
// ======================================

let cart = JSON.parse(localStorage.getItem("cart")) || [];


// ======================================
// CONVERT OLD DATA
// ======================================

cart = cart.map(item => {

    return {

        productId: item.productId,

        name: item.name || "Product",

        price: Number(item.price) || 0,

        image: item.image || "",

        quantity: Number(
            item.quantity ?? item.qty ?? 1
        )

    };

});


// Save converted cart
localStorage.setItem(
    "cart",
    JSON.stringify(cart)
);


// ======================================
// HTML ELEMENTS
// ======================================

const cartItems =
    document.getElementById("cart-items");

const totalPrice =
    document.getElementById("total-price");


// ======================================
// IMAGE PATH FUNCTION
// ======================================

function getImagePath(image) {

    if (!image) {

        return "/images/no-image.png";

    }


    // Full URL
    if (
        image.startsWith("http://") ||
        image.startsWith("https://")
    ) {

        return image;

    }


    // Already /uploads/filename.jpg
    if (image.startsWith("/uploads/")) {

        return image;

    }


    // Already /images/filename.jpg
    if (image.startsWith("/images/")) {

        return image;

    }


    // uploads/filename.jpg
    if (image.startsWith("uploads/")) {

        return "/" + image;

    }


    // images/filename.jpg
    if (image.startsWith("images/")) {

        return "/" + image;

    }


    // Filename coming from database
    // Example: fdf.jpg
    return "/uploads/" + image;

}


// ======================================
// DISPLAY CART
// ======================================

function displayCart() {

    if (!cartItems) {

        return;

    }


    cartItems.innerHTML = "";


    let grandTotal = 0;


    // ==================================
    // EMPTY CART
    // ==================================

    if (cart.length === 0) {

        cartItems.innerHTML = `

            <div class="text-center py-5">

                <h3>
                    Your Cart is Empty
                </h3>

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


    // ==================================
    // DISPLAY PRODUCTS
    // ==================================

    cart.forEach((item, index) => {


        let price =
            Number(item.price) || 0;


        let quantity =
            Number(item.quantity) || 1;


        let total =
            price * quantity;


        grandTotal += total;


        // Get correct image path
        let imagePath =
            getImagePath(item.image);


        cartItems.innerHTML += `

            <div class="card mb-3 shadow-sm">

                <div class="row g-0">


                    <!-- IMAGE -->

                    <div class="col-md-3">

                        <img
                            src="${imagePath}"

                            class="img-fluid rounded-start"

                            alt="${item.name}"

                            style="
                                width: 100%;
                                height: 140px;
                                object-fit: cover;
                            "

                            onerror="
                                this.onerror=null;
                                this.src='/images/no-image.png';
                            "
                        >

                    </div>


                    <!-- PRODUCT DETAILS -->

                    <div class="col-md-9">

                        <div class="card-body">


                            <!-- NAME -->

                            <h5 class="fw-bold">

                                ${item.name}

                            </h5>


                            <!-- PRICE -->

                            <p class="text-success fw-bold">

                                ₹${price}

                            </p>


                            <!-- QUANTITY -->

                            <div class="d-flex align-items-center mt-3">


                                <!-- MINUS -->

                                <button
                                    class="btn btn-outline-success btn-sm"

                                    onclick="
                                        decreaseQty(${index})
                                    "
                                >

                                    <i class="fa-solid fa-minus"></i>

                                </button>


                                <!-- QUANTITY -->

                                <span class="mx-3 fw-bold">

                                    ${quantity}

                                </span>


                                <!-- PLUS -->

                                <button
                                    class="btn btn-outline-success btn-sm"

                                    onclick="
                                        increaseQty(${index})
                                    "
                                >

                                    <i class="fa-solid fa-plus"></i>

                                </button>


                                <!-- REMOVE -->

                                <button
                                    class="btn btn-danger btn-sm ms-4"

                                    onclick="
                                        removeProduct(${index})
                                    "
                                >

                                    <i class="fa-solid fa-trash"></i>

                                </button>

                            </div>


                            <!-- TOTAL -->

                            <h5 class="text-success mt-3">

                                Total : ₹${total}

                            </h5>


                        </div>

                    </div>

                </div>

            </div>

        `;

    });


    // ==================================
    // GRAND TOTAL
    // ==================================

    if (totalPrice) {

        totalPrice.innerHTML =
            "₹" + grandTotal;

    }

}


// ======================================
// INCREASE QUANTITY
// ======================================

function increaseQty(index) {

    if (!cart[index]) {

        return;

    }


    cart[index].quantity =
        Number(cart[index].quantity || 1) + 1;


    saveCart();

}


// ======================================
// DECREASE QUANTITY
// ======================================

function decreaseQty(index) {

    if (!cart[index]) {

        return;

    }


    let quantity =
        Number(cart[index].quantity) || 1;


    if (quantity > 1) {

        cart[index].quantity =
            quantity - 1;

    } else {

        removeProduct(index);

        return;

    }


    saveCart();

}


// ======================================
// REMOVE PRODUCT
// ======================================

function removeProduct(index) {

    if (!cart[index]) {

        return;

    }


    cart.splice(index, 1);


    saveCart();

}


// ======================================
// SAVE CART
// ======================================

function saveCart() {

    localStorage.setItem(
        "cart",
        JSON.stringify(cart)
    );


    displayCart();


    updateCartCount();

}


// ======================================
// CART COUNT
// ======================================

function updateCartCount() {

    let total = 0;


    cart.forEach(item => {

        total += Number(
            item.quantity ?? item.qty ?? 0
        );

    });


    const count =
        document.getElementById("count");


    if (count) {

        count.innerHTML = total;

    }

}


// ======================================
// CLEAR CART
// ======================================

function clearCart() {

    if (
        confirm(
            "Are you sure you want to clear your cart?"
        )
    ) {

        cart = [];


        localStorage.removeItem(
            "cart"
        );


        displayCart();


        updateCartCount();

    }

}


// ======================================
// LOAD CART
// ======================================

window.addEventListener(
    "load",
    function () {

        displayCart();

        updateCartCount();

    }
);