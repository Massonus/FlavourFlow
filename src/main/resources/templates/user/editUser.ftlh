<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Product editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form enctype="multipart/form-data" action="/user/edit-user/${user.getId()}" method="post"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="username" class="col-sm-4 col-form-label">Username</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="username" name="username"
                                       value="${user.getUsername()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="email" class="col-sm-4 col-form-label">Email</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${user.getEmail()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="password" class="col-sm-4 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="password" name="password">
                            </div>
                        </div>

                        <div>
                            <label class="col-sm-8">Role:
                                <input type="radio" name="role" value="USER" checked>USER
                                <input type="radio" name="role" value="ADMIN">ADMIN
                            </label>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/admin/panel">Cancel</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>