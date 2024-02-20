<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>



    <div class="flex-grow-1">
        <form class="d-flex" role="search" action="/search" method="get">
            <input class="form-control me-2 flex-grow-1" type="search" name="title" placeholder="Search"
                   aria-label="Search">

            <select class="form-control me-2 flex-grow-1" id="searchType" name="searchType" required>
                <option value="company">Company</option>
                <option value="product">Product</option>
            </select>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>

</@c.page>