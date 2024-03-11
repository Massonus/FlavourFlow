<#import "../parts/common.ftlh" as c>

<@c.page>

<form onsubmit="editMessage(event, ${message.getId()}, '${message.getMessageItemType()}', ${itemId}, '${_csrf.token}')">
    <div>
        <label for="comment">Title</label>
        <input type="text" id="comment" name="comment" value="${message.getText()}">
    </div>
    <br>
    <div>
        <a role="button" class="btn btn-outline-secondary mt-3" href="/company/info/${itemId}">Cancel</a>
        <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
    </div>
</form>

</@c.page>