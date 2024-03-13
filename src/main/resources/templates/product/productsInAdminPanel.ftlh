<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <table id="product-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Product id</th>
                    <th scope="col">Title</th>
                    <th scope="col">Price</th>
                </tr>
                </thead>
                <tbody>
                <#list products as product>
                    <tr id="product-table-${product.getId()}">
                        <th class="p-3" scope="row">${product.getId()}</th>
                        <td class="p-3">${product.getTitle()}</td>
                        <td class="p-3">${product.getPrice()}</td>
                        <td class="p-3"><a role="button" class="btn black-btn-outline"
                                           href="/product/edit/${product.getId()}">Edit</a>
                        </td>
                        <td class="p-3 align-middle">
                            <a role="button" class="btn btn-outline-danger"
                               onclick="deleteProduct('${product.getId()}', '${_csrf.token}')">
                                <i class="bi bi-trash text-danger"></i>
                            </a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div>
                <a role="button" class="btn black-btn btn-gradient mt-2 w-100" href="/product/add/${companyId}">Add product</a>
            </div>
        </div>
    </section>

</@c.page>