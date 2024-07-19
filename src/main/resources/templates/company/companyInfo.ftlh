<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>

<@c.page>

    <div style="text-align: center">
        <img
                alt="product image"
                src="${company.getImageLink()}"
                style="width: 25%">
    </div>
    <h2>${company.getTitle()}</h2>
    <legend class="rating__caption">Rating</legend>
    <#if company.getCurrentRating()?has_content && known>
        <fieldset class="rating">
            <div class="rating__group">
                <input class="rating__star" type="radio" name="health" value="1"
                       onclick="rate(event, ${company.getId()}, '${company.getClass().getSimpleName()}'.toUpperCase(), this.value)"
                       aria-label="Terrible" <#if company.getCurrentRating() == 1>checked</#if>>

                <input class="rating__star" type="radio" name="health" value="2"
                       onclick="rate(event, ${company.getId()}, '${company.getClass().getSimpleName()}'.toUpperCase(), this.value)"
                       aria-label="Tolerable" <#if company.getCurrentRating() == 2>checked</#if>>

                <input class="rating__star" type="radio" name="health" value="3"
                       onclick="rate(event, ${company.getId()}, '${company.getClass().getSimpleName()}'.toUpperCase(), this.value)"
                       aria-label="Normal" <#if company.getCurrentRating() == 3>checked</#if>>

                <input class="rating__star" type="radio" name="health" value="4"
                       onclick="rate(event, ${company.getId()}, '${company.getClass().getSimpleName()}'.toUpperCase(), this.value)"
                       aria-label="Fine" <#if company.getCurrentRating() == 4>checked</#if>>

                <input class="rating__star" type="radio" name="health" value="5"
                       onclick="rate(event, ${company.getId()}, '${company.getClass().getSimpleName()}'.toUpperCase(), this.value)"
                       aria-label="Great" <#if company.getCurrentRating() == 5>checked</#if>>

                <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
            </div>
        </fieldset>

    <#elseif company.getRating()?has_content>
        <div class="object-stars">
            <#assign rating = (company.getCurrentRating()?number)?int />
            <#list 1..5 as i>
                <#if i <= rating>
                    <object id="star-fill-big"></object>
                <#else>
                    <object id="star-empty-big"></object>
                </#if>
            </#list>
        </div>
    <#else>
        <h5 style="text-align: center">Not rated</h5>
    </#if>
    <br><br>

    <h2>Comments:</h2>
    <br>
    <div id="container-comment" class="container">
        <#if comments?has_content>
            <#list comments as comment>
                <div id="comment-card" class="card my-3">
                    <div class="m-2">
                        <span>${comment.getAuthor().getUsername()}:</span>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <p id="comment-text">${comment.getText()}</p>
                            <a id="likes-${comment.getId()}" class="col align-self-center"
                                    <#if known> onclick="likeComment(${comment.getId()},${company.getId()},'${_csrf.token}' )"</#if>>
                                <#if comment.getIsUnliked()>
                                    <i class="bi bi-heart-fill"></i>
                                <#else>
                                    <i class="bi bi-heart"></i>
                                </#if>
                                <span>${comment.getLikesCount()}</span>
                            </a>
                            <#if comment.getAuthor().getId() == currentUserId || isAdmin>
                                <a class="btn black-btn btn-outline-secondary mt-2 w-100"
                                   href="/comment/edit?commentId=${comment.getId()}&itemId=${company.getId()}">Edit</a>

                                <a id="message-delete" role="button" class="btn btn-outline-danger w-100 mt-2"
                                   onclick="deleteMessage(${comment.getId()}, ${company.getId()}, '${_csrf.token}')">Delete</a>
                            </#if>
                        </div>
                    </div>
                </div>
            <#else>
                No comments
            </#list>
        </#if>
    </div>

    <#if known>
        <div class="d-flex justify-content-center" style="width: 100%; display: flex; justify-content: center;">
            <form onsubmit="createMessage(event, ${company.getId()}, '${_csrf.token}')"
                  style="display: flex; flex-direction: row; align-items: center; justify-content: center; width: 70%;">

                <textarea id="comment" class="form-control me-2 flex-grow-1" placeholder="Write your comment"
                          required></textarea>

                <button type="submit" class="btn btn-outline-success">Post comment</button>
            </form>
        </div>
    </#if>

    <br><br><br><br>
</@c.page>

