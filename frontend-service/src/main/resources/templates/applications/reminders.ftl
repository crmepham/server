<#include '../shared/header.ftl' />

<@pageHeader title='Reminders' uri='/applications/reminders' action="Create" actionUri="/applications/reminders/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Secrets</div>
                <div class="card-body">
                    <#if reminders?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="80%">Instruction</th>
                                    <th width="20%">Action Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list reminders as r>
                                <tr>
                                    <td><p><a href="/applications/reminders/${r.id}">${r.instruction}</a></p></td>
                                    <td><p>${r.day}/${r.month}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no reminders defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />