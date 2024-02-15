<#import "parts/common.ftlh" as c>

<@c.page>

    <h1>Trees List</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <#list treePage as tree>
            <tr>
                <td>${tree.getId()}</td>
                <td>${tree.getName()}</td>
            </tr>
        </#list>
        </tbody>
    </table>

    <div>
        <#if currentPage?number gt 0>
            <a href="/tree-page?page=${currentPage - 1}">Previous</a>
        </#if>

        Page ${currentPage + 1} of ${totalPages}

        <#if currentPage < totalPages - 1>
            <a href="/tree-page?page=${currentPage + 1}">Next</a>
        </#if>
    </div>

</@c.page>