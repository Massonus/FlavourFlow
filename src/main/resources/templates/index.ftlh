<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>

    <div>
        <#if known>
            Hello ${user.getEmail()}!
        <#else>
            Hello unknown!
        </#if>
    </div>

    <div></div>

    <table border="2px">
        <thead>
        <tr>
            <th>tree id</th>
            <th>name</th>
            <th>age</th>
            <th>is green</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <#list trees as tree>
            <tr>
                <td><a href="/trees/${tree.getId()}"><p>${tree.getId()}</a></td>
                <td>${tree.getName()}</td>
                <td>${tree.getAge()}</td>
                <#if tree.getIsGreen()>
                    <td>GREEN</td>
                <#else>
                    <td>NOT GREEN</td>
                </#if>
                <td><a href="/trees/delete/${tree.getId()}">DELETE</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>