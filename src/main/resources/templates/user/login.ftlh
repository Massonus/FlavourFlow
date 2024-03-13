<#import "../parts/common.ftlh" as c>

<@c.page>

    <br><br>
    <#--<form action="/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Login</button>
        </div>
    </form>-->

    <div id="container-form" class="container">
        <div class="form-login">
            <div class="first-row">
                <h2 class="first-login">LOGIN</h2>
            </div>
            <form>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Username">
                    <span class="input-group-addon">
              <span id="login-user" class="fa fa-user fa-2x"></span>
            </span>
                </div>
                <div class="input-group">
                    <input type="password" class="form-control" placeholder="password">
                    <span class="input-group-addon">
              <span class="fa fa-lock fa-2x"></span>
            </span>
                </div>
            </form>
            <div class="row">
                <div class="remember-me col-sm-6 col-xs-6">
                </div>
                <div class="register col-sm-6 col-xs-6">
                    <a href="#">Register</a>
                </div>
            </div>

            <a href="#" type="submit" class="btn btn-primary btn-lg login-btn">LOGIN</a>
        </div>
    </div>

</@c.page>

