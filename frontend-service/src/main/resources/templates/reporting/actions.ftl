<#include '../shared/header.ftl' />

<@pageHeader title="Actions" uri="/reporting/actions"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if actions?? && actions?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="20%">Class Name</th>
                                    <th width="20%">Reference</th>
                                    <th width="45%">Message</th>
                                    <th width="15%">State</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list actions as a>
                                <tr>
                                    <td><p><a href="/reporting/actions/${a.id}">${a.className}</a></p></td>
                                    <td><p>${a.objectReference!''}</p></td>
                                    <td><p>${a.resultMessage!''}</p></td>
                                    <td><p>${a.state}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no actions defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../shared/footer.ftl' />