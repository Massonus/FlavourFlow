<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <div class="container">
        <h2 class="h5 text-uppercase mb-4">Wishlist</h2>
        <div class="row">
            <div class="col-lg-8 mb-4 mb-lg-0">
                <!--BASKET OBJECTS TABLE-->
                <div class="table-responsive mb-4">
                    <table class="table text-nowrap">
                        <thead class="thead bg-light">
                        <tr>
                            <th class="border-0 p-3" scope="col"><strong
                                        class="text-sm text-uppercase">Product</strong>
                            </th>
                            <th class="border-0 p-3" scope="col"><strong
                                        class="text-sm text-uppercase">Company</strong>
                            </th>
                            <th class="border-0 p-3" scope="col"><strong
                                        class="text-sm text-uppercase">Price</strong>
                            </th>
                            <th class="border-0 p-3" scope="col"><strong class="text-sm text-uppercase"></strong>
                            </th>
                        </tr>
                        </thead>
                        <#list products as product>
                            <tbody class="tbody border-0">
                            <tr id="wish-item-${product.getId()}">
                                <th class="ps-0 py-3 border-light" scope="row">
                                    <div class="d-flex align-items-center">
                                        <a class="d-block">
                                            <#if (product.getImage()??)>

                                                <a href="/product/${product.getId()}"><img
                                                            style="object-fit: cover; height: 80px; width: 80px;"
                                                            alt="product image"
                                                            src="/images/${product.getImage().getId()}.jpg"
                                                            class="card-img-top"/></a>

                                            <#else>

                                                <a href="/product/${product.getId()}"><img
                                                            style="object-fit: cover; height: 80px; width: 80px;"
                                                            alt="product image"
                                                            src="${product.getImageLink()}"
                                                            class="card-img-top"></a>

                                            </#if>
                                        </a>
                                        <div class="ms-3">
                                            <span class="fw-medium">${product.getTitle()}</span>
                                        </div>
                                    </div>
                                </th>
                                <td class="p-3 align-middle border-light">
                                    <p class="mb-0 small">${product.getCompany().getTitle()}</p>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <p class="mb-0 small">${product.getPrice() + '$'}</p>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <div class="d-flex align-items-stretch">

                                        <a role="button" class="btn btn-outline-secondary mx-1"
                                           onclick="moveWishToBasket(${product.getProductId()}, '${_csrf.token}')">To
                                            Basket</a>

                                        <a role="button" class="btn btn-outline-danger"
                                           onclick="deleteWishItem(${product.getProductId()}, '${_csrf.token}', undefined)">
                                            <i class="bi bi-trash text-danger"></i>
                                        </a>
                                    </div>
                                </td>
                            </tbody>
                        </#list>
                    </table>
                </div>
            </div>
            <!-- TOTAL-->
            <div class="col-lg-4">
                <div id="wish-card" class="card border-0 rounded-0 p-lg-4 bg-light">
                    <div class="card-body">
                        <h5 class="text-uppercase mb-4">Navigation</h5>
                        <ul class="list-unstyled mb-0">

                            <li><a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                                   href="/company">Continue shopping</a></li>

                            <li><a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                                   href="/basket">Go to basket</a></li>

                            <#if products?has_content>
                                <li>
                                    <button onclick="clearWishes('${_csrf.token}')" class="btn btn-outline-danger w-100 mt-2">Clear the
                                        wishes
                                    </button>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="my-modal">
        <div class="modal__box">

            <button onclick="closeWishAlertWindow()" class="modal__close-btn" id="close-my-modal-btn">
                <i class="fa-solid fa-xmark"></i>
            </button>
            <h2>This product is already in basket</h2>

        </div>
    </div>

</@c.page>

