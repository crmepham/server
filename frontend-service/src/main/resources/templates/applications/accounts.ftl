<#include '../shared/header.ftl' />

<@pageHeader title='Accounts' uri='/applications/accounts' action="Create" actionUri="/applications/accounts/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Accounts</div>
                <div class="card-body">
                    <#if accounts?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="100%">Name</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list accounts as a>
                                <tr>
                                    <td><p><a href="/applications/accounts/${a.id}">${a.name}</a></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no accounts defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />