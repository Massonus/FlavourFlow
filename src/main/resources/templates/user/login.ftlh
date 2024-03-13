<#import "../parts/common.ftlh" as c>

<@c.page>

    <br><br>

    <div id="container-form" class="container">
        <div class="form-login">
            <div class="first-row">
                <h2 class="first-login">LOGIN</h2>
            </div>

            <form action="/login" method="post">
                <div class="input-group">
                    <input id="username" name="username" type="text" class="form-control" placeholder="Username">
                    <span class="input-group-addon">
              <span id="login-user" class="fa fa-user fa-2x"></span>
            </span>
                </div>

                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    test
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <div class="input-group">
                    <input id="password" name="password" type="password" class="form-control" placeholder="password">

                    <span class="input-group-addon">
              <span class="fa fa-lock fa-2x"></span>
            </span>
                </div>
                <div class="row">
                    <div class="remember-me col-sm-6 col-xs-6">
                    </div>
                    <div class="register col-sm-6 col-xs-6">
                        <a href="registration">Register</a>
                    </div>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-lg login-btn">LOGIN</button>
            </form>
        </div>
    </div>

</@c.page>

