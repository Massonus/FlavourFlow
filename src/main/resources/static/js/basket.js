function saveOrDeleteBasketItem(productId, csrf) {
    let element = document.getElementById(`cart-${productId}`);

    if (element.className === "bi bi-cart") {
        saveItem(productId, csrf, element);
    } else {
        deleteItem(productId, csrf, element);
    }
}

function saveItem(productId, csrf, iconElement) {
    fetch(`/product/new-basket-item/${productId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(response => {
            if (response.ok) {
                iconElement.className = "bi bi-cart-fill";
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function deleteItem(productId, csrf, iconElement) {
    fetch(`/basket/delete-from-basket/${productId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(response => {
            if (response.ok) {
                iconElement.className = "bi bi-cart";
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}