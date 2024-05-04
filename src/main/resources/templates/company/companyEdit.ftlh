<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Company edit</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editCompany(event, ${company.getId()})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="companyTitle" class="col-sm-4 col-form-label">Title:</label>
                            <div class="col-sm-8">
                                <input id="companyTitle" type="text" class="form-control" placeholder="company title" value="${company.getTitle()}" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="companyCountry" class="col-sm-4 col-form-label">Company Country:</label>
                            <div class="col-sm-8">
                                <select class="form-select" id="companyCountry" required>
                                    <#list countries as country>
                                        <option <#if company.getCompanyCountry().getId() == country.getId()>selected</#if>
                                                value=${country.getId()}>${country.getTitle()}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="kitchenCategory" class="col-sm-4 col-form-label">Kitchen Category:</label>
                            <div class="col-sm-8">
                                <select class="form-select" id="kitchenCategory" required>
                                    <#list categories as category>
                                        <option <#if company.getKitchenCategory().getId() == category.getId()>selected</#if>
                                                value=${category.getId()}>${category.getTitle()}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="companyDescription" class="col-sm-4 col-form-label">Company description:</label>
                            <div class="col-sm-8">
                                <input id="companyDescription" type="text" class="form-control"
                                       placeholder="description" value="${company.getDescription()}" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="companyImageLink" class="col-sm-4 col-form-label">Choose a file or enter link:</label>
                            <div class="col-sm-8">
                                <input id="companyImageLink" type="text" class="form-control"
                                       placeholder="image link">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="companyFileUpload" class="col-sm-4 col-form-label">File:</label>
                            <div class="col-sm-8">
                                <div class="input__wrapper">
                                    <input id="companyFileUpload" type="file" class="input input__file" onchange="checkFile('companyFileUpload')">
                                    <label for="companyFileUpload" class="input__file-button">
                                        <span class="input__file-icon-wrapper"><img class="input__file-icon" src="../../static/img/add.svg" alt="Choose your file" width="25"></span>
                                        <span class="input__file-button-text">Choose your file</span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/admin/panel">Cancel</a>
                        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

    <#include "../token.ftlh">

</@c.page>