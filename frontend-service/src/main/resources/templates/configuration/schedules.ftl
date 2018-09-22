<#include '../shared/header.ftl' />

<@pageHeader title='Schedules' uri='/schedules'/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Secrets</div>
                <div class="card-body">
                    <#if schedules?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="25%">Title</th>
                                    <th width="20%">Cron Expression</th>
                                    <th width="55%">Description</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list schedules as s>
                                <tr>
                                    <td><p><a href="/configuration/schedules/${s.id}">${s.title!s.externalReference}</a></p></td>
                                    <td><p>${s.cronExpression}</p></td>
                                    <td><p>${s.description!''}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no schedules defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />