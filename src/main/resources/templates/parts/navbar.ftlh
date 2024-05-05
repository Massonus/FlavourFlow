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
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                        </svg></a>
                    <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdown">
                        <#if known>
                            <li><a class="dropdown-item" href="/user/profile"> Profile (${name})</a></li>
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
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-basket2-fill" viewBox="0 0 16 16">
                                        <path d="M5.929 1.757a.5.5 0 1 0-.858-.514L2.217 6H.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h.623l1.844 6.456A.75.75 0 0 0 3.69 15h8.622a.75.75 0 0 0 .722-.544L14.877 8h.623a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1.717L10.93 1.243a.5.5 0 1 0-.858.514L12.617 6H3.383zM4 10a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0zm3 0a1 1 0 0 1 2 0v2a1 1 0 1 1-2 0zm4-1a1 1 0 0 1 1 1v2a1 1 0 1 1-2 0v-2a1 1 0 0 1 1-1"/>
                                    </svg>
                                    <span id="basket-count">0</span>
                                </div>
                            </a>
                        </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/wishes">
                            <svg id="wish-icon" xmlns="http://www.w3.org/2000/svg" width="25" height="27" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                                <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                            </svg>
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