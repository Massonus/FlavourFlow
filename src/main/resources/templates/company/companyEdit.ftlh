<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Company editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form enctype="multipart/form-data" action="/companies/edit/${company.getId()}" method="post"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="inputTitle" class="col-sm-4 col-form-label">Title</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputTitle" name="title"
                                       value="${company.getTitle()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label>Price Category:
                                <input type="radio" name="priceCategory"
                                       <#if company.getPriceCategory() == "LOW">checked</#if> value="LOW">LOW
                                <input type="radio" name="priceCategory"
                                       <#if company.getPriceCategory() == "MEDIUM">checked</#if> value="MEDIUM">MEDIUM
                                <input type="radio" name="priceCategory"
                                       <#if company.getPriceCategory() == "HIGH">checked</#if> value="HIGH">HIGH
                                <input type="radio" name="priceCategory"
                                       <#if company.getPriceCategory() == "PREMIUM">checked</#if> value="PREMIUM"
                                       value="PREMIUM">PREMIUM
                            </label><br><br>
                        </div>

                        <#list categories as category>
                        <div class="mb-3 row">
                            <label>Category:
                                <input type="radio" name="categoryId" <#if company.getKitchenCategory().getId() == category.getId()>checked</#if>
                                       value=${category.getId()}> ${category.getTitle()}
                            </label>
                            </#list>
                        </div>

                        <#list types as type>
                        <div class="mb-3 row">
                            <label>Category:
                                <input type="radio" name="typeId" <#if company.getCompanyCountry().getId() == type.getId()>checked</#if>value=${type.getId()}> ${type.getTitle()}
                            </label>
                            </#list>
                        </div>

                        <h5>Choose a file or enter link</h5>
                        <label for="file">File:</label>
                        <input type="file" name="file">
                        <br><br>

                        <label for="imageLink"></label>
                        <input placeholder="image link" type="text"
                               name="imageLink" id="imageLink">

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/admin/panel">Cancel</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>