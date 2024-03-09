<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <#if isAdmin>
        <h2>Add Product</h2>
        <form onsubmit="createProduct(event, ${companyId})">
            <label for="productTitle">Title:</label>
            <input type="text" id="productTitle" name="productTitle" required><br><br>

            <label for="productPrice">Price:</label>
            <input type="number" step="0.01" min="0" id="productPrice" name="productPrice" required><br><br>

            <#--<h5>Choose a file or enter link</h5>
            <label for="fileUpload">File:</label>
            <input type="file" name="fileUpload" onchange="checkFile()">
            <br><br>-->

            <div class="mb-3 row">
                <label for="productCategory">Product Category:</label>
                <select class="form-select" id="productCategory">
                    <option selected value="MEAL">MEAL</option>
                    <option value="DRINK">DRINK</option>
                </select>
            </div>

            <br><br>

            <label for="productImageLink"></label>
            <input placeholder="image link" type="text"
                   name="productImageLink" id="productImageLink">
            <br><br>

            <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Send">
        </form>
    </#if>

</@c.page>