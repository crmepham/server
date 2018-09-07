<#include '../shared/header.ftl' />

<@pageHeader title="Errors" uri="/configuration/errors"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if errors?? && errors?size gt 0>
                        <table class="data-table" class="display" style="width:100%;">
                            <thead>
                                <tr>
                                    <th width="25%">Reference</th>
                                    <th width="25%">Class</th>
                                    <th width="25%">Exception</th>
                                    <th width="25%">Created</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list errors as e>
                                <tr>
                                    <td><p><a href="/configuration/errors/${e.id}">${e.entityReference!''}</a></p></td>
                                    <td><p>${e.entity}</p></td>
                                    <td><p>${e.exception}</p></td>
                                    <td><p>${e.created?string('dd/MM/yyyy HH:mm:ss')}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no fragments defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />