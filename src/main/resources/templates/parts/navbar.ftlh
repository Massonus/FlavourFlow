<#include "security.ftlh">

<nav id="navbar" class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">RCC Navigator</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 top-menu">
                <#if known>
                    <li class="nav-item">
                        <a class="nav-item nav-link" href="/companies">CATALOG</a>
                    </li>
                    <#if isAdmin>
                        <li class="nav-item">
                            <a class="nav-item nav-link" href="/admin/panel">ADMIN-PANEL</a>
                        </li>
                    </#if>
                </#if>
            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <div id="search" class="search">
                        <input id="search-input" type="search" placeholder="Type to search" class="search-input-light">
                        <button id="search-button" class="search-button-light">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                                class="far fa-user"></i></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <#if known>
                            <li><a class="nav-item nav-link" href="/user/profile">${name}</a></li>
                            <a class="nav-item nav-link" href="/logout">logout</a>
                        <#else>
                            <li><a class="nav-item nav-link" href="/registration">registration</a></li>
                            <li><a class="nav-item nav-link" href="/login">login</a></li>
                        </#if>
                    </ul>
                </li>
                <#if known>
                    <li class="nav-item">
                        <a class="nav-link" href="/basket">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/wishes">
                            <i class="fa-regular fa-heart"></i>
                        </a>
                    </li>
                </#if>

                <li class="nav-item">
                    <input class="choose" type="checkbox" id="checker" onclick="changeStyle()">
                    <label class="chooseLabel" for="checker"></label>
                </li>

            </ul>
        </div>
    </div>
</nav>

<br><br>