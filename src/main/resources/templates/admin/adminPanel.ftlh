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
                        <#if !(admin.getId() == user.getId() || admin.getRedactor() == user.getId() || user.getUsername() == "admin")>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/user/edit-user/${user.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">

                            <a role="button" class="btn btn-outline-danger"
                               href="/user/delete-user/${user.getId()}">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <a role="button" class="btn black-btn-outline mt-3" href="/user/add-new-user">Add new</a>
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
                <#list companyCountry as country>
                    <tr>
                        <th class="p-3" scope="row">${country.getId()}</th>
                        <td class="p-3">${country.getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/category/edit-category/${country.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-outline-danger"
                               onclick="deleteKitchenCategory(event, ${country.getId()})">
                                <i class="bi bi-trash text-danger"></i>
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/category/add-new-category">Add new
                    category</a>
            </div>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Type id</th>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>
                <#list types as type>
                    <tr>
                        <th class="p-3" scope="row">${type.getId()}</th>
                        <td class="p-3">${type.getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/type/edit-type/${type.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <a role="button" class="btn btn-outline-danger"
                               href="/type/delete-type/${type.getId()}">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/type/add-new-type">Add new type</a>
            </div>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Company id</th>
                    <th scope="col">Title</th>
                    <th scope="col">Price category</th>
                    <th scope="col">Products count</th>
                    <th scope="col">Company type</th>
                    <th scope="col">Category</th>

                </tr>
                </thead>
                <tbody>
                <#list companies as company>
                    <tr>
                        <th class="p-3" scope="row">${company.getId()}</th>
                        <td class="p-3">${company.getTitle()}</td>
                        <td class="p-3">${company.getPriceCategory()}</td>
                        <td class="p-3">${company.getCountOfProducts()}</td>
                        <td class="p-3">${company.getCompanyCountry().getTitle()}</td>
                        <td class="p-3">${company.getKitchenCategory().getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/companies/edit/${company.getId()}">Edit title</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/product/admin/all-products/${company.getId()}">Edit products</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/companies/delete/${company.getId()}"><i
                                        class="bi bi-trash text-danger"></i></a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/companies/add-company">Add new company</a>
            </div>
        </div>
    </section>


</@c.page>



