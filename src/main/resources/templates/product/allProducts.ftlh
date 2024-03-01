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
<#--
                    <form action="/companies/filter" method="get" class="me-2">
                        <div class="mb-3">
                            <label for="kitchenCategory" class="form-label">Kitchen category:</label>
                            <select name="categoryId" class="form-select" id="kitchenCategory">
                                <option selected value=""></option>
                                <#list companyCountry as category>
                                    <option value="${category.getId()}"> ${category.getTitle()}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="companyCountry" class="form-label">Company type:</label>
                            <select name="typeId" class="form-select" id="companyCountry">
                                <option value=""></option>
                                <#list types as type>
                                    <option value="${type.getId()}"> ${type.getTitle()}</option>
                                </#list>
                            </select>
                        </div>

                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>-->
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

                                                <a onclick="saveOrDeleteBasketItem('${product.getId()}', '${_csrf.token}')"><#if product.getIsInBasket()>
                                                        <i id="cart-${product.getId()}" class="bi bi-cart"></i>
                                                    <#else>
                                                    <i id="cart-${product.getId()}" class="bi bi-cart-fill"></i>
                                                    </#if></a>

									            <a onclick="saveOrDeleteWishItem('${product.getId()}', '${_csrf.token}')"><#if product.getIsinWishes()>
									            <i id="wish-${product.getId()}" class="far fa-heart"></i>
                                                    <#else>
                                                        <i id="wish-${product.getId()}" class="fa-solid fa-heart"></i>
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

</@c.page>



