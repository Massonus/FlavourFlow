<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <form onsubmit="createUser(event, ${currentUserId})">

        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username">
        </div>
        <br>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email">
        </div>
        <br>
        <div class="mb-3 row">
            <label for="role">Role:</label>
            <select class="form-select" id="role">
                <option selected value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>

        <br>

        <span id="passwordError" class="error"></span>
        <div>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password">
        </div>
        <br>
        <div>
            <label for="confirmPassword">Password confirmation:</label>
            <input id="confirmPassword" type="password" name="confirmPassword">
        </div>
        <br>
        <div>
            <a role="button" class="btn btn-outline-secondary mt-3" href="/admin/panel">Cancel</a>
            <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
        </div>
    </form>

</@c.page>