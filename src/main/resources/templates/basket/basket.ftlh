<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>

    <div class="container">
        <h2 class="h5 text-uppercase mb-4">Shopping cart</h2>
        <div class="row">
            <div class="col-lg-8 mb-4 mb-lg-0">
                <!--BASKET OBJECTS TABLE-->
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
                                        class="text-sm text-uppercase">Amount</strong></th>
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
                                        <span id="amount-input"
                                              class="small text-uppercase text-gray headings-font-family"><input
                                                    class="form-control"
                                                    id="newAmount-${product.getId()}"
                                                    type="number"
                                                    onchange="changeAmount(${product.getId()})"
                                                    value="${product.getAmount()}"></span>

                                    </div>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <p class="mb-0 small">${product.getSum() + '$'}</p>
                                </td>
                                <td class="p-3 align-middle border-light">
                                    <div class="d-flex align-items-stretch">
                                        <a role="button" class="btn black-btn-outline"
                                           href="basket/delete-from-basket/${product.getProductId()}">
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
                <div id="basket-card" class="card border-0 rounded-0 p-lg-4 bg-light">
                    <div class="card-body">
                        <h5 class="text-uppercase mb-4">Cart total</h5>
                        <ul class="list-unstyled mb-0">
                            <li class="d-flex align-items-center justify-content-between mb-4"><strong
                                        class="text-uppercase small font-weight-bold">Total</strong>
                                <span>${basket.getTotal() + '$'}</span></li>

                            <li><a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                                   href="/companies">Continue shopping</a></li>

                            <#if products?has_content>
                                <li>
                                    <a role="button" class="btn black-btn btn-gradient mt-2 w-100"
                                       onclick="checkOrder()">Checkout</a></li>
                                <li>
                                    <button onclick="clearBasket()" class="btn btn-outline-danger w-100 mt-2">Clear the
                                        basket
                                    </button>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="${orderModal}" id="order-form-modal">
        <div class="modal__box">

            <button onclick="closeOrderForm()" class="modal__close-btn" id="close-my-modal-btn">
                <i class="fa-solid fa-xmark"></i>
            </button>


            <form id="order-checkout" onsubmit="createOrder(event, ${companyId!''})">
                <input id="orderName" name="name" type="text" placeholder="Your name">
                <input id="orderPhone" name="phone" type="text" placeholder="Mobile phone">
                <input type="date" name="date" id="date">

                <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Отправить">
            </form>


        </div>
    </div>

    <div class="${modal}" id="order-alert">
        <div class="modal__box">

            <button onclick="closeOrderAlertForm()" class="modal__close-btn" id="close-my-modal-btn">
                <i class="fa-solid fa-xmark"></i>
            </button>

            <#if companies?has_content>
                <p>You have ${size!''} different companies in your basket, please choose only one for checkout</p>
                <div class="container-buttons">
                    <#list companies as company>
                        <button type="submit" onclick="afterAlert(${company.getId()})">${company.getTitle()}</button>
                    </#list>
                </div>
            </#if>
        </div>
    </div>

</@c.page>

