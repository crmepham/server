<#macro pageHeader title uri="" action="" actionUri="" modal=false>
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
                        <button type="button" class="btn btn-primary btn-sm float-right header-btn" <#if modal>data-toggle="modal" data-target="#modal"</#if>>${action}</button>
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

<#macro commonMeta item>
    <div class="form-group row">
        <div class="col-2"><label class="col-form-label right">Created</label></div>
        <div class="col-10"><input name="created" class="form-control no-padding" type="text" value="${item.created?string('dd/MM/yyyy HH:mm:ss')!''}" placeholder="Created" id="" readonly></div>
    </div>
    <div class="form-group row">
        <div class="col-2"><label class="col-form-label right">Created User</label></div>
        <div class="col-10"><input name="createdUser" class="form-control no-padding" type="text" value="${item.createdUser!''}" placeholder="Created User" id="" readonly></div>
    </div>
    <div class="form-group row">
        <div class="col-2"><label class="col-form-label right">Last Updated</label></div>
        <div class="col-10"><input name="lastUpdated" class="form-control no-padding" type="text" value="${item.lastUpdated?string('dd/MM/yyyy HH:mm:ss')}" placeholder="Last Updated" id="" readonly></div>
    </div>
    <div class="form-group row">
        <div class="col-2"><label class="col-form-label right">Last Updated User</label></div>
        <div class="col-10"><input name="lastUpdatedUser" class="form-control no-padding" type="text" value="${item.lastUpdatedUser!''}" placeholder="Last Updated User" id="" readonly></div>
    </div>
</#macro>