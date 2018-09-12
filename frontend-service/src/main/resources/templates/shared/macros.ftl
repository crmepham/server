<#macro pageHeader title uri="" action="" actionUri="">
    <#assign toUse = '#' />
    <#if uri?has_content>
        <#assign toUse = uri />
    </#if>
    <header>
        <nav class="navbar navbar-light bg-light">
            <a href="${toUse}"><span class="navbar-brand mb-0 h1 page-title">${title}</span></a>
            <#if action?has_content>
                <div class="float-right">
                    <a href="<#if actionUri?has_content>${actionUri}<#else>#</#if>">
                        <button type="button" class="btn btn-primary btn-sm float-right">${action}</button>
                    </a>
                </div>
            </#if>
        </nav>
    </header>
</#macro>

<#macro iconBoolean value=false>
    <#if value>
        <i class="fas fa-check"></i>
    <#else>
        <i class="fas fa-times"></i>
    </#if>
</#macro>

<#macro dashboardFragment reference fragments>
    <#if fragments[reference]??>
        <#assign fragment = fragments[reference]>
        <#if fragment.errors?? && fragment.errors?size gt 0>
            <div class="card module">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <div class="alert alert-warning">Failed to load fragment. See errors for more information.</div>
                </div>
            </div>
        <#else>
            <@fragment.design?interpret />
        </#if>
    </#if>
</#macro>

<#macro select name options item>
    <select class="form-control" name="${name}">
        <#list options as o>
            <option value="${o}" <#if item.type?has_content && item.type == o>selected</#if>>${o?cap_first}</option>
        </#list>
    </select>
</#macro>