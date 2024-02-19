<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form name="myForm" onsubmit="return validateForm()" method="post" action="/registration" class="form-inline" >

                <label for="login">Login:</label>
                <input type="text" id="login" name="username"><br>
                <label for="login" class="small-font">(login must start with a letter and contain only letters, numbers, and
                    underscores)</label><br>
                <span id="loginError" class="error"></span><br><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password"><br>
                <label for="password" class="small-font">(password must be 4-15 characters long and contain at least one letter, one
                    digit, and one special character)</label><br>
                <span id="passwordError" class="error"></span><br><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email"><br>
                <span id="emailError" class="error"></span><br><br>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Send">
            </form>
        </div>
    </div>

</@c.page>



