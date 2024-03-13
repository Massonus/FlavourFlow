function validateForm(event) {

    let csrf = document.getElementById("csrf").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (username === "") {
        document.getElementById("usernameError").textContent = "Please input username";
        document.getElementById("usernameAlert").classList.remove('d-none');
        return;

    } else if (!/^[a-zA-Z][a-zA-Z0-9_]*$/.test(username)) {
        document.getElementById("usernameError").textContent = "Username must start with a letter and contain only letters, numbers, and underscores";
        document.getElementById("usernameAlert").classList.remove('d-none');
        return;
    }

    if (password === "") {
        document.getElementById("passwordError").textContent = "Please input password";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return;

    } else if (!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{4,15}$/.test(password)) {
        document.getElementById("passwordError").textContent = "Password must be 4-15 characters long and contain at least one letter, one digit, and one special character";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return;

    } else if (password !== confirmPassword) {
        document.getElementById("passwordError").textContent = "Passwords are different";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return;
    }

    if (email === "") {
        document.getElementById("emailError").textContent = "Please input email";
        document.getElementById("emailAlert").classList.remove('d-none');
        return;

    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById("emailError").textContent = "Invalid email format";
        document.getElementById("emailAlert").classList.remove('d-none');
        return;
    }

    regUser(event, username, email, password, csrf);

}

function regUser(event, username, email, password, csrf) {
    event.preventDefault();

    const body = JSON.stringify({
        username: username,
        email: email,
        password: password,
    });

    const url = "/registration";

    fetch(url, {
        method: 'POST',
        redirect: 'follow',
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrf
        },
        body: body,

    })
        .then(res => {
            if (res.ok) {
                window.location.href = `/login`;
            }
        })
        .catch(error => {
            console.log(error);
        })
}