<#import "../parts/common.ftlh" as c>

<@c.page>

<input id="fileupload" type="file" name="fileupload" onchange="checkFile()"/>
<input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
<button id="upload-button" onclick="uploadFile()"> Upload</button>

</@c.page>