<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-3 col-md-4 col-lg-12">
                    <div class="dropdown mt-3 bg-light py-3 px-4 categories rounded">
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
                </div>

                <#list companies as company>
                <div class="col-lg-4 col-sm-6 mb-3">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product-card">
                                <div class="product-thumb">
                                    <#if (company.getImage()??)>
                                        <div class="filters__img">
                                            <img alt="product image" src="/images/${company.getImage().getId()}.jpg"
                                                 class="card-img-top"/>
                                        </div>
                                    <#else>
                                        <div class="filters__img">
                                            <img alt="product image" src="${company.getImageLink()}"
                                                 class="card-img-top">
                                        </div>
                                    </#if>
                                </div>
                                <div class="product-details">
                                    <h4><a href="/product/all-products/${company.getId()}">${company.getTitle()}</a>
                                    </h4>
                                    <h5>${company.getCompanyType().getTitle()}</h5>
                                    <h5>${company.getKitchenCategory().getTitle()}</h5>
                                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Laudantium,
                                        repudiandae?</p>
                                    <div class="product-bottom-details d-flex justify-content-between">
                                        <div class="product-price">
                                            <p>${company.getPriceCategory()}</p>
                                        </div>
                                        <div class="product-links">
                                            <a href="#"><i class="fas fa-shopping-cart"></i></a>
                                            <a href="#"><i class="far fa-heart"></i></a>
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
    </section>

</@c.page>