<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="flex-grow-1">
        <form class="d-flex" role="search" action="/product/search" method="get">
            <input class="form-control me-2 flex-grow-1" type="search" name="title" placeholder="Search"
                   aria-label="Search">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>

</@c.page>