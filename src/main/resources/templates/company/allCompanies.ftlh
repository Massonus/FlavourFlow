<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

<#--<nav class="bg-light py-3 px-4 categories rounded">
    <p class="fw-bold text-black fs-5">Categories</p>
    <ul class="nav flex-column my-2 p-0">
        <li class="nav-item" th:each="cat : ${categories}">
            <a class="nav-link ps-0" aria-current="page"
               th:href="@{/products/productsByCategory/{id}(id=${cat.id})}" th:text="${cat.title}">
                Cakes
            </a>
        </li>
    </ul>
</nav>-->

    <div class="dropdown mt-3 bg-light py-3 px-4 rounded">
        <div class="dropdown-toggle d-flex align-items-center" type="button" data-bs-toggle="dropdown"
             aria-expanded="false">
            <p class="mb-0">Sort by</p>
        </div>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/products">(Default)</a></li>
            </li>
            <li><a class="dropdown-item" href="/products/?sort=descending">Sort by price:
                    descending</a>
            </li>
            <li><a class="dropdown-item" href="/products/?sort=ascending">Sort by price:
                    ascending</a>
            </li>
            <li><a class="dropdown-item" href="/products/?sort=a-z">Sort by name: A-Z</a>
            </li>
            <li><a class="dropdown-item" href="/products/?sort=z-a">Sort by name: Z-A</a>
            </li>
        </ul>
    </div>

    <#list companies as company>
        <div class="col-12 col-md-8 col-lg-9">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4">
                <div class="card border-none">
                    <#if (company.getImage()??)>
                        <img alt="product image" src="/images/${company.getImage().getId()}.jpg" class="card-img-top"/>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${company.getTitle()}</h5>
                        <p class="text-muted">${company.getCompanyType()}</p>
                        <p class="text-muted">${company.getCategory().getTitle()}</p>
                        <a role="button" class="btn black-btn-outline"
                           href="/product/all-products/${company.getId()}">Go to products of this company</a>
                        <#if isAdmin>

                            <a role="button" class="btn black-btn-outline"
                               href="/companies/edit/${company.getId()}">Edit title</a>

                            <a role="button" class="btn black-btn-outline"
                               href="/product/add-product/${company.getId()}">Add product</a>

                            <a role="button" class="btn black-btn-outline"
                               href="/companies/delete/${company.getId()}">Delete</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>

    <#if isAdmin>
        <h2>Add Company</h2>
        <form enctype="multipart/form-data" method="post" action="/companies/new-company">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label>Company Type:
                <input type="radio" name="companyType" value="RESTAURANT" checked>RESTAURANT
                <input type="radio" name="companyType" value="CLUB">CLUB
                <input type="radio" name="companyType" value="CAFFE">CAFFE
            </label><br><br>

            <div class="choose-category">
                <label>Category:
                    <#list categories as category>
                    <input type="radio" name="categoryId" value=${category.getId()}> ${category.getTitle()}
                </label>
                </#list>
            </div>

            <label for="file">File:</label>
            <input type="file" name="file">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Send">
        </form>
        <br><br>
    </#if>


</@c.page>