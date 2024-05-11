<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-sm-6 mb-3">
                <div style="text-align: center;">
                    <a href="/product/all-products?companyId=${product.getCompany().getId()}"><img
                                alt="product image"
                                src="${product.getImageLink()}"
                                style="width: 40%"></a>
                    <h2><a href="/product/all-products?companyId=${product.getCompany().getId()}">${product.getTitle()}</a></h2>
                </div>
            </div>
            <div class="col-lg-6 col-sm-6 mb-3">
                <h2 class="rating__caption">Composition: ${product.getComposition()}</h2>
                <h2 class="rating__caption">Kitchen: ${product.getCompany().getKitchenCategory().getTitle()}</h2>
                <h2 class="rating__caption">Country: ${product.getCompany().getCompanyCountry().getTitle()}</h2>
                <h2 class="rating__caption">Price: ${product.getPrice() + '$'}</h2>
                <h2 class="rating__caption">Company: ${product.getCompany().getTitle()}</h2>
            </div>

        </div>
    </div>
</@c.page>