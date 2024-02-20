<#import "../parts/common.ftlh" as c>

<@c.page>

<h2>Add Company</h2>
<form enctype="multipart/form-data" method="post" action="/companies/new-company">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label>Company Type:
        <input type="radio" name="companyType" value="RESTAURANT" checked>RESTAURANT
        <input type="radio" name="companyType" value="CLUB">CLUB
        <input type="radio" name="companyType" value="CAFFE">CAFFE
    </label><br><br>

    <div class="choose-category">
        <label>Category:
            <#list categories as category>
            <input type="radio" name="categoryId" value=${category.getId()}> ${category.getTitle()}
        </label>
        </#list>
    </div>

    <label for="file">File:</label>
    <input type="file" name="file">

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Send">
</form>
<br><br>

</@c.page>