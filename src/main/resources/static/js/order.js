function checkOrder() {

    fetch("/basket/check", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(res => res.json())
        .then((data) => {

            if (!(data.isSuccess)) {
                window.location.href = `/basket?size=${data.size}`;
            } else {
                openOrderForm();
            }
        })
        .catch(error => console.log(error));
}

function createOrder() {

    let csrf = document.getElementById("_csrf").value;

    let name = document.getElementById("orderName").value;
    let phone = document.getElementById("orderPhone").value;
    let date = document.getElementById("date").valueAsDate;

    if (date <= new Date()) {
        console.log("error");
        return;
    }

    const body = JSON.stringify({
        name: name,
        phone: phone,
        date: date,
        isSuccess: true
    });

    const url = "/order/create";

    fetch(url, {
        method: "POST",
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,
    })
        .then(res => res.json())
        .then((data) => {
            if (!(data.isSuccess)) {
                /*window.location.href = "/order";*/
                openOrderAlertForm();
            } else {
                window.location.href = "/basket";
            }

        })
        .catch(error => {
            console.log(error);
        })

}

function openOrderForm() {
    window.addEventListener('keydown', (e) => {
        if (e.key === "Escape") {
            document.getElementById("order-modal").classList.remove("open")
        }
    });
    document.querySelector("#order-modal .modal__box").addEventListener('click', event => {
        event._isClickWithInModal = true;
    });
    document.getElementById("order-modal").addEventListener('click', event => {
        if (event._isClickWithInModal) return;
        event.currentTarget.classList.remove('open');
    });
}

function openOrderAlertForm() {
    window.addEventListener('keydown', (e) => {
        if (e.key === "Escape") {
            document.getElementById("order-alert-modal").classList.remove("open")
        }
    });
    document.querySelector("#order-alert-modal .modal__box").addEventListener('click', event => {
        event._isClickWithInModal = true;
    });
    document.getElementById("order-alert-modal").addEventListener('click', event => {
        if (event._isClickWithInModal) return;
        event.currentTarget.classList.remove('open');
    });
}

function closeOrderForm() {
    document.getElementById("order-modal").classList.remove("open");
}

function closeOrderAlertForm() {
    document.getElementById("order-alert-modal").classList.remove("open");
}