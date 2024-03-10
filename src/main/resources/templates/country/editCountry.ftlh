<#import "../parts/common.ftlh" as c>

<@c.page>

    <form onsubmit="editCountry(event, ${country.getId()}, '${_csrf.token}')">
        <div>
            <label for="countryTitle">Title</label>
            <input type="text" id="countryTitle" name="countryTitle" value="${country.getTitle()}">
        </div>
        <br>
        <div>
            <a role="button" class="btn btn-outline-secondary mt-3" href="/admin/panel">Cancel</a>

            <button type="submit" class="btn black-btn-outline mt-3 ms-2">Save</button>
        </div>
    </form>

</@c.page>