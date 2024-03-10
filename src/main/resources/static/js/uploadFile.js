function uploadFile(file, companyId, title) {
    let csrf = document.getElementById("csrf").value;

    let formData = new FormData();
    formData.append("file", file);

    let url;

    if (companyId === undefined) {
        url = `/upload-company?title=${title}`;
    } else {
        url = `/upload-product?companyId=${companyId}&title=${title}`;
    }

    let response = fetch(url, {
        headers: {
            "X-CSRF-TOKEN": csrf
        },
        method: "POST",
        body: formData
    });
}

function checkFile(fileId) {

    let file = fileId === "productFileUpload" ? productFileUpload.files[0] : companyFileUpload.files[0];

    if (!["image/jpeg", "image/png", "image/gif", "image/svg+xml"].includes(file.type)) {
        alert("Only images");
        document.getElementById(fileId).value = '';
        return;
    }

    if (file.size > 1024 * 1024) {
        alert("File must be less then 1 MB");
        document.getElementById(fileId).value = '';
    }
}