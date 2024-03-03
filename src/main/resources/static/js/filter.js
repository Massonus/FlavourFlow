function filterCompany() {
    let sort = document.getElementById("sort").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let countryId = document.getElementById("companyCountry").value;

    const url = `/companies?categoryId=${categoryId}&countryId=${countryId}&sort=${sort}`;

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            return response.text();
        })
        .then((html) => {
            document.body.innerHTML = html;
        })
        .catch(error => console.error(error));
}