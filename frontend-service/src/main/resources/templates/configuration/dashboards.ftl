<#include '../shared/header.ftl' />

<@pageHeader title="Dashboards"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if dashboards?size gt 0>
                        <table class="data-table" class="display" style="width:100%;">
                            <thead>
                                <tr>
                                    <th width="70%">URI</th>
                                    <th class="text-right" width="80%">Enabled</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list dashboards as d>
                                <tr>
                                    <td><p><a href="/configuration/dashboards/${d.id}">${d.uri!''}</a></p></td>
                                    <td class="text-right"><p><@iconBoolean d.enabled /></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no dashboards defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../shared/footer.ftl' />