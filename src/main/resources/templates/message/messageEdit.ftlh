<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="form-group mt-3">
        <form method="post" action="/message/edit/${id}">
            <div class="form-group">
                <input type="text"
                       value="<#if message??>${message.getText()}</#if>" name="text" placeholder="Enter new text"/>
            </div>

            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Save message</button>
            </div>
        </form>
    </div>

</@c.page>