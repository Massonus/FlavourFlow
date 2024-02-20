<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <div>
    <form action="/companies/filter" method="get" class="me-2">
        <div class="mb-3">
            <label for="bookCategory" class="form-label">Kitchen category:</label>
            <select name="categoryId" class="form-select" id="bookCategory">
                <option value=""></option>
                <#list categories as category>
                    <option value="${category.getId()}" selected="selected"> ${category.getTitle()}</option>
                </#list>
            </select>
        </div>
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>

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
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>

</@c.page>