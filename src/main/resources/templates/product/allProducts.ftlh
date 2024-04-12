<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-3 col-md-4 col-lg-12">
                    <div class="mb-3">

                        <label for="sort" class="form-label">Sort:</label>
                        <select onchange="filterProduct(event, ${companyId})" name="sort" class="form-select" id="sort">
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
                </div>

                <div class="mb-3">
                    <label for="productType" class="form-label">Product category:</label>
                    <select onchange="filterProduct(event, ${companyId})" class="form-select" id="productType">

                        <option selected value="">Default</option>

                        <option <#if productCategory == "DRINK">selected</#if> class="filterOption" value="DRINK">
                            DRINK
                        </option>

                        <option <#if productCategory == "MEAL">selected</#if> class="filterOption" value="MEAL">MEAL
                        </option>

                    </select>
                </div>

                <#if products?has_content>
                    <#list products as product>
                        <div class="col-lg-3 col-sm-6 mb-3">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="product-card">
                                        <div class="product-thumb">

                                            <div class="filters__product-img">
                                                <a class="cursor-default"><img alt="product image"
                                                                               src="${product.getImageLink()}"
                                                                               class="card-img-top"></a>
                                            </div>

                                        </div>
                                        <div class="product-details">
                                            <h4>${product.getTitle()}</h4>
                                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Laudantium,
                                                repudiandae?</p>
                                            <div class="product-bottom-details d-flex justify-content-between">
                                                <div class="product-price">
                                                    ${product.getPrice() + '$'}
                                                </div>
                                                <div class="product-links">

                                                    <#if known>
                                                        <a onclick="saveOrDeleteBasketItem('${product.getId()}', '${_csrf.token}')"><#if basketService.isInBasket("${product.getId()}", "${currentUserId}")>
                                                                <i id="cart-${product.getId()}"
                                                                   class="bi bi-cart-fill"></i>
                                                            <#else>
                                                                <i id="cart-${product.getId()}" class="bi bi-cart"></i>
                                                            </#if></a>

                                                        <a onclick="saveOrDeleteWishItem('${product.getId()}', '${_csrf.token}')"><#if wishService.isInWishes("${product.getId()}", "${currentUserId}")>
                                                                <i id="wish-${product.getId()}"
                                                                   class="fa-solid fa-heart"></i>
                                                            <#else>
                                                                <i id="wish-${product.getId()}"
                                                                   class="far fa-heart"></i>
                                                            </#if></a>
                                                    </#if>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <h2>No such products</h2>
                </#if>

            </div>
        </div>
    </section>

    <#if products?has_content>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center fixed-bottom py-4">
                <#if currentPage?number gt 0>

                    <li class="page-item">
                        <a class="page-link"
                           href="/product/all-products?companyId=${companyId}&page=${currentPage - 1}&sort=${sort!''}">Previous</a>
                    </li>
                </#if>

                <#list 1..totalPages as i>
                    <li class="page-item<#if i == currentPage + 1> active</#if>">
                        <a class="page-link"
                           href="/product/all-products?companyId=${companyId}&page=${i-1}&sort=${sort!''}">${i}</a>
                    </li>
                </#list>

                <#if currentPage < totalPages - 1>
                    <li class="page-item">
                        <a class="page-link"
                           href="/product/all-products?companyId=${companyId}&page=${currentPage + 1}&sort=${sort!''}">Next</a>
                    </li>
                </#if>

            </ul>
        </nav>
    </#if>

</@c.page>



