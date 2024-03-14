<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Your profile</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="changeProfile(event, '${_csrf.token}')"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="staticUsername" class="col-sm-4 col-form-label">Username</label>
                            <div class="col-sm-8">

                                <#if isAdmin>
                                    <input type="text" value="${username}" class="form-control"
                                           id="staticUsername">

                                    <div id="usernameAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                        <strong id="usernameError"></strong>
                                    </div>

                                <#else>
                                    <input type="text" value="${username}" readonly class="form-control"
                                           id="staticUsername">
                                </#if>

                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="staticEmail" class="col-sm-4 col-form-label">Email</label>
                            <div class="col-sm-8">
                                <#if isAdmin>
                                    <input type="email" value="${email}" class="form-control"
                                           id="staticEmail">

                                    <div id="emailAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                        <strong id="emailError"></strong>
                                    </div>
                                <#else>
                                    <input type="email" value="${email}" readonly class="form-control"
                                           id="staticEmail">
                                </#if>

                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="oldPassword" class="col-sm-4 col-form-label">Old password</label>
                            <div class="col-sm-8">

                                <input type="password" class="form-control" id="oldPassword"
                                       placeholder="old password" oninput="closeAlertWindow()">

                                <div id="oldPasswordAlert"
                                     class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="oldPasswordError"></strong>
                                </div>

                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="password" class="col-sm-4 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="password"
                                       placeholder="password">
                                <div id="passwordAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="passwordError"></strong>
                                </div>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="confirmPassword" class="col-sm-4 col-form-label">Password confirmation</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="confirmPassword"
                                       placeholder="password confirmation">
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3" href="/">Cancel</a>

                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>