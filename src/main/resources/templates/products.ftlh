<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>

</@c.page>

    <table border="1px">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Image</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.getId()}</td>
                <td>${product.getTitle()}</td>
                <td><img alt="image" src="/images/${product.getImage().getId()}.jpg"/></td>
            </tr>
        </#list>
        </tbody>
    </table>

<#if isAdmin>
    <h2>Add Product</h2>
    <form enctype="multipart/form-data" method="post" action="/products/new-product">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>

        <label for="file">File:</label>
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Send">
    </form>

</#if>

