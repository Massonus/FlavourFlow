<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="dropdown mt-3 bg-light py-3 px-4 rounded">
        <div class="dropdown-toggle d-flex align-items-center" type="button" data-bs-toggle="dropdown"
             aria-expanded="false">
            <p class="mb-0">Sort by</p>
        </div>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/companies">(Default)</a></li>
            <li><a class="dropdown-item" href="/companies/sort?sort=descending">Sort by price:
                    descending</a>
            </li>
            <li><a class="dropdown-item" href="/companies/sort?sort=ascending">Sort by price:
                    ascending</a>
            </li>
            <li><a class="dropdown-item" href="/companies/sort?sort=a-z">Sort by name: A-Z</a>
            </li>
            <li><a class="dropdown-item" href="/companies/sort?sort=z-a">Sort by name: Z-A</a>
            </li>
        </ul>
    </div>

    <form action="/companies/filter" method="get" class="me-2">
        <div class="mb-3">
            <label for="kitchenCategory" class="form-label">Kitchen category:</label>
            <select name="categoryId" class="form-select" id="kitchenCategory">
                <option selected value=""></option>
                <#list categories as category>
                    <option value="${category.getId()}"> ${category.getTitle()}</option>
                </#list>
            </select>
        </div>

        <div class="mb-3">
            <label for="companyType" class="form-label">Company type:</label>
            <select name="typeId" class="form-select" id="companyType">
                <option value=""></option>
                <#list types as type>
                    <option value="${type.getId()}"> ${type.getTitle()}</option>
                </#list>
            </select>
        </div>

        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
    <br><br>

    <#list companies as company>
        <div class="col-12 col-md-8 col-lg-9">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4">
                <div class="card border-none">
                    <#if (company.getImage()??)>
                        <img alt="product image" src="/images/${company.getImage().getId()}.jpg" class="card-img-top"/>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${company.getTitle()}</h5>
                        <h5 class="card-title">${company.getPriceCategory()}</h5>
                        <p class="text-muted">${company.getCompanyType().getTitle()}</p>
                        <p class="text-muted">${company.getKitchenCategory().getTitle()}</p>
                        <a role="button" class="btn black-btn-outline"
                           href="/product/all-products/${company.getId()}">Go to products of this company</a>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>

</@c.page>