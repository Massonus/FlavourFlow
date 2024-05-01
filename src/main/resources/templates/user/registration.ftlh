<#import "../parts/common.ftlh" as c>

<@c.page>

    <div id="container-form" class="container">

        <div class="form-login">
            <div class="first-row">
                <h2 class="first-login">REGISTRATION</h2>
            </div>

            <form onsubmit="return validateForm(event, '${_csrf.token}')">

                <div id="usernameAlert" class="alert alert-login alert-danger alert-dismissible fade show d-none" role="alert">
                    <strong id="usernameError"></strong>
                </div>

                <div class="input-group">
                    <input id="username" type="text" class="form-control" placeholder="Username" oninput="closeAlertWindow()">
                    <span class="input-group-addon">
              <span id="login-user" class="fa fa-user fa-2x"></span>
            </span>
                </div>

                <div id="emailAlert" class="alert alert-login alert-danger alert-dismissible fade show d-none" role="alert">
                    <strong id="emailError"></strong>
                </div>

                <div class="input-group">
                    <input id="email" type="email" class="form-control" placeholder="Email" oninput="closeAlertWindow()">

                    <span class="input-group-addon">
              <span class="fa-solid fa-envelope fa-2x"></span>
            </span>
                </div>

                <div id="passwordAlert" class="alert alert-login alert-danger alert-dismissible fade show d-none" role="alert">
                    <strong id="passwordError"></strong>
                </div>

                <div class="input-group">
                    <input id="password" type="password" class="form-control" placeholder="Password" oninput="closeAlertWindow()">

                    <span class="input-group-addon">
              <span class="fa fa-lock fa-2x"></span>
            </span>
                </div>

                <div id="confirmPasswordAlert" class="alert alert-login alert-danger alert-dismissible fade show d-none" role="alert">
                    <strong id="confirmPasswordError"></strong>
                </div>

                <div class="input-group">
                    <input id="confirmPassword" type="password" class="form-control" placeholder="Confirm password" oninput="closeAlertWindow()">

                    <span class="input-group-addon">
              <span class="fa fa-lock fa-2x"></span>
            </span>
                </div>

                <div class="row">
                    <div class="remember-me col-sm-6 col-xs-6">
                    </div>
                    <div class="register col-sm-6 col-xs-6">
                        <a role="button" class="btn btn-outline-secondary mt-3" href="/login">Login</a>
                    </div>
                </div>

                <div id="captchaAlert" class="alert alert-login alert-danger alert-dismissible fade show d-none" role="alert">
                    <strong id="captchaError"></strong>
                </div>

                <div class="g-recaptcha m-3 row justify-content-center" data-sitekey="6LdIupkpAAAAAPYFGz2QASF-BNfI5wi7ikPy_MYy"></div>

                <button id="registration-btn" type="submit" class="btn btn-primary btn-lg login-btn">REGISTRATION</button>
            </form>
        </div>
    </div>

</@c.page>



