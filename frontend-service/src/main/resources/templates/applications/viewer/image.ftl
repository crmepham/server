<div class="image-module">
<#if !file?? || !image??>
    <p style="color: white;">Could not find image!</p>
<#else>
    <a href="/applications/files/${file.id?c}"><img style="width:300px;" src="data:image/${suffix};base64,${image}" title="${file.title!''}"/></a>
</#if>
</div>