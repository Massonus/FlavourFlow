function createProduct(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("productTitle").value;
    let price = document.getElementById("productPrice").value;
    let productCategory = document.getElementById("productCategory").value;
    let imageLink = document.getElementById("productImageLink").value;

    const body = JSON.stringify({
        companyId: companyId,
        title: title,
        price: price,
        productCategory: productCategory,
        imageLink: imageLink
    });

    const url = "/product/add";

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
            console.log(data.title)
            window.location.href = "/admin/panel";
        })
        .catch(error => {
            console.log(error);
        })
}