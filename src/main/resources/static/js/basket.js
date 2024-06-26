function saveOrDeleteBasketItem(productId, csrf, isProductInfo) {
    let element = document.getElementById(`cart-${productId}`);

    if (element.className === "bi bi-cart" || element.className === "bi bi-cart fa-2x") {
        saveBasketItem(productId, csrf, element, isProductInfo);
    } else {
        deleteBasketItem(productId, csrf, element, isProductInfo);
    }
}

function saveBasketItem(productId, csrf, iconElement, isProductInfo) {
    fetch(`/basket/add-item?productId=${productId}`, {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrf
        },
    })
        .then(response => {
            if (response.ok) {
                if (isProductInfo) {
                    iconElement.className = "bi bi-cart-fill fa-2x";
                } else {
                    iconElement.className = "bi bi-cart-fill";
                }
                changeBasketObjectsCount();
                document.getElementById(`success-${productId}`).style.display = 'inline';
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function deleteBasketItem(productId, csrf, iconElement, isProductInfo) {

    const body = JSON.stringify({
        productId: productId
    });

    fetch("/basket/delete-item", {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json",
            'X-CSRF-TOKEN': csrf
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (iconElement !== undefined) {
                if (isProductInfo) {
                    iconElement.className = "bi bi-cart fa-2x";
                } else {
                    iconElement.className = "bi bi-cart";
                }
                changeBasketObjectsCount();
                document.getElementById(`success-${productId}`).style.display = 'none';

            } else if (data !== undefined) {
                document.getElementById(`basket-item-${data.itemId}`).remove();
                document.getElementById("basket-total").innerHTML = `${data.total.toFixed(2) + '$'}`;
                changeBasketObjectsCount();

            } else {
                alert("Error! Reload the page and try again");
            }

        })
        .catch(error => console.log(error));
}

function changeAmount(productId, csrf) {

    let amount = document.getElementById(`newAmount-${productId}`).value;

    if (amount <= 0) {
        alert("Invalid amount");
        return;
    }

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
        .then(res => res.json())
        .then((data) => {
            document.getElementById(`object-sum-${productId}`).innerHTML = `${data.sum.toFixed(2) + '$'}`;
            document.getElementById("basket-total").innerHTML = `${data.total.toFixed(2) + '$'}`;
        })
        .catch(error => console.error(error));
}

function changeBasketObjectsCount() {

    const url = `/basket/count`;

    fetch(url, {
        method: 'GET',
    })
        .then(res => res.json())
        .then((data) => {
            document.getElementById(`basket-count`).innerHTML = `${data}`;
            let basketButton = document.getElementById("clear-basket-button");
            if (data === 0 && basketButton !== null) {
                basketButton.remove();
                document.getElementById("checkout-button").remove();
            }

        })
        .catch(error => console.error(error));
}

function clearBasket(csrf) {

    let url;

    if (confirm("Do you really want to clear your basket?")) {
        url = `/basket/clear`;
    } else {
        url = `/basket`;
    }

    fetch(url, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrf
        },
    })
        .then(response => {
            if (response.ok) {
                window.location.href = "/basket";
            }
        })
        .catch(error => console.error(error));
}