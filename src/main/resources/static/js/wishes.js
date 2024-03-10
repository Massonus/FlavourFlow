function saveOrDeleteWishItem(productId, csrf) {
    let element = document.getElementById(`wish-${productId}`);

    if (element.className === "far fa-heart") {
        saveItem(productId, csrf, element);
    } else {
        deleteItem(productId, csrf, element);
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

function clearWishes() {

    let url;

    if (confirm("Do you really want to clear the wishes?")) {
        url = `/wishes/clear`;
    } else {
        url = `/wishes`;
    }

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
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
                openAlertWindow();
            } else {
                window.location.href = "/wishes";
            }

        })
        .catch(error => console.log(error));

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
