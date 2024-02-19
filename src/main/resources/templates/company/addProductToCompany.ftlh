<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <#if isAdmin>
        <h2>Add Product</h2>
        <form enctype="multipart/form-data" method="post" action="/product/new-product/${id}">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required><br><br>
f
            <label for="file">File:</label>
            <input type="file" name="file">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Send">
        </form>
    </#if>

</@c.page>