<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Product id</th>
                    <th scope="col">Title</th>
                    <th scope="col">Price</th>
                </tr>
                </thead>
                <tbody>
                <#list products as product>
                    <tr>
                        <th class="p-3" scope="row">${product.getId()}</th>
                        <td class="p-3">${product.getTitle()}</td>
                        <td class="p-3">${product.getPrice()}</td>
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/product/edit/${product.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <a role="button" class="btn btn-outline-danger"
                               href="/product/delete/${product.getId()}">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn-outline" href="/product/add-product/${id}">Add product</a>
            </div>
        </div>
    </section>

</@c.page>