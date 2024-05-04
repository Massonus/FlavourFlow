<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="order">
        <h1>Your orders</h1>
        <br>
        <#if orders?has_content>
            <div class="container d-flex flex-column justify-content-center align-items-center">
                <#list orders as order>
                    <div class="card w-50 mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${order.getDate()}</h5>
                            <h5 class="card-title">${order.getTime()}</h5>
                            <h5 class="card-title">${order.getAddress()}</h5>
                            <h5 class="card-title">${order.getCompany().getTitle()}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">Products:</h6>
                            <ul>
                                <#list order.getOrderObjects() as product>
                                    <li>${product.getTitle()} ${product.getAmount()} (${product.getSum() + '$'})</li>
                                </#list>
                            </ul>
                            <h6>Total Price: ${order.getTotal() + '$'}&nbsp;</h6>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <h2>Empty yet</h2>
            <div class="empty"></div>
        </#if>
    </div>

</@c.page>