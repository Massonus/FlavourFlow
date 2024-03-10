<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Product editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editProduct(event, ${product.getId()}, ${product.getCompany().getId()})"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="productTitle" class="col-sm-4 col-form-label">Title</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="productTitle" name="productTitle"
                                       value="${product.getTitle()}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="productPrice" class="col-sm-4 col-form-label">Price</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="productPrice" name="productPrice"
                                       value="${product.getPrice()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productCategory">Product Category:</label>
                            <select class="form-select" id="productCategory">
                                <option <#if product.getProductCategory() == "MEAL">selected</#if> value="MEAL">MEAL
                                </option>
                                <option <#if product.getProductCategory() == "DRINK">selected</#if> value="DRINK">
                                    DRINK
                                </option>
                            </select>
                        </div>

                        <br><br>

                        <span id="imageError" class="error"></span>
                        <div class="mb-3 row">
                            <h5>Choose a file or enter link</h5>
                            <label for="productFileUpload">File:</label>
                            <input type="file" id="productFileUpload" name="productFileUpload"
                                   onchange="checkFile('productFileUpload')">
                            <br><br>

                            <label for="productImageLink"></label>
                            <input placeholder="image link" type="text"
                                   name="productImageLink" id="productImageLink">
                            <br><br>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/product/admin/all-products/${product.getCompany().getId()}">Cancel</a>
                        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>