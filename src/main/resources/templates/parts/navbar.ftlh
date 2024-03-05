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

                <li class="nav-item">
                    <a class="nav-item nav-link" href="/">HOME</a>
                </li>

                <li class="nav-item">
                    <a class="nav-item nav-link" href="/companies">CATALOG</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-item nav-link" href="/admin/panel">ADMIN-PANEL</a>
                    </li>
                </#if>
            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <div id="search" class="search">
                        <form class="d-flex" role="search" action="/companies" method="get">
                            <input id="search-input" name="search" type="search" placeholder="Type to search"
                                   class="search-input">

                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button id="search-button" class="search-button">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                                class="far fa-user"></i></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <#if known>
                            <li><a class="dropdown-item" href="/user/profile">${name}</a></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        <#else>
                            <li><a class="dropdown-item" href="/login">Login</a></li>
                            <li><a class="dropdown-item" href="/registration">Registration</a></li>
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
                    <input class="choose" type="checkbox" id="checker" onchange="changeStyle()">
                    <label class="chooseLabel" for="checker"></label>
                </li>

            </ul>
        </div>
    </div>
</nav>

<br><br>