<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>


    <#list products as product>
        <div class="col-12 col-md-8 col-lg-9">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4">
                <div class="card border-none">
                    <#if (product.getImage()??)>
                        <a href="/product/${product.getId()}"><img alt="product image"
                                                                   src="/images/${product.getImage().getId()}.jpg"
                                                                   class="card-img-top"/></a>
                        <#elseif product.getImageLink()??>
                            <img src="${product.getImageLink()}" class="card-img-top" alt="product image">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title"><a href="/product/${product.getId()}">${product.getTitle()}</a></h5>
                        <p class="text-muted">${product.getPrice() + '$'}</p>
                        <div class="d-flex justify-content-between">

                            <a href="/product/new-basket-item/${product.getId()}"
                               class="btn black-btn-outline">Add to basket</a>

                            <a class="nav-link" href="/product/new-wish-item/${product.getId()}"
                               data-toggle="tooltip" data-placement="left" title="Add this to your Wish List"><i
                                        class="bi bi-heart"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>


</@c.page>



