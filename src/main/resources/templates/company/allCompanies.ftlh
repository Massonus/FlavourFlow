<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>
    <section>
        <div class="container">
            <div class="row">
                <div class="row">
                    <div class="col-lg-4 col-sm-6 mb-3">
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="sort" class="form-label">Sort:</label>
                                <select onchange="filterCompany(event)" name="sort" class="form-select" id="sort">
                                    <option <#if sort == "Default">selected</#if> value="Default">Default</option>
                                    <option <#if sort == "priceDesc">selected</#if> class="filterOption" value="priceDesc">Sort by price: descending</option>
                                    <option <#if sort == "priceAsc">selected</#if> class="filterOption" value="priceAsc">Sort by price: ascending</option>
                                    <option <#if sort == "nameA">selected</#if> class="filterOption" value="nameA"> Sort by name: A-Z</option>
                                    <option <#if sort == "nameZ">selected</#if> class="filterOption" value="nameZ"> Sort by name: Z-A</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-sm-6 mb-3">
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="kitchenCategory" class="form-label">Kitchen category:</label>
                                <select onchange="filterCompany(event)" name="categoryId" class="form-select" id="kitchenCategory">
                                    <option selected value="">Default</option>
                                    <#list categories as category>
                                        <option <#if category.getId() = (categoryId)!-1>selected</#if> class="filterOption" value="${category.getId()}">${category.getTitle()}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-sm-6 mb-3">
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="companyCountry" class="form-label">Company country:</label>
                                <select onchange="filterCompany(event)" name="countryId" class="form-select"
                                        id="companyCountry">
                                    <option value="">Default</option>
                                    <#list countries as country>
                                        <option <#if country.getId() = (countryId)!-1>selected</#if> class="filterOption" value="${country.getId()}">${country.getTitle()}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <br><br><br><br>

                <#if companies?has_content>
                    <#list companies as company>
                        <div class="col-lg-4 col-sm-6 mb-3">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="product-card">
                                        <div class="product-thumb">

                                            <div class="filters__img">
                                                <a href="/product/all-products?companyId=${company.getId()}"><img
                                                            alt="product image"
                                                            src="${company.getImageLink()}"
                                                            class="card-img-top"></a>
                                            </div>

                                        </div>
                                        <div class="product-details">
                                            <h4>
                                                <a href="/product/all-products?companyId=${company.getId()}">${company.getTitle()}</a>
                                            </h4>
                                            <#if company.getRating()?has_content>
                                                <#assign rating = (company.getCurrentRating()?number)?int />
                                                <#list 1..5 as i>
                                                    <#if i <= rating>
                                                        <object id="star-fill"></object>
                                                    <#else>
                                                        <object id="star-empty"></object>
                                                    </#if>
                                                </#list>
                                            <#else>
                                                <#list 1..5 as i>
                                                    <object id="star-empty"></object>
                                                </#list>
                                            </#if>
                                            <span>(${company.getCountOfRates()})</span>

                                            <h5>${company.getCompanyCountry().getTitle()}</h5>
                                            <h5>${company.getKitchenCategory().getTitle()}</h5>
                                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Laudantium,
                                                repudiandae?</p>
                                            <div class="product-bottom-details d-flex justify-content-between">
                                                <div class="product-price">
                                                    Average: ${company.getAverageProductsPrice() + '$'}
                                                </div>
                                                <div class="product-links">
                                                    <a href="/company/info/${company.getId()}">Comments
                                                        (${company.getCountOfMessages()})</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <h2>No such companies</h2>
                </#if>
            </div>
        </div>
    </section>
    <br><br>
    <#if companies?has_content>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center fixed-bottom py-4">
                <#if currentPage?number gt 0>
                    <li class="page-item">
                        <a class="page-link"
                           href="/company?page=${currentPage - 1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}&search=${search!''}">Previous</a>
                    </li>
                </#if>
                <#list 1..totalPages as i>
                    <li class="page-item<#if i == currentPage + 1> active</#if>">
                        <a class="page-link"
                           href="company?page=${i-1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}&search=${search!''}">${i}</a>
                    </li>
                </#list>
                <#if currentPage < totalPages - 1>
                    <li class="page-item">
                        <a class="page-link"
                           href="/company?page=${currentPage + 1}&categoryId=${categoryId!''}&countryId=${countryId!''}&sort=${sort!''}&search=${search!''}">Next</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </#if>

</@c.page>