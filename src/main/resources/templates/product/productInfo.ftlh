<#import "../parts/common.ftlh" as c>

<@c.page>

    <div style="text-align: center">
        <a href="/product/all-products?companyId=${product.getCompany().getId()}"><img
                alt="product image"
                src="${product.getImageLink()}"
                style="width: 25%"></a>
    </div>
    <h2><a href="/product/all-products?companyId=${product.getCompany().getId()}">${product.getTitle()}</a></h2>
    <legend class="rating__caption">Composition: ${product.getComposition()}</legend>
    <legend class="rating__caption">Kitchen: ${product.getCompany().getKitchenCategory().getTitle()}</legend>
    <legend class="rating__caption">Country: ${product.getCompany().getCompanyCountry().getTitle()}</legend>
    <legend class="rating__caption">Price: ${product.getPrice() + '$'}</legend>
    <legend class="rating__caption">Company: ${product.getCompany().getTitle()}</legend>

    <br><br><br><br>
</@c.page>