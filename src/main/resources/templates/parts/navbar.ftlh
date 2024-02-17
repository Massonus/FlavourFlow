<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">RCC Navigator</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="/">Home <span class="sr-only">(current)</span></a>
            <#if known>
                <a class="nav-item nav-link" href="/companies">Companies</a>
                <a class="nav-item nav-link" href="/basket">Basket</a>
                <a class="nav-item nav-link" href="/logout">logout</a>
                <a class="nav-item nav-link" href="/add-user-from-admin">Admin-panel</a>
                <a class="nav-item nav-link" href="/user/profile">${name}</a>
            <#else>
                <a class="nav-item nav-link" href="/registration">registration</a>
                <a class="nav-item nav-link" href="/login">login</a>
            </#if>
        </div>
    </div>
</nav>