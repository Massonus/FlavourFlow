function uploadProductFile(file, productId, companyId) {
    let csrf = document.getElementById("csrf").value;

    let formData = new FormData();
    formData.append("file", file);

    let url = `/upload-product?&productId=${productId}`;

    fetch(url, {
        method: "POST",
        headers: {
            "X-CSRF-TOKEN": csrf,
        },
        body: formData,

    })
        .then(res => res.json())
        .then((data) => {

            if (data.status === 200) {
                window.location.href = `/product/admin/all-products?companyId=${companyId}`;
            } else if (data.status === 500) {
                deleteProductFetch(productId, csrf);
                document.getElementById("token-modal").classList.add("open");
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function uploadCompanyFile(file, companyId, isAdd) {
    let csrf = document.getElementById("csrf").value;

    let formData = new FormData();
    formData.append("file", file);

    let url = `/upload-company?companyId=${companyId}`;

    fetch(url, {
        method: "POST",
        headers: {
            "X-CSRF-TOKEN": csrf,
        },
        body: formData,

    })
        .then(res => res.json())
        .then((data) => {

            if (data.status === 200) {
                window.location.href = "/admin/panel";

            } else if (data.status === 500 && isAdd) {

                deleteCompanyFetch(companyId, csrf);
                document.getElementById("token-modal").classList.add("open");

            } else if (data.status === 500 && !isAdd) {
                document.getElementById("token-modal").classList.add("open");

            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function checkFile(fileId) {

    let file = fileId === "productFileUpload" ? productFileUpload.files[0] : companyFileUpload.files[0];

    if (!["image/jpeg", "image/png", "image/gif", "image/svg+xml"].includes(file.type)) {
        alert("Only images");
        document.getElementById(fileId).value = '';
        document.querySelector('.input__file-button-text').innerText = 'Choose your file';
        return;
    }

    if (file.size > 1024 * 1024) {
        alert("File must be less then 1 MB");
        document.getElementById(fileId).value = '';
        document.querySelector('.input__file-button-text').innerText = 'Choose your file';
        return;
    }

    document.querySelector('.input__file-button-text').innerText = 'Selected';
}