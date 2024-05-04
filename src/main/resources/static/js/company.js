function createCompany(event) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("companyTitle").value;
    let countryId = document.getElementById("companyCountry").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let imageLink = document.getElementById("companyImageLink").value;
    let description = document.getElementById("companyDescription").value;

    let file = companyFileUpload.files[0];

    if (file === undefined && imageLink.trim() === "") {
        document.getElementById("companyImageAlert").classList.remove('d-none');
        document.getElementById("companyImageError").textContent = "Please input image link or upload your file";
        return;
    }

    const body = JSON.stringify({
        title: title,
        countryId: countryId,
        categoryId: categoryId,
        imageLink: imageLink,
        description: description
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
        .then(res => res.json())
        .then((data) => {

            if (imageLink.trim() === "") {
                uploadCompanyFile(file, data.companyId, true);
            } else if (!(imageLink.trim() === "")) {
                window.location.href = `/admin/panel`;
            } else {
                alert("Error detected, try again later")
            }

        })
        .catch(error => {
            console.log(error);
        })
}


function editCompany(event, companyId) {
    event.preventDefault();

    let csrf = document.getElementById("csrf").value;

    let title = document.getElementById("companyTitle").value;
    let countryId = document.getElementById("companyCountry").value;
    let categoryId = document.getElementById("kitchenCategory").value;
    let imageLink = document.getElementById("companyImageLink").value;
    let description = document.getElementById("companyDescription").value;

    let file = companyFileUpload.files[0];

    const body = JSON.stringify({
        companyId: companyId,
        title: title,
        countryId: countryId,
        categoryId: categoryId,
        imageLink: imageLink,
        description: description
    });

    const url = "/company/edit";

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
                uploadCompanyFile(file, companyId, false);

            } else if (file === undefined && res.ok) {
                window.location.href = `/admin/panel`;

            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error => {
            console.log(error);
        })
}

function deleteCompany(companyId, csrf) {

    if (!confirm("If you'll delete this company, all products will be deleted too")) {
        return;
    }

    deleteCompanyFetch(companyId, csrf);

}

function deleteCompanyFetch(companyId, csrf) {

    const url = `/company/delete?companyId=${companyId}`;

    fetch(url, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': csrf
        },
    })
        .then(res => res.json())
        .then((data) => {

            if (data.status === 500) {
                document.getElementById("token-modal").classList.add("open");

            } else if (data.status === 200) {
                document.getElementById(`company-table-${companyId}`).remove();
            } else {
                alert("Error detected, try again later");
            }

        })
        .catch(error =>
            console.error(error));
}

function checkCompany(itemId, itemType, csrf) {

    fetch(`/company/check?itemId=${itemId}&itemType=${itemType}`, {
        method: 'GET',
    })
        .then(res => res.json())
        .then((data) => {

            if (data.isSuccess && (itemType === 'COMPANYCOUNTRY')) {
                deleteCountry(itemId, csrf);
            } else if (data.isSuccess && (itemType === 'KITCHENCATEGORY')) {
                deleteCategory(itemId, csrf);
            } else {
                window.location.href = `/admin/panel?checkId=${itemId}&itemType=${itemType}`;
            }
        })
        .catch(error => console.log(error));
}

function afterAlertWindow(itemType, checkId) {
    window.location.href = `/admin/panel?itemType=${itemType}&checkId=${checkId}&isAfterAlert=${true}`;
}

function moveCompanies(oldId, newId, itemType, csrf) {

    const url = `/company/move`;

    const body = JSON.stringify({
        checkId: oldId,
        newId: newId,
        itemType: itemType
    });

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            'X-CSRF-TOKEN': csrf
        },
        body: body
    })
        .then(res => res.json())
        .then((data) => {

            if (data.itemType === 'COMPANYCOUNTRY') {
                deleteCountry(oldId, csrf);
            } else {
                deleteCategory(oldId, csrf);
            }

        })
        .catch(error =>
            console.error(error));
}