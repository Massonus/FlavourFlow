function saveOrDeleteWishItem(productId, csrf) {
    let element = document.getElementById(`wish-${productId}`);

    if (element.className === "far fa-heart") {
        saveItem(productId, csrf, element);
    } else {
        deleteItem(productId, csrf, element);
    }
}

function saveItem(productId, csrf, iconElement) {
    fetch(`/wishes/new-wish-item/${productId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(response => {
            if (response.ok) {
                iconElement.className = "fa-solid fa-heart";
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function deleteItem(productId, csrf, iconElement) {
    fetch(`/wishes/delete-from-wishes/${productId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(response => {
            if (response.ok) {
                iconElement.className = "far fa-heart";
            } else {
                alert("Error! Reload the page and try again");
            }
        })
        .catch(error => console.log(error));
}

function moveWishToBasket(productId, csrf) {

    fetch(`/wishes/move-wish-to-basket?id=${productId}`, {

        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => res.json())
        .then((data) => {

            if (data) {
                document.getElementById("my-modal").classList.add("open");
            } else {
                window.location.href = "/wishes";
            }

        })
        .catch(error => console.log(error));

}
