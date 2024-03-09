<#import "../parts/common.ftlh" as c>

<@c.page>

    <h2>Add Company</h2>
    <form enctype="multipart/form-data" method="post" action="/companies/new-company">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>

        <div class="mb-3 row">
            <label>Price Category:
                <input type="radio" name="priceCategory" value="LOW" checked>LOW
                <input type="radio" name="priceCategory" value="MEDIUM">MEDIUM
                <input type="radio" name="priceCategory" value="HIGH">HIGH
                <input type="radio" name="priceCategory" value="PREMIUM">PREMIUM
            </label><br><br>
        </div>

        <div class="choose-type">
            <label>Type:
                <#list types as type>
                <input type="radio" name="typeId" checked value=${type.getId()}> ${type.getTitle()}
            </label>
            </#list>
        </div>

        <br><br>

        <div class="choose-category">
            <label>Category:
                <#list categories as category>
                <input type="radio" name="categoryId" checked value=${category.getId()}> ${category.getTitle()}
            </label>
            </#list>
        </div>

        <label for="file">File:</label>
        <input type="file" name="file">

        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Send">
    </form>
    <br><br>

</@c.page>