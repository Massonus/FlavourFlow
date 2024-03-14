function validateForm(event, csrf) {

    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (password === "") {
        document.getElementById("passwordError").textContent = "Please input password";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return false;

    }

    if (!(validateUsername(username))) {
        return false;
    }

    if (!(validatePassword(password, confirmPassword))) {
        return false;
    }

    if (!(validateEmail(email))) {
        return false;
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
            "X-CSRF-TOKEN": csrf,
        },
        body: body

    })
        .then(res => res.json())
        .then((data) => {

            if (data.isSameUsername) {
                document.getElementById("usernameError").textContent = "User with the same username is already exist";
                document.getElementById("usernameAlert").classList.remove('d-none');

            } else if (data.isSameEmail) {
                document.getElementById("emailError").textContent = "User with the same email is already exist";
                document.getElementById("emailAlert").classList.remove('d-none');

            }

            if (data.isSuccess) {
                window.location.href = "/login";
            }
        })
        .catch(error => {
            console.log(error);
        })
}

function closeAlertWindow() {
    let alerts = document.getElementsByClassName("alert-danger");
    for (const alert of alerts) {
        alert.classList.add("d-none");
    }
}

function validateUsername(username) {

    if (username === "") {
        document.getElementById("usernameError").textContent = "Please input username";
        document.getElementById("usernameAlert").classList.remove('d-none');
        return false;

    } else if (!/^[a-zA-Z][a-zA-Z0-9_]*$/.test(username)) {
        document.getElementById("usernameError").textContent = "Username must start with a letter and contain only letters, numbers, and underscores";
        document.getElementById("usernameAlert").classList.remove('d-none');
        return false;
    }

    return true;
}

function validatePassword(password, confirmPassword) {

    if (!(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{4,15}$/.test(password)) && !(password === "")) {
        document.getElementById("passwordError").textContent = "Password must be 4-15 characters long and contain at least one letter, one digit, and one special character";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return false;

    } else if (password !== confirmPassword) {
        document.getElementById("passwordError").textContent = "Passwords are different";
        document.getElementById("passwordAlert").classList.remove('d-none');
        return false;
    }

    return true;
}

function validateEmail(email) {

    if (email === "") {
        document.getElementById("emailError").textContent = "Please input email";
        document.getElementById("emailAlert").classList.remove('d-none');
        return false;

    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById("emailError").textContent = "Invalid email format";
        document.getElementById("emailAlert").classList.remove('d-none');
        return false;
    }

    return true;
}