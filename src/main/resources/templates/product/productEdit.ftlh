<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Product editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form enctype="multipart/form-data" action="/product/edit/${product.getId()}" method="post"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="inputTitle" class="col-sm-4 col-form-label">Title</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputTitle" name="title"
                                       value="${product.getTitle()}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputPrice" class="col-sm-4 col-form-label">Price</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputPrice" name="price"
                                       value="${product.getPrice()}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="file">File:</label>
                            <input type="file" name="file">
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/product/${product.getCompany().getId()}">Cancel</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>