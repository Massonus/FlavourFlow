function saveOrDeleteWishItem(productId, csrf) {
    let element = document.getElementById(`wish-${productId}`);

    if (element.className === "far fa-heart") {
        saveItem(productId, csrf, element);
    } else {
        deleteWishItem(productId, csrf, element);
    }
}

function saveItem(productId, csrf, iconElement) {
    fetch(`/wishes/add-item?id=${productId}`, {
        method: 'POST',
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

function deleteWishItem(productId, csrf, iconElement) {

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
                iconElement.className = "far fa-heart";

            } else if (data !== undefined) {
                console.log(data.itemId);
                document.getElementById(`wish-item-${data.itemId}`).remove();
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
            }

        })
        .catch(error => console.log(error));

}

function clearWishes(csrf) {

    let url;

    if (confirm("Do you really want to clear your wishes?")) {
        url = `/wishes/clear`;
    } else {
        url = `/wishes`;
    }

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

function closeWishAlertWindow() {
    document.getElementById("my-modal").classList.remove("open");
}
