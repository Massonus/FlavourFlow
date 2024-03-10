<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Company editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editCompany(event, ${company.getId()})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="companyTitle" class="col-sm-4 col-form-label">Title</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="companyTitle" name="companyTitle"
                                       value="${company.getTitle()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="priceCategory">Price Category:</label>
                            <select class="form-select" id="priceCategory" required>
                                <option <#if company.getPriceCategory() == "LOW">selected</#if> value="LOW">LOW</option>
                                <option <#if company.getPriceCategory() == "MEDIUM">selected</#if> value="MEDIUM">MEDIUM</option>
                                <option <#if company.getPriceCategory() == "HIGH">selected</#if> value="HIGH">HIGH</option>
                                <option <#if company.getPriceCategory() == "PREMIUM">selected</#if> value="PREMIUM">PREMIUM</option>
                            </select>
                        </div>

                        <div class="mb-3 row">
                            <label for="companyCountry">Company Country:</label>
                            <select class="form-select" id="companyCountry" required>
                                <#list countries as country>
                                    <option <#if company.getCompanyCountry().getId() == country.getId()>selected</#if>value=${country.getId()}>${country.getTitle()}</option>
                                </#list>
                            </select>
                        </div>

                        <br><br>

                        <div class="mb-3 row">
                            <label for="kitchenCategory">Kitchen Category:</label>
                            <select class="form-select" id="kitchenCategory" required>
                                <#list categories as category>
                                    <option <#if company.getKitchenCategory().getId() == category.getId()>selected</#if>
                                            value=${category.getId()}>${category.getTitle()}</option>
                                </#list>
                            </select>
                        </div>

                        <h5>Choose a file or enter link</h5>
                        <label for="companyFileUpload">File:</label>
                        <input type="file" id="companyFileUpload" name="companyFileUpload"
                               onchange="checkFile('companyFileUpload')">

                        <br><br>

                        <label for="companyImageLink"></label>
                        <input placeholder="image link" type="text"
                               name="companyImageLink" id="companyImageLink">
                        <br><br>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/admin/panel">Cancel</a>

                        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>