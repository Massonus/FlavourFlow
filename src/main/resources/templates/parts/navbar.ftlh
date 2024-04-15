<#include "security.ftlh">

<nav id="navbar" class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Flavour Flow</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 top-menu">

                <li class="nav-item">
                    <a class="nav-item nav-link" href="/company">CATALOG</a>
                </li>

                <#if known>
                    <li class="nav-item">
                        <a class="nav-item nav-link" href="/order">ORDERS</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-item nav-link" href="/admin/panel">ADMIN-PANEL</a>
                    </li>
                </#if>
            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <div id="search" class="search">
                        <form class="d-flex" onsubmit="search(event)">
                            <input id="search-input" type="text" placeholder="Type to search"
                                   class="search-input">

                            <button id="search-button" class="search-button">
                                <i class="fa fa-search fa-lg"></i>
                            </button>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                                class="far fa-user fa-lg"></i></a>
                    <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdown">
                        <#if known>
                            <li><a class="dropdown-item" href="/user/profile">${name}</a></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        <#else>
                            <li><a class="dropdown-item" href="/login">Login</a></li>
                            <li><a class="dropdown-item" href="/reg">Registration</a></li>
                        </#if>
                    </ul>
                </li>
                <#if known>
                        <li class="nav-item">
                            <a class="nav-link" href="/basket">
                                <div id="basket">
                                    <i class="fas fa-shopping-basket fa-lg"></i>
                                    <span id="basket-count">0</span>
                                </div>
                            </a>
                        </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/wishes">
                            <i class="fa-regular fa-heart fa-lg"></i>
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
<br>