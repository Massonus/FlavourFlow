function createProduct(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("productTitle").value;
    let price = document.getElementById("productPrice").value;
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
                window.location.href = "/admin/panel";
            } else {
                window.location.href = "/admin/panel";
            }

        })
        .catch(error => {
            console.log(error);
        })
}