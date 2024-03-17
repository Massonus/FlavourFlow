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
                iconElement.className = "bi bi-cart";

            } else if (data !== undefined) {
                console.log(data.itemId);
                document.getElementById(`basket-item-${data.itemId}`).remove();
                document.getElementById("basket-total").innerHTML = `${data.total.toFixed(2) + '$'}`;
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