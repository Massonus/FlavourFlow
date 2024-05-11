function filterCompany(event, search) {
    event.preventDefault();
    let sort = document.getElementById("sort").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let countryId = document.getElementById("companyCountry").value;

    let url;

    if (search !== undefined) {
        url = `/company?categoryId=${categoryId}&countryId=${countryId}&sort=${sort}&search=${search}`;
    } else {
        url = `/company?categoryId=${categoryId}&countryId=${countryId}&sort=${sort}`;
    }

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}

function filterProduct(event, companyId) {
    event.preventDefault();

    let sort = document.getElementById("sort").value;
    let productType = document.getElementById("productType").value;

    const url = `/product/all-products?companyId=${companyId}&sort=${sort}&productCategory=${productType}`;

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

    const url = `/search?search=${search}`;

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            window.location.href = response.url;
        })
        .catch(error => console.error(error));
}
