<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Changing password</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="changePassword(event, '${_csrf.token}')"
                          class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="password" class="col-sm-4 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <div id="passwordAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="passwordError"></strong>
                                </div>
                                <input type="password" class="form-control" id="password"
                                       placeholder="password">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="confirmPassword" class="col-sm-4 col-form-label">Password confirmation</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="confirmPassword"
                                       placeholder="password confirmation">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="oldPassword" class="col-sm-4 col-form-label">Old password</label>
                            <div class="col-sm-8">
                                <div id="oldPasswordAlert"
                                     class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="oldPasswordError"></strong>
                                </div>

                                <input type="password" class="form-control" id="oldPassword"
                                       placeholder="old password" oninput="closeAlertWindow()">

                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3" href="/user/profile">Cancel</a>

                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>