<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>


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
</div>

</@c.page>

