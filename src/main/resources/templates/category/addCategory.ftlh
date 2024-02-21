<#import "../parts/common.ftlh" as c>

<@c.page>

    <form id="addCategory" onsubmit="createKitchenCategory(event)">
        <div>
            <label for="title">Title</label>
            <input type="text" id="title" name="title">
        </div>
        <br>
        <div>
            <a role="button" class="btn btn-outline-secondary mt-3" href="/admin/panel">Cancel</a>
            <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
        </div>
    </form>

</@c.page>