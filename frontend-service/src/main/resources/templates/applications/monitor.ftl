<#include '../shared/header.ftl' />

<@pageHeader title='Monitor' uri='/applications/monitor' action="Create" actionUri="/applications/monitor/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Servers</div>
                <div class="card-body">
                    <#if servers?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="30%">Name</th>
                                    <th width="60%">URI</th>
                                    <th class="text-right" width="5%">Reachable</th>
                                    <th class="text-right" width="5%">Notify</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list servers as s>
                                <tr>
                                    <td><p><a href="/applications/monitor/${s.id}">${s.name}</a></p></td>
                                    <td><p><a href="${s.uri}">${s.uri}</a></p></td>
                                    <td class="text-right"><p><@iconBoolean s.reachable /></p></td>
                                    <td class="text-right"><p><@iconBoolean s.notify /></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no servers defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />