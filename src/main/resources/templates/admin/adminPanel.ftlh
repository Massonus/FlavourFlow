<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <section>
        <div class="container">
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
                    <tr id="user-table-${user.getId()}">
                        <th class="p-3" scope="row">${user.getUsername()}</th>
                        <td class="p-3">${user.getStringRole()}</td>
                        <td class="p-3">${user.getEmail()}</td>
                        <#if !(admin.getId() == user.getId() || admin.getRedactor() == user.getId() || user.getId() == currentUserId)>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/user/edit/${user.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">

                            <a role="button" class="btn btn-outline-danger"
                               onclick="deleteUser(${user.getId()}, '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <a role="button" class="btn black-btn-outline mt-3" href="/user/add">Add new user</a>
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
                    <tr id="category-table-${category.getId()}">
                        <th class="p-3" scope="row">${category.getId()}</th>
                        <td class="p-3">${category.getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/category/edit/${category.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <button class="btn btn-outline-danger"
                                    onclick="deleteKitchenCategory(${category.getId()}, '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/category/add">Add new
                    category</a>
            </div>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Country id</th>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>
                <#list countries as country>
                    <tr id="country-table-${country.getId()}">
                        <th class="p-3" scope="row">${country.getId()}</th>
                        <td class="p-3">${country.getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           href="/country/edit/${country.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <a role="button" class="btn btn-outline-danger"
                               onclick="deleteCountry(${country.getId()}, '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/country/add">Add new country</a>
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
                    <th scope="col">Company country</th>
                    <th scope="col">Category</th>

                </tr>
                </thead>
                <tbody>
                <#list companies as company>
                    <tr id="company-table-${company.getId()}">
                        <th class="p-3" scope="row">${company.getId()}</th>
                        <td class="p-3">${company.getTitle()}</td>
                        <td class="p-3">${company.getPriceCategory()}</td>
                        <td class="p-3">${company.getCountOfProducts()}</td>
                        <td class="p-3">${company.getCompanyCountry().getTitle()}</td>
                        <td class="p-3">${company.getKitchenCategory().getTitle()}</td>
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/company/edit/${company.getId()}">Edit title</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/product/admin/all-products?companyId=${company.getId()}">Edit
                                products</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           onclick="deleteCompany(${company.getId()}, '${_csrf.token}')"><i
                                        class="bi bi-trash text-danger"></i></a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline mt-3" href="/company/add">Add new company</a>
            </div>
        </div>
    </section>


</@c.page>



