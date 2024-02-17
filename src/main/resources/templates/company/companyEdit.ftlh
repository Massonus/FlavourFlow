<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <body>
    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Company editing</h2>
                <div style="max-width: 600px" class="w-100">
                    <form action="/companies/edit/${company.getId()}" method="post"
                          class="text-start py-4">
                        <div class="mb-3 row">
                            <label for="inputTitle" class="col-sm-4 col-form-label">Title</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputTitle" name="title"
                                       value="${company.getTitle()}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-8">Company Type:
                                <input type="radio" name="companyType" value="RESTAURANT" checked>RESTAURANT
                                <input type="radio" name="companyType" value="CLUB">CLUB
                                <input type="radio" name="companyType" value="CAFFE">CAFFE
                            </label>
                        </div>

                        <div class="mb-3 row">
                            <label class="col-sm-8">Kitchen Type:
                                <input type="radio" name="kitchenType" value="ITALIAN" checked>ITALIAN
                                <input type="radio" name="kitchenType" value="UKRAINIAN">UKRAINIAN
                                <input type="radio" name="kitchenType" value="CHINESE">CHINESE
                                <input type="radio" name="kitchenType" value="AMERICAN">AMERICAN
                            </label>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/companies">Cancel</a>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>
    </body>

</@c.page>