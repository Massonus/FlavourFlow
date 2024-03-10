function saveOrDeleteBasketItem(productId, csrf) {
    let element = document.getElementById(`cart-${productId}`);

    if (element.className === "bi bi-cart") {
        saveBasketItem(productId, csrf, element);
    } else {
        deleteBasketItem(productId, csrf, element);
    }
}

function saveBasketItem(productId, csrf, iconElement) {
    fetch(`/basket/add-item?productId=${productId}`, {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrf
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

function deleteBasketItem(productId, csrf, iconElement) {
    fetch(`/basket/delete-item?productId=${productId}`, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrf
        },
    })
        .then(response => {

            if (iconElement === undefined) {
                document.getElementById(`basket-item-${productId}`).remove();

            } else if (response.ok) {
                iconElement.className = "bi bi-cart";

            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function changeAmount(productId, csrf) {

    let amount = document.getElementById(`newAmount-${productId}`).value;

    const url = `/basket/change-amount`;

    const body = JSON.stringify({
        productId: productId,
        amount: amount
    });

    fetch(url, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json",
            'X-CSRF-TOKEN': csrf
        },
        body: body,
    })
        .then(response => {
            window.location.href = "/basket";
        })
        .catch(error => console.error(error));
}

function clearBasket() {

    let url;

    if (confirm("Do you really want to clear the basket?")) {
        url = `/basket/clear`;
    } else {
        url = `/basket`;
    }

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}