<#import "../parts/common.ftlh" as c>

<@c.page>

    <form action="/admin/add-new-user" method="post">
        <div>
            <label for="username">Username</label>
            <input type="text" id="username" name="username">
        </div>
        <br>
        <div>
            <label for="email">Email</label>
            <input type="email" id="email" name="email">
        </div>
        <br>
        <div>
            <label class="col-sm-8">Role:
                <input type="radio" name="role" value="USER" checked>USER
                <input type="radio" name="role" value="ADMIN">ADMIN
            </label>
        </div>
        <br>
        <div>
            <label for="password">Password</label>
            <input id="password" type="password" name="password">
        </div>
        <br>
        <div>
            <label for="confirmPassword">Password confirmation</label>
            <input id="confirmPassword" type="password" name="confirmPassword">
        </div>
        <br>
        <div>
            <a role="button" class="btn btn-outline-secondary mt-3" href="/admin/panel">Cancel</a>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
        </div>
    </form>

</@c.page>