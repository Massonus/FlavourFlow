<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-3 col-md-4 col-lg-12">
                    <div class="mb-3">

                        <label for="sort" class="form-label">Sort:</label>
                        <select onchange="filterProduct(event, ${id})" name="sort" class="form-select" id="sort">
                            <option <#if sort == "Default">selected</#if> value="Default">Default</option>
                            <option <#if sort == "priceDesc">selected</#if> class="filterOption" value="priceDesc"> Sort
                                by price: descending
                            </option>
                            <option <#if sort == "priceAsc">selected</#if> class="filterOption" value="priceAsc"> Sort
                                by price: ascending
                            </option>
                            <option <#if sort == "nameA">selected</#if> class="filterOption" value="nameA"> Sort by
                                name: A-Z
                            </option>
                            <option <#if sort == "nameZ">selected</#if> class="filterOption" value="nameZ"> Sort by
                                name: Z-A
                            </option>
                        </select>
                    </div>
                    <#--<div class="mb-3">
                        <label for="kitchenCategory" class="form-label">Kitchen category:</label>
                        <select onchange="filterCompany(event)" name="categoryId" class="form-select" id="kitchenCategory">
                            <option selected value="">Default</option>
                            <#list categories as category>
                                <option <#if category.getId() = (categoryId)!-1>selected</#if> class="filterOption" value="${category.getId()}">${category.getTitle()}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="companyCountry" class="form-label">Company type:</label>
                        <select onchange="filterCompany(event)" name="countryId" class="form-select" id="companyCountry">
                            <option value="">Default</option>
                            <#list countries as country>
                                <option <#if country.getId() = (countryId)!-1>selected</#if> class="filterOption" value="${country.getId()}">${country.getTitle()}</option>
                            </#list>
                        </select>
                    </div>-->
                </div>

                <#list products as product>
                        <div class="col-lg-3 col-sm-6 mb-3">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="product-card">
                                        <div class="product-thumb">
                                            <#if (product.getImage()??)>
                                                <div class="filters__product-img">
                                                    <a href="/product/${product.getId()}"><img alt="product image"
                                                                                               src="/images/${product.getImage().getId()}.jpg"
                                                                                               class="card-img-top"/></a>
                                                </div>
                                            <#else>
                                                <div class="filters__product-img">
                                                    <a href="/product/${product.getId()}"><img alt="product image"
                                                                                               src="${product.getImageLink()}"
                                                                                               class="card-img-top"></a>
                                                </div>
                                            </#if>
                                        </div>
                                        <div class="product-details">
                                            <h4><a href="/product/${product.getId()}">${product.getTitle()}</a></h4>
                                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Laudantium,
                                                repudiandae?</p>
                                            <div class="product-bottom-details d-flex justify-content-between">
                                                <div class="product-price">
                                                    ${product.getPrice() + '$'}
                                                </div>
                                                <div class="product-links">

                                                    <a onclick="saveOrDeleteBasketItem('${product.getId()}', '${_csrf.token}')"><#if service.isInBasket("${product.getId()}", "${currentUserId}")>
                                                            <i id="cart-${product.getId()}" class="bi bi-cart-fill"></i>
                                                        <#else>
                                                            <i id="cart-${product.getId()}" class="bi bi-cart"></i>
                                                        </#if></a>

                                                    <a onclick="saveOrDeleteWishItem('${product.getId()}', '${_csrf.token}')"><#if product.getIsinWishes()>
                                                            <i id="wish-${product.getId()}"
                                                               class="fa-solid fa-heart"></i>
                                                        <#else>
                                                            <i id="wish-${product.getId()}" class="far fa-heart"></i>
                                                        </#if></a>

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

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center fixed-bottom">
            <#if currentPage?number gt 0>
                <li class="page-item">
                    <a class="page-link" href="/product/all-products?id=${id}&page=${currentPage - 1}&sort=${sort!''}">Previous</a>
                </li>
            </#if>
            <#list 1..totalPages as i>
                <li class="page-item<#if i == currentPage + 1> active</#if>">
                    <a class="page-link" href="/product/all-products?id=${id}&page=${i-1}&sort=${sort!''}">${i}</a>
                </li>
            </#list>
            <#if currentPage < totalPages - 1>
                <li class="page-item">
                    <a class="page-link" href="/product/all-products?id=${id}&page=${currentPage + 1}&sort=${sort!''}">Next</a>
                </li>
            </#if>
        </ul>
    </nav>

</@c.page>



