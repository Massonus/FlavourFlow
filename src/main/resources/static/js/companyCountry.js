function createCountry(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;
    let title = document.getElementById("countryTitle").value.toUpperCase();

    const body = JSON.stringify({
        title: title
    });

    const url = "/country/add";

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
            window.location.href = "/admin/panel";
        })
        .catch(error => {
            console.log(error);
        })
}

function editCountry(event, countryId, csrf) {
    event.preventDefault();

    let title = document.getElementById("countryTitle").value.toUpperCase();

    const body = JSON.stringify({
        countryId: countryId,
        title: title
    });

    const url = `/country/edit`;

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf
        },
        body: body,
    })
        .then(res => {
            window.location.href = "/admin/panel";
        })
        .catch(error => {
            console.log(error);
        })
}

function deleteCountry(countryId, csrf) {

    const url = `/country/delete?id=${countryId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => {
            document.getElementById(`country-table-${countryId}`).remove();
        })
        .catch(error =>
            console.error(error));
}

