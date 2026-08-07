let count = document.getElementById("count");
let buttons = document.querySelectorAll(".ashu");

let cartCount = 0;

buttons.forEach(function(button){

    button.addEventListener("click", function(){

        cartCount++;

        count.innerText = cartCount;
        window.alert("your item has been added!!!")

    });

});