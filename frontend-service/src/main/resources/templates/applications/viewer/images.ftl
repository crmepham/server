<#if images?? && images?size gt 0>
    <#list images as f, i>
        <div class="image-module">
            <#assign suffix = f.absolutePath?substring(f.absolutePath?last_index_of(".") + 1) />
            <a href="/applications/files/${f.id?c}"><img style="width:300px;" src="data:image/${suffix};base64,${i}" title="${f.title!''}"/></a>
        </div>
    </#list>
</#if>

