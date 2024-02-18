<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <br>
    <div class="d-flex justify-content-center" style="width: 100%; display: flex; justify-content: center;">
        <form method="post" action="/message/new-product-message/${product.getId()}"
              style="display: flex; flex-direction: row; align-items: center; justify-content: center; width: 70%;">
            <label for="comment" class="col-sm-4 col-form-label">Comment</label>
            <input type="text" class="form-control me-2 flex-grow-1" placeholder="Write your comment" name="comment"
                   id="comment" style="height: 68px; margin-right: 10px;">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-outline-success">Post comment</button>
        </form>
    </div>
    <br>
    <#if product.getMessages()?has_content>
        <h2>Comments:</h2>
        <#list product.getMessages() as message>
            <p>${message.getAuthor().getUsername()}:</p>
            <p class="comment">${message.getText()}</p>
            <#if message.getAuthor().getId() == user.getId() || isAdmin>
            <a role="button" class="btn black-btn-outline"
               href="/message/delete/${product.getId()}/${message.getId()}">Delete</a>
               </#if>
        </#list>
    </#if>


</@c.page>