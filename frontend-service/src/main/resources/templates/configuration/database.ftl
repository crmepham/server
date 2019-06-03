<#include '../shared/header.ftl' />

<@pageHeader title="Database Configurations" uri="/configuration/database" action="Create" actionUri="/configuration/database/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if configurations?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="25%">Database</th>
                                    <th width="25%">Host</th>
                                    <th width="25%">Type</th>
                                    <th class="text-right" width="25%">Enabled</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list configurations as c>
                                <tr>
                                    <td><p><a href="/configuration/database/${c.id}">${c.name!''}</a></p></td>
                                    <td><p>${c.host!''}</p></td>
                                    <td><p>${c.type!''}</p></td>
                                    <td class="text-right"><p><@iconBoolean c.enabled /></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no database configurations defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />