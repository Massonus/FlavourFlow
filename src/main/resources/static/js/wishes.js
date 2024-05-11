function saveOrDeleteWishItem(productId, csrf, isProductInfo) {
    let element = document.getElementById(`wish-${productId}`);

    if (element.className === "bi bi-heart" || element.className === "bi bi-heart fa-2x") {
        saveWishItem(productId, csrf, element, isProductInfo);
    } else {
        deleteWishItem(productId, csrf, element, isProductInfo);
    }
}

function saveWishItem(productId, csrf, iconElement, isProductInfo) {
    fetch(`/wishes/add-item?id=${productId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(response => {
            if (response.ok) {
                if (isProductInfo) {
                    iconElement.className = "bi bi-heart-fill fa-2x";
                } else {
                    iconElement.className = "bi bi-heart-fill";
                }
                checkWishes();
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function deleteWishItem(productId, csrf, iconElement, isProductInfo) {

    const body = JSON.stringify({
        productId: productId
    });

    fetch("/wishes/delete", {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (iconElement !== undefined) {
                if (isProductInfo) {
                    iconElement.className = "bi bi-heart fa-2x";
                } else {
                    iconElement.className = "bi bi-heart";
                }
                checkWishes();

            } else if (data !== undefined) {
                console.log(data.itemId);
                document.getElementById(`wish-item-${data.itemId}`).remove();
                checkWishes();
            } else {
                alert("Error! Reload the page and try again");
            }

        })
        .catch(error => console.log(error));


}

function moveWishToBasket(productId, csrf) {

    const body = JSON.stringify({
        productId: productId
    });

    fetch("/wishes/move", {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (data.isInBasket) {
                openAlertWindow();
            } else {
                document.getElementById(`wish-item-${data.itemId}`).remove();
                changeBasketObjectsCount();
                checkWishes();
            }

        })
        .catch(error => console.log(error));

}

function clearWishes(csrf) {

    if (!confirm("Do you really want to clear your wishes?")) {
        window.location.href = "/wishes";
    }

    let url = `/wishes/clear`;

    fetch(url, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrf
        },
    })
        .then(response => {
            if (response.ok) {
                window.location.href = "/wishes";
            }
        })
        .catch(error => console.error(error));
}

function openAlertWindow() {
    document.getElementById("my-modal").classList.add("open");
    window.addEventListener('keydown', (e) => {
        if (e.key === "Escape") {
            document.getElementById("my-modal").classList.remove("open")
        }
    });
    document.querySelector("#my-modal .modal__box").addEventListener('click', event => {
        event._isClickWithInModal = true;
    });
    document.getElementById("my-modal").addEventListener('click', event => {
        if (event._isClickWithInModal) return;
        event.currentTarget.classList.remove('open');
    });
}

function checkWishes() {

    const url = `/wishes/check`;

    fetch(url, {
        method: 'GET',
    })
        .then(res => res.json())
        .then((data) => {
            if (!data) {
                document.getElementById("wish-icon").innerHTML = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-heart-fill\" viewBox=\"0 0 16 16\">\n" +
                    "<path fill-rule=\"evenodd\" d=\"M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314\"/>\n" +
                    "</svg>";

            } else {
                document.getElementById("wish-icon").innerHTML = "<svg id=\"wish-icon\" xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-heart\" viewBox=\"0 0 16 16\">\n" +
                    "                                <path d=\"m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15\"/>\n" +
                    "                            </svg>";

                let button = document.getElementById("clear-wish-button");
                if (button !== null) {
                    button.remove();
                }
            }

        })
        .catch(error => console.error(error));
}

function closeWishAlertWindow() {
    document.getElementById("my-modal").classList.remove("open");
}
