function checkOrder() {

    fetch("/basket/check", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(res => res.json())
        .then((data) => {

            console.log(data.isSuccess);
            if (data.isSuccess) {
                openOrderForm();
            } else {
                window.location.href = `/basket?size=${data.size}`;
            }
        })
        .catch(error => console.log(error));
}

function afterAlert(companyId) {
    console.log(companyId);
    window.location.href = `/basket?companyId=${companyId}`;
}

function createOrder(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let time = document.getElementById("orderTime").value;
    let countGuests = document.getElementById("countGuests").value;
    let date = document.getElementById("orderDate").valueAsDate;

    if (date <= new Date()) {
        console.log("error");
        return;
    }

    const body = JSON.stringify({
        date: date,
        time: time,
        countGuests: countGuests,
        isSuccess: true,
        companyId: companyId
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
        .then(res => {
            window.location.href = "/basket";
        })
        .catch(error => {
            console.log(error);
        })

}

function openOrderForm() {
    document.getElementById("order-form-modal").classList.add("open");
}
