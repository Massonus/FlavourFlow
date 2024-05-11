<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-sm-6 mb-3">
                    <div style="text-align: center;">
                        <a href="/product/all-products?companyId=${product.getCompany().getId()}"><img
                                    alt="product image"
                                    src="${product.getImageLink()}"
                                    style="width: 40%"></a>
                        <h2>
                            <a href="/product/all-products?companyId=${product.getCompany().getId()}">${product.getTitle()}</a>
                        </h2>
                    </div>
                </div>
                <div class="col-lg-6 col-sm-6 mb-3">
                    <h2 class="rating__caption">Composition: ${product.getComposition()}</h2>
                    <h2 class="rating__caption">Kitchen: ${product.getCompany().getKitchenCategory().getTitle()}</h2>
                    <h2 class="rating__caption">Country: ${product.getCompany().getCompanyCountry().getTitle()}</h2>
                    <h2 class="rating__caption">Price: ${product.getPrice() + '$'}</h2>
                    <h2 class="rating__caption">Company: ${product.getCompany().getTitle()}</h2>
                </div>

            </div>
        </div>

        <#if known>

        <div class="product-links" style="text-align: center">
            <svg id="success-${product.getId()}" class="checkmark"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                <circle class="checkmark__circle" cx="26" cy="26" r="25"
                        fill="none"/>
                <path class="checkmark__check" fill="none"
                      d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
            </svg>

            <a onclick="saveOrDeleteBasketItem('${product.getId()}', '${_csrf.token}', true)"><#if basketService.isInBasket("${product.getId()}", "${currentUserId}")>
                    <i id="cart-${product.getId()}"
                       class="bi bi-cart-fill fa-2x"></i>
                <#else>
                    <i id="cart-${product.getId()}" class="bi bi-cart fa-2x"></i>
                </#if></a>

            <a onclick="saveOrDeleteWishItem('${product.getId()}', '${_csrf.token}', true)"><#if wishService.isInWishes("${product.getId()}", "${currentUserId}")>
                    <i id="wish-${product.getId()}"
                       class="bi bi-heart-fill fa-2x"></i>

                <#else>
                    <i id="wish-${product.getId()}"
                       class="bi bi-heart fa-2x"></i>
                </#if></a>
            </#if>
        </div>
    </section>
</@c.page>