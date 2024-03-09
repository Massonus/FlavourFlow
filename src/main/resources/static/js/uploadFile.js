async function uploadFile() {
    let csrf = document.getElementById("csrf").value;

    let file = fileupload.files[0];

    let formData = new FormData();
    formData.append("file", file);

    let response = await fetch('/upload', {
        headers: {
            "X-CSRF-TOKEN": csrf
        },
        method: "POST",
        body: formData
    });

    if (response.status === 200) {
        alert("File successfully uploaded!");
    }
}

function checkFile() {

    let file = fileupload.files[0];

    if (!["image/jpeg", "image/png", "image/gif", "image/svg+xml"].includes(file.type)) {
        alert("Only images");
        document.getElementById("fileupload").value = '';
        return;
    }

    if (file.size > 1024 * 1024) {
        alert("File must be less then 1 MB");
        document.getElementById("fileupload").value = '';
    }
}