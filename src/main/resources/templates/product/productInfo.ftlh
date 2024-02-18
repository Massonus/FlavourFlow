<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <div class="card-columns" id="message-list">
        <#if product.getMessages()?has_content>
            <h2>Comments:</h2>
            <#list product.getMessages() as message>
                <div class="card my-3">
                    <div class="m-2">
                        <span>${message.getText()}</span><br/>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <p>${message.getAuthor().getUsername()}:</p>
                            <a class="col align-self-center" href="/message/like/${message.getId()}">
                                <#if message.getIsUnliked()>
                                    <i class="bi bi-heart"></i>
                                <#else>
                                    <i class="bi bi-heart-fill"></i>
                                </#if>
                                ${message.getLikesCount()}
                            </a>
                            <#if message.getAuthor().getId() == user.getId() || isAdmin>
                                <a role="button" class="btn black-btn-outline"
                                   href="/message/delete/${message.getId()}">Delete</a>
                            <a class="col btn btn-primary" href="/message/editing/${message.getId()}">Edit</a>
                            </#if>
                        </div>
                    </div>
                </div>
            <#else>
                No message
            </#list>
        </#if>
    </div>

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

</@c.page>