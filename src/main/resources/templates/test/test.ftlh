<#import "../parts/common.ftlh" as c>

<@c.page>

<input id="fileUpload" type="file" name="fileUpload" onchange="checkFile()"/>
<input type="hidden" id="csrf" name="_csrf" value="${_csrf.token}"/>
<button id="upload-button" onclick="uploadFile()"> Upload</button>

</@c.page>