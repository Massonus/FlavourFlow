<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Edit message</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editMessage(event, ${message.getId()}, '${message.getMessageItemType()}', ${itemId}, '${_csrf.token}')" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="comment" class="col-sm-4 col-form-label">Text:</label>
                            <div class="col-sm-8">
                                <input style="height: 68px; margin-right: 10px;" id="comment" type="text" class="form-control" placeholder="message text" value="${message.getText()}" required>
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/company/info/${itemId}">Cancel</a>

                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>