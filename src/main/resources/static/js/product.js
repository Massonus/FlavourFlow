function createProduct(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("productTitle").value;
    let price = parseFloat(document.getElementById("productPrice").value.replace(/,/, '.'));
    let productCategory = document.getElementById("productCategory").value;
    let imageLink = document.getElementById("productImageLink").value;

    let file = productFileUpload.files[0];

    if (file === undefined && imageLink.trim() === "") {
        document.getElementById("productImageAlert").classList.remove('d-none');
        document.getElementById("productImageError").textContent = "Please input image link or upload your file";
        return;
    }

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

            if (imageLink.trim() === "") {
                uploadProductFile(file, data.productId, companyId);
            } else if (!(imageLink.trim() === "")) {
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function editProduct(event, productId, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("productTitle").value;
    let price = parseFloat(document.getElementById("productPrice").value.replace(/,/, '.'));
    let productCategory = document.getElementById("productCategory").value;
    let imageLink = document.getElementById("productImageLink").value;

    let file = productFileUpload.files[0];

    const body = JSON.stringify({
        productId: productId,
        title: title,
        price: price,
        productCategory: productCategory,
        imageLink: imageLink
    });

    const url = "/product/edit";

    fetch(url, {
        method: "PUT",
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf,
        },
        body: body,

    })
        .then(res => {

            if (!(file === undefined) && res.ok) {
                uploadProductFile(file, productId);
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else if (file === undefined && res.ok) {
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function deleteProduct(productId, csrf) {

    if (!confirm("Do you really want to delete this product?")) {
        return;
    }
    deleteProductFetch(productId, csrf);

}

function deleteProductFetch(productId, csrf) {

    const url = `/product/delete?productId=${productId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => {

            if (res.ok) {
                document.getElementById(`product-table-${productId}`).remove();
            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error =>
            console.error(error));

}