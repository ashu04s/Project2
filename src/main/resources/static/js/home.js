// ================= CART JS =================

// Get cart from localStorage
function getCart() {

    let cart = localStorage.getItem("cart");

    if (cart === null) {
        return [];
    }

    return JSON.parse(cart);
}


// Save cart
function saveCart(cart) {

    localStorage.setItem(
        "cart",
        JSON.stringify(cart)
    );
}


// ================= CART COUNT =================

function updateCartCount() {

    let cart = getCart();

    let totalQuantity = 0;

    cart.forEach(function(item) {

        totalQuantity += item.quantity;

    });


    let count = document.getElementById("count");

    if (count) {

        count.innerText = totalQuantity;

    }
}


// ================= ADD TO CART =================

function addToCart(name, price, image) {

    let cart = getCart();


    // Check product already exists
    let existingProduct = cart.find(function(item) {

        return item.name === name;

    });


    if (existingProduct) {

        existingProduct.quantity++;

    } else {

        cart.push({

            name: name,
            price: Number(price),
            image: image,
            quantity: 1

        });

    }


    saveCart(cart);

    updateCartCount();

    alert("Your item has been added to cart!");
}


// ================= BUTTONS =================

document.addEventListener("DOMContentLoaded", function() {

    updateCartCount();


    let buttons =
        document.querySelectorAll(".add-cart");


    buttons.forEach(function(button) {

        button.addEventListener("click", function() {

            let name =
                button.getAttribute("data-name");

            let price =
                button.getAttribute("data-price");

            let image =
                button.getAttribute("data-image");


            addToCart(
                name,
                price,
                image
            );

        });

    });

});