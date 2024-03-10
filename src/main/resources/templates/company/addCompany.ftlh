<#import "../parts/common.ftlh" as c>

<@c.page>

    <h2>Add Company</h2>
    <form onsubmit="createCompany(event)">
        <label for="companyTitle">Title:</label>
        <input type="text" id="companyTitle" name="companyTitle" required><br><br>

        <div class="mb-3 row">
            <label for="priceCategory">Price Category:</label>
            <select class="form-select" id="priceCategory" required>
                <option value="LOW">LOW</option>
                <option value="MEDIUM">MEDIUM</option>
                <option value="HIGH">HIGH</option>
                <option value="PREMIUM">PREMIUM</option>
            </select>
        </div>

        <div class="mb-3 row">
            <label for="companyCountry">Company Country:</label>
            <select class="form-select" id="companyCountry" required>
                <#list countries as country>
                    <option value=${country.getId()}>${country.getTitle()}</option>
                </#list>
            </select>
        </div>

        <br><br>

        <div class="mb-3 row">
            <label for="kitchenCategory">Kitchen Category:</label>
            <select class="form-select" id="kitchenCategory" required>
                <#list categories as category>
                    <option value=${category.getId()}>${category.getTitle()}</option>
                </#list>
            </select>
        </div>

        <span id="companyImageError" class="error"></span>
        <h5>Choose a file or enter link</h5>
        <label for="companyFileUpload">File:</label>
        <input type="file" id="companyFileUpload" name="companyFileUpload" onchange="checkFile('companyFileUpload')">

        <br><br>

        <label for="companyImageLink"></label>
        <input placeholder="image link" type="text"
               name="companyImageLink" id="companyImageLink">
        <br><br>

        <input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Send">
    </form>
    <br><br>

</@c.page>