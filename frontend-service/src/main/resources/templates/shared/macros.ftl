<#macro pageHeader title uri="">
    <#assign toUse = '#' />
    <#if uri?has_content>
        <#assign toUse = uri />
    </#if>
    <header>
        <nav class="navbar navbar-light bg-light">
            <a href="${toUse}"><span class="navbar-brand mb-0 h1 page-title">${title}</span></a>
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