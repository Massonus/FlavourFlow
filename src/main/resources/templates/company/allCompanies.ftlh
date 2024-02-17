<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <#list companies as company>
        <div class="col-12 col-md-8 col-lg-9">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4">
                <div class="card border-none">
                    <#if (company.getImage()??)>
                        <img alt="product image" src="/images/${company.getImage().getId()}.jpg" class="card-img-top"/>
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${company.getTitle()}</h5>
                        <p class="text-muted">${company.getKitchenType()}</p>
                        <p class="text-muted">${company.getCompanyType()}</p>
                        <a role="button" class="btn black-btn-outline"
                           href="/products/${company.getId()}">Go to products of this company</a>
                            <#--<#if isAdmin>

                                <a role="button" class="btn black-btn-outline"
                                   href="/products/edit/${company.getId()}">Edit</a>

                                <a role="button" class="btn black-btn-outline"
                                   href="/products/delete/${company.getId()}">Delete</a>
                            </#if>-->
                        </div>
                    </div>
                </div>
            </div>
    </#list>
    <br>

    <#if isAdmin>
        <h2>Add Company</h2>
        <form enctype="multipart/form-data" method="post" action="/companies/new-company">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label>Company Type:
                <input type="radio" name="companyType" value="RESTAURANT" checked>RESTAURANT
                <input type="radio" name="companyType" value="CLUB">CLUB
                <input type="radio" name="companyType" value="CAFFE">CAFFE
            </label><br><br>

            <label>Kitchen Type:
                <input type="radio" name="kitchenType" value="ITALIAN" checked>ITALIAN
                <input type="radio" name="kitchenType" value="UKRAINIAN">UKRAINIAN
                <input type="radio" name="kitchenType" value="CHINESE">CHINESE
                <input type="radio" name="kitchenType" value="AMERICAN">AMERICAN
            </label><br><br>

            <label for="file">File:</label>
            <input type="file" name="file">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Send">
        </form>
    </#if>


</@c.page>