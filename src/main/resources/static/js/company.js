function createCompany(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("companyTitle").value;
    let priceCategory = document.getElementById("priceCategory").value;
    let countryId = document.getElementById("companyCountry").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let imageLink = document.getElementById("companyImageLink").value;

    let file = companyFileUpload.files[0];

    if (file === undefined && imageLink.trim() === "") {
        document.getElementById("companyImageError").textContent = "Please input image link or upload your file";
        return;
    }

    const body = JSON.stringify({
        title: title,
        priceCategory: priceCategory,
        countryId: countryId,
        categoryId: categoryId,
        imageLink: imageLink
    });

    const url = "/company/add";

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
                uploadFile(file, undefined, title);
                window.location.href = `/admin/panel`;
            } else {
                window.location.href = `/admin/panel`;
            }

        })
        .catch(error => {
            console.log(error);
        })
}
