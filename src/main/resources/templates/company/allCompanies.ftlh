<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row">
            <div class="col-3 col-md-4 col-lg-12">
                <div id="sort" class="dropdown mt-3 bg-light py-3 px-4 categories rounded">
                    <div class="dropdown-toggle d-flex align-items-center" type="button" data-bs-toggle="dropdown"
                         aria-expanded="false">
                        <p class="mb-0">Sort by</p>
                    </div>
                    <ul id="dropdown-menu" class="dropdown-menu">
                        <li><a class="dropdown-item" href="/companies">(Default)</a></li>
                        <li><a class="dropdown-item" href="/companies?sort=descending">Sort by price:
                                descending</a>
                        </li>
                        <li><a class="dropdown-item" href="/companies?sort=ascending">Sort by price:
                                ascending</a>
                        </li>
                        <li><a class="dropdown-item" href="/companies?sort=a-z">Sort by name: A-Z</a>
                        </li>
                        <li><a class="dropdown-item" href="/companies?sort=z-a">Sort by name: Z-A</a>
                        </li>
                    </ul>
                </div>

                <form action="/companies" method="get" class="me-2">
                    <div class="mb-3">
                        <label for="kitchenCategory" class="form-label">Kitchen category:</label>
                        <select name="categoryId" class="form-select" id="kitchenCategory">
                            <option selected value=""></option>
                            <#list categories as category>
                                <option class="filterOption"
                                        value="${category.getId()}"> ${category.getTitle()}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="companyCountry" class="form-label">Company type:</label>
                        <select name="countryId" class="form-select" id="companyCountry">
                            <option value=""></option>
                            <#list types as type>
                                <option class="filterOption" value="${type.getId()}"> ${type.getTitle()}</option>
                            </#list>
                        </select>
                    </div>

                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>

            <#list companies as company>
                <div class="col-lg-4 col-sm-6 mb-3">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product-card">
                                <div class="product-thumb">
                                    <#if (company.getImage()??)>
                                        <div class="filters__img">
                                            <a href="/product/all-products/${company.getId()}"><img alt="product image"
                                                                                                    src="/images/${company.getImage().getId()}.jpg"
                                                                                                    class="card-img-top"/></a>
                                        </div>
                                    <#else>
                                        <div class="filters__img">
                                            <a href="/product/all-products/${company.getId()}"><img alt="product image"
                                                                                                    src="${company.getImageLink()}"
                                                                                                    class="card-img-top"></a>
                                        </div>
                                    </#if>
                                </div>
                                <div class="product-details">
                                    <h4><a href="/product/all-products/${company.getId()}">${company.getTitle()}</a></h4>
                                    <h5>${company.getCompanyCountry().getTitle()}</h5>
                                    <h5>${company.getKitchenCategory().getTitle()}</h5>
                                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Laudantium,
                                        repudiandae?</p>
                                    <div class="product-bottom-details d-flex justify-content-between">
                                        <div class="product-price">
                                            <p>${company.getPriceCategory()}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center fixed-bottom">
            <#if currentPage?number gt 0>
                <li class="page-item">
                    <a class="page-link" href="/companies?page=${currentPage - 1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}">Previous</a>
                </li>
            </#if>
            <#list 1..totalPages as i>
                <li class="page-item<#if i == currentPage + 1> active</#if>">
                    <a class="page-link" href="companies?page=${i-1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}">${i}</a>
                </li>
            </#list>
            <#if currentPage < totalPages - 1>
                <li class="page-item">
                    <a class="page-link" href="/companies?page=${currentPage + 1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}">Next</a>
                </li>
            </#if>
        </ul>
    </nav>

</@c.page>