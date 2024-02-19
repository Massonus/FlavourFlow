<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Role</th>
                    <th scope="col">Email</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <th class="p-3" scope="row">${user.getUsername()}</th>
                        <td class="p-3">${user.getStringRole()}</td>
                        <td class="p-3">${user.getEmail()}</td>
                        <td class="p-3 align-middle">
                            <a class="reset-anchor">
                                <i class="fas fa-trash-alt small text-muted"></i>
                            </a>
                            <#if !(admin.getId() == user.getId())>
                                <a role="button" class="btn btn-outline-danger"
                                   href="/admin/delete-user/${user.getId()}">
                                    <i class="bi bi-trash text-danger"></i>
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <a role="button" class="btn black-btn-outline mt-3" href="/admin/add-new-user">Add new</a>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Category id</th>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>
                <#list categories as category>
                    <tr>
                        <th class="p-3" scope="row">${category.getId()}</th>
                        <td class="p-3">${category.getTitle()}</td>
                        <td class="p-3 align-middle">
                            <a class="reset-anchor">
                                <i class="fas fa-trash-alt small text-muted"></i>
                            </a>
                            <a role="button" class="btn btn-outline-danger"
                               href="/admin/delete-category/${category.getId()}">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/admin/add-new-category">Add new category</a>
            </div>
        </div>
    </section>


</@c.page>


