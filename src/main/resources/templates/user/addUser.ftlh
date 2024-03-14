<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">User create</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="createUser(event, ${currentUserId})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="username" class="col-sm-4 col-form-label">Username:</label>
                            <div class="col-sm-8">
                                <input id="username" type="text" class="form-control" placeholder="input username" required oninput="closeAlertWindow()">

                                <div id="usernameAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="usernameError"></strong>
                                </div>

                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="email" class="col-sm-4 col-form-label">Email:</label>
                            <div class="col-sm-8">
                                <input id="email" type="text" class="form-control" placeholder="input email" required oninput="closeAlertWindow()">

                                <div id="emailAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="emailError"></strong>
                                </div>

                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="password" class="col-sm-4 col-form-label">Password:</label>
                            <div class="col-sm-8">
                                <input id="password" type="password" class="form-control"
                                       placeholder="password" required oninput="closeAlertWindow()">
                                <div id="passwordAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="passwordError"></strong>
                                </div>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="confirmPassword" class="col-sm-4 col-form-label">Password confirmation:</label>
                            <div class="col-sm-8">
                                <input id="confirmPassword" type="password" class="form-control"
                                       placeholder="confirm password" required oninput="closeAlertWindow()">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="role" class="col-sm-4 col-form-label">Role:</label>
                            <div class="col-sm-8">
                                <select class="form-select" id="role" required>
                                    <option selected value="USER">USER</option>
                                    <option value="ADMIN">ADMIN</option>
                                </select>
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/admin/panel">Cancel</a>
                        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>