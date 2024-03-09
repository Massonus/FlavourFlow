<#import "../parts/common.ftlh" as c>

<@c.page>

<input id="fileupload" type="file" name="fileupload"/>
<input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}"/>
<button id="upload-button" onclick="uploadFile()"> Upload</button>

</@c.page>