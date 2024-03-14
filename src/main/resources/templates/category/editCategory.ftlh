<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Add Category</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editKitchenCategory(event, #{category.getId()})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="categoryTitle" class="col-sm-4 col-form-label">Title:</label>
                            <div class="col-sm-8">
                                <input id="categoryTitle" type="text" class="form-control" placeholder="category title" value="${category.getTitle()}" required>
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

</@c.page>