<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Add Product</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="createProduct(event, ${companyId})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="productTitle" class="col-sm-4 col-form-label">Title:</label>
                            <div class="col-sm-8">
                                <input id="productTitle" type="text" class="form-control" placeholder="product title" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productPrice" class="col-sm-4 col-form-label">Price:</label>
                            <div class="col-sm-8">
                                <input id="productPrice" type="number" step="0.01" min="1" class="form-control" placeholder="product price" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productDescription" class="col-sm-4 col-form-label">Input product description:</label>
                            <div class="col-sm-8">
                                <input id="productDescription" type="text" class="form-control"
                                       placeholder="description" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productComposition" class="col-sm-4 col-form-label">Input product composition:</label>
                            <div class="col-sm-8">
                                <input id="productComposition" type="text" class="form-control"
                                       placeholder="composition" required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productImageLink" class="col-sm-4 col-form-label">Choose a file or enter link:</label>
                            <div class="col-sm-8">
                                <input id="productImageLink" type="text" class="form-control"
                                       placeholder="image link" oninput="closeAlertWindow()">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productFileUpload" class="col-sm-4 col-form-label">File:</label>
                            <div class="col-sm-8">

                                <div id="productImageAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="productImageError"></strong>
                                </div>

                                <div class="input__wrapper">
                                    <input id="productFileUpload" type="file" class="input input__file" onchange="checkFile('productFileUpload'); closeAlertWindow()">
                                    <label for="productFileUpload" class="input__file-button">
                                        <span class="input__file-icon-wrapper"><img class="input__file-icon" src="../../static/img/add.svg" alt="Choose your file" width="25"></span>
                                        <span class="input__file-button-text">Choose your file</span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="productCategory" class="col-sm-4 col-form-label">Category:</label>
                            <div class="col-sm-8">
                                <select class="form-select" id="productCategory" required>
                                    <option selected value="MEAL">MEAL</option>
                                    <option value="DRINK">DRINK</option>
                                </select>
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/product/admin/all-products?companyId=${companyId}">Cancel</a>
                        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

    <#include "../token.ftlh">

</@c.page>