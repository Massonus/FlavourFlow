<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Your profile</h2>
                <div style="max-width: 600px" class="w-100">
                    <form action="/user/change-profile" method="post"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="staticUsername" class="col-sm-4 col-form-label">Username</label>
                            <div class="col-sm-8">
                                <#if isAdmin>
                                    <input type="text" name="username" value="${username}" class="form-control" id="staticUsername">

                                <#else>
                                    <input type="text" name="username" value="${username}" readonly class="form-control"
                                           id="staticUsername">
                                </#if>

                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="staticEmail" class="col-sm-4 col-form-label">Email</label>
                            <div class="col-sm-8">
                                <#if isAdmin>
                                    <input type="email" name="email" value="${email}" class="form-control" id="staticEmail">
                                <#else>
                                    <input type="email" name="email" value="${email}" readonly class="form-control" id="staticEmail">
                                </#if>

                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputPassword" class="col-sm-4 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="inputPassword" name="password"
                                       placeholder="password">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputPasswordConfirmation" class="col-sm-4 col-form-label">Password
                                confirmation</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="inputPasswordConfirmation"
                                       name="confirmPassword" placeholder="password confirmation">
                            </div>
                        </div>
                        <a role="button" class="btn btn-outline-secondary mt-3" href="/">Cancel</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>