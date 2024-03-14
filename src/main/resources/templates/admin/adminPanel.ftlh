<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <section>
        <div class="container">
            <h2>Users</h2>
            <br>
            <table id="user-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Role</th>
                    <th scope="col">Email</th>
                    <th scope="col">Redactor id</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr id="user-table-${user.getId()}">
                        <th class="p-3" scope="row">${user.getId()}</th>
                        <th class="p-3" scope="row">${user.getUsername()}</th>
                        <td class="p-3">${user.getStringRole()}</td>
                        <td class="p-3">${user.getEmail()}</td>
                        <td class="p-3">${user.getRedactor()}</td>
                        <#if !(admin.getId() == user.getId() || admin.getRedactor() == user.getStringId() || user.getId() == currentUserId || user.getRedactor() == "system")>
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
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
            <a role="button" class="btn black-btn btn-gradient mt-2 w-100" href="/user/add">Add new user</a>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <h2>Kitchen categories</h2>
            <br>
            <table id="category-table" class="table table-striped table-hover">
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
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/category/edit/${category.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <button class="btn btn-outline-danger"
                                    onclick="checkCompany(${category.getId()}, '${category.getClass().getSimpleName()}'.toUpperCase(), '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn btn-gradient mt-2 w-100" href="/category/add">Add new
                    category</a>
            </div>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <h2>Countries</h2>
            <br>
            <table id="country-table" class="table table-striped table-hover">
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
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/country/edit/${country.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <a role="button" class="btn btn-outline-danger"
                               onclick="checkCompany(${country.getId()}, '${country.getClass().getSimpleName()}'.toUpperCase(), '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn btn-gradient mt-2 w-100" href="/country/add">Add new country</a>
            </div>
        </div>
    </section>

    <section>
        <div class="container py-5">
            <h2>Companies</h2>
            <br>
            <table id="company-table" class="table table-striped table-hover">
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
                                           href="/company/edit/${company.getId()}">Edit</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/product/admin/all-products?companyId=${company.getId()}">Edit
                                products</a>
                        </td>

                        <td class="p-3"><a role="button" class="btn btn-outline-danger"
                                           onclick="deleteCompany(${company.getId()}, '${_csrf.token}')"><i
                                        class="bi bi-trash text-danger"></i></a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn btn-gradient mt-2 w-100" href="/company/add">Add new company</a>
            </div>
        </div>
    </section>

    <#if alertCompanies?has_content>
        <div class="${alertModal}" id="alert-companies-modal">
            <div class="modal__box">

                <button onclick="closeModal()" class="modal__close-btn" id="close-my-modal-btn">
                    <i class="fa-solid fa-xmark"></i>
                </button>

                <h4>This item contains ${size} <#if size == 1>company<#else>companies</#if>, choose the option</h4>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Company id</th>
                            <th>Title</th>
                            <th>Price category</th>
                            <th>Products count</th>
                            <th>Company country</th>
                            <th>Category</th>

                        </tr>
                        </thead>

                        <tbody>
                        <#list alertCompanies as company>
                            <tr>
                                <th>${company.getId()}</th>
                                <td>${company.getTitle()}</td>
                                <td>${company.getPriceCategory()}</td>
                                <td>${company.getCountOfProducts()}</td>
                                <td>${company.getCompanyCountry().getTitle()}</td>
                                <td>${company.getKitchenCategory().getTitle()}</td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>
                </div>

                <a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                   onclick="afterAlertWindow('${itemType}', ${checkId})">Move to the another item</a>

                <#if itemType == 'COMPANYCOUNTRY'>
                    <button onclick="deleteCountry(${checkId}, '${_csrf.token}')"
                            class="btn btn-outline-danger w-100 mt-2">Delete companies and products
                    </button>

                <#else>
                    <button onclick="deleteCategory(${checkId}, '${_csrf.token}')"
                            class="btn btn-outline-danger w-100 mt-2">Delete companies and products
                    </button>
                </#if>
            </div>
        </div>
    </#if>

    <div class="${chooseModal!''}" id="order-alert">
        <div class="modal__box">

            <button onclick="closeModal()" class="modal__close-btn" id="close-my-modal-btn">
                <i class="fa-solid fa-xmark"></i>
            </button>

            <#if existItems?has_content>
                <h3>Choose the another item</h3>
                <div class="container-buttons">
                    <#list existItems as item>
                        <a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                           onclick="moveCompanies(${checkId}, ${item.getId()}, '${item.getClass().getSimpleName()}'.toUpperCase(), '${_csrf.token}')">${item.getTitle()}</a>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
</@c.page>



