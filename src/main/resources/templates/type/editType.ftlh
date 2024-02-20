<#import "../parts/common.ftlh" as c>

<@c.page>

    <form action="/type/edit-type/${type.getId()}" method="post">
        <div>
            <label for="title">Title</label>
            <input type="text" id="title" name="title" value="${type.getTitle()}">
        </div>
        <br>
        <div>
            <a role="button" class="btn btn-outline-secondary mt-3" href="/admin/panel">Cancel</a>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
        </div>
    </form>

</@c.page>