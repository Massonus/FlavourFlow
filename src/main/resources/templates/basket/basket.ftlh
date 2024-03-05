<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

<#--
    <#list products as product>
        <div class="col-12 col-md-8 col-lg-9">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4">
                <div class="card border-none">
                    <#if (product.getImage()??)>
                        <img alt="product image" src="/images/${product.getImage().getId()}.jpg" class="card-img-top"/>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${product.getTitle()}</h5>
                        <p class="text-muted">${product.getPrice() + '$'}</p>
                        <div class="d-flex justify-content-between">

                            <a role="button" class="btn black-btn-outline"
                               href="basket/delete-from-basket/${product.getId()}">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>


    <div class="col-lg-4">

        <a role="button" class="btn btn-outline-danger w-100 mt-2"
           href="/basket/clear">
            Clear the basket
        </a>
    </div>-->

    <div class="container">
        <h2 class="h5 text-uppercase mb-4">Shopping cart</h2>
        <div class="row">
            <div class="col-lg-8 mb-4 mb-lg-0">
                <!-- CART TABLE-->
                <div class="table-responsive mb-4">
                    <table class="table text-nowrap">
                        <thead class="bg-light">
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
                            <th class="border-0 p-3" scope="col"><strong
                                        class="text-sm text-uppercase">Quantity</strong></th>
                            <th class="border-0 p-3" scope="col"><strong
                                        class="text-sm text-uppercase">Total</strong>
                            </th>
                            <th class="border-0 p-3" scope="col"><strong class="text-sm text-uppercase"></strong>
                            </th>
                        </tr>
                        </thead>
                        <#list products as product>
                            <tbody class="border-0">
                            <tr>
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
                                    <div class="d-flex align-items-center justify-content-between px-3">
                                        <span class="small text-uppercase text-gray headings-font-family"><input
                                                    class="form-control"
                                                    id="newAmount"
                                                    name="newAmount"
                                                    type="number"
                                                    onchange="changeAmount(event, ${product.getId()})"
                                                    value="${product.getAmount()}" step="1"
                                                    min="1" required></span>

                                    </div>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <p class="mb-0 small">${product.getSum()}</p>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <div class="d-flex align-items-stretch">
                                        <#--<a role="button" class="btn btn-outline-danger"
                                           th:href="@{/basket/removeOneUnitOfProduct/{id}(id=${detail.getProductId()})}">
                                            Reduce quantity by one
                                        </a>-->
                                        <a role="button" class="btn black-btn-outline"
                                           href="basket/delete-from-basket/${product.getId()}">
                                            <i class="bi bi-trash text-danger"></i>
                                        </a>
                                    </div>
                                </td>
                            </tbody>
                        </#list>
                    </table>
                </div>
            </div>
            <!-- ORDER TOTAL-->
            <div class="col-lg-4">
                <div class="card border-0 rounded-0 p-lg-4 bg-light">
                    <div class="card-body">
                        <h5 class="text-uppercase mb-4">Cart total</h5>
                        <ul class="list-unstyled mb-0">
                            <li class="d-flex align-items-center justify-content-between mb-4"><strong
                                        class="text-uppercase small font-weight-bold">Total</strong>
                                <#-- <span th:text="${basket.sum + '$'}">250.0</span>--></li>
                            <#--<a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                               th:href="@{/products}">Continue shopping</a>
                            <button class="btn black-btn-outline w-100 mt-2"
                                    data-bs-toggle="modal" data-bs-target="#motiv">Get motivation
                            </button>-->
                            <a role="button" class="btn btn-outline-danger w-100 mt-2"
                               href="/basket/clear">
                                Clear the basket
                            </a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>

