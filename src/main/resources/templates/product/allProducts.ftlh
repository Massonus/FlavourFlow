<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

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
                            <#if isAdmin>

                                <a role="button" class="btn black-btn-outline"
                                   href="/products/edit/${product.getId()}">Edit</a>

                                <a role="button" class="btn black-btn-outline"
                                   href="/products/delete/${product.getId()}">Delete</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <br>

    <#if isAdmin>
        <h2>Add Product</h2>
        <form enctype="multipart/form-data" method="post" action="/products/new-product/${id}">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required><br><br>

            <label for="file">File:</label>
            <input type="file" name="file">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Send">
        </form>
    </#if>

</@c.page>



