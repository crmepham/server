<#include 'shared/header.ftl' />

<@pageHeader title='Passwords' uri='/passwords' action="Create" actionUri="/secrets/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Secrets</div>
                <div class="card-body">
                    <#if secrets?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="100%">Context</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list secrets as s>
                                <tr>
                                    <td><p><a href="/secrets/${s.id}">${s.context!''}</a></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no secrets defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include 'shared/footer.ftl' />