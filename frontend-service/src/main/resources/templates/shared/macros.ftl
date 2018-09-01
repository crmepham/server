<#macro pageHeader title>
    <header>
        <nav class="navbar navbar-light bg-light">
            <span class="navbar-brand mb-0 h1 page-title">${title}</span>
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