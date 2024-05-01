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
                        <a role="button" class="btn btn-outline-secondary mt-3" href="/reg">Register</a>
                    </div>
                </div>

                <#if error>
                    <div class="alert alert-login alert-danger alert-dismissible fade show" role="alert">
                        <strong>Username or password are incorrect</strong>
                    </div>
                </#if>
                <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                <button id="login-btn" type="submit" class="btn btn-primary btn-lg login-btn">LOGIN</button>
            </form>
        </div>
    </div>

</@c.page>

