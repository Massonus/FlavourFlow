function filterCompany(event) {
    event.preventDefault();
    let sort = document.getElementById("sort").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let countryId = document.getElementById("companyCountry").value;

    const url = `/companies?categoryId=${categoryId}&countryId=${countryId}&sort=${sort}`;

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}

function filterProduct(event, productId) {
    event.preventDefault();

    let sort = document.getElementById("sort").value;

    const url = `/product/all-products?id=${productId}&sort=${sort}`;

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}

function search(event) {
    event.preventDefault();

    let search = document.getElementById("search-input").value;

    const url = `/companies?search=${search}`;

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}
