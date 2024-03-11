<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <fieldset class="rating">
        <legend class="rating__caption">Rating</legend>
        <div class="rating__group">
            <input class="rating__star" type="radio" name="health" value="1" onclick="rate(${company.getId()}, this.value)" aria-label="Terrible">
            <input class="rating__star" type="radio" name="health" value="2" onclick="rate(${company.getId()}, this.value)" aria-label="Tolerable">
            <input class="rating__star" type="radio" name="health" value="3" onclick="rate(${company.getId()}, this.value)" aria-label="Normal">
            <input class="rating__star" type="radio" name="health" value="4" onclick="rate(${company.getId()}, this.value)" aria-label="Fine">
            <input class="rating__star" type="radio" name="health" value="5" onclick="rate(${company.getId()}, this.value)" aria-label="Great">
            <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
        </div>
    </fieldset>
    <br><br>
    <div class="card-columns" id="message-list">
        <#if messages?has_content>
            <h2>Comments:</h2>
            <#list messages as message>
                <div class="card my-3">
                    <div class="m-2">
                        <span>${message.getText()}</span><br/>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <p>${message.getAuthor().getUsername()}:</p>
                            <a id="likes" class="col align-self-center"
                               onclick="likeMessage(${message.getId()}, '${message.getMessageItemType()}', ${company.getId()}, '${_csrf.token}')">
                                <#if message.getIsUnliked()>
                                    <i class="bi bi-heart"></i>
                                <#else>
                                    <i class="bi bi-heart-fill"></i>
                                </#if>
                                <span>${message.getLikesCount()}</span>
                            </a>
                            <#if message.getAuthor().getId() == currentUserId || isAdmin>
                                <a role="button" class="btn black-btn-outline"
                                   onclick="deleteMessage(${message.getId()}, '${message.getMessageItemType()}', ${company.getId()}, '${_csrf.token}')">Delete</a>
                                <a class="col btn btn-primary"
                                   href="/message/edit?messageId=${message.getId()}&itemId=${company.getId()}">Edit</a>
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

    <#if known>
        <div class="d-flex justify-content-center" style="width: 100%; display: flex; justify-content: center;">
            <form onsubmit="createMessage(event, ${company.getId()}, '${company.getClass().getSimpleName()}', '${_csrf.token}')"
                  style="display: flex; flex-direction: row; align-items: center; justify-content: center; width: 70%;">
                <label for="comment" class="col-sm-4 col-form-label">Comment</label>
                <input type="text" class="form-control me-2 flex-grow-1" placeholder="Write your comment" name="comment"
                       id="comment" style="height: 68px; margin-right: 10px;">

                <button type="submit" class="btn btn-outline-success">Post comment</button>
            </form>
        </div>
    </#if>



</@c.page>

