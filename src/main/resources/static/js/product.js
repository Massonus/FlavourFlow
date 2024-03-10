function createProduct(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("productTitle").value;
    let price = parseFloat(document.getElementById("productPrice").value.replace(/,/, '.'));
    let productCategory = document.getElementById("productCategory").value;
    let imageLink = document.getElementById("productImageLink").value;

    let file = productFileUpload.files[0];

    if (file === undefined && imageLink.trim() === "") {
        document.getElementById("imageError").textContent = "Please input image link or upload your file";
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
        .then(res => {

            if (imageLink.trim() === "") {
                uploadFile(file, companyId, title);
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else {
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
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

            if (!(file === undefined)) {
                uploadFile(file, companyId, title);
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else {
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function deleteProduct(productId, csrf) {

    const url = `/product/delete?productId=${productId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf,
        },
    })
        .then(res => res.json())
        .then((data) => {
            window.location.href = `/product/admin/all-products?companyId=${data}`;
        })
        .catch(error =>
            console.error(error));
}