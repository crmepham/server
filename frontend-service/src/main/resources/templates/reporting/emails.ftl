<#include '../shared/header.ftl' />

<@pageHeader title="Emails" uri="/reporting/emails"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if emails?? && emails?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="20%">To</th>
                                    <th width="50%">Subject</th>
                                    <th width="20%">State</th>
                                    <th width="10%">Sent</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list emails as e>
                                <tr>
                                    <td><p><a href="/reporting/emails/${e.id}">${e.to}</a></p></td>
                                    <td><p>${e.subject}</p></td>
                                    <td><p>${e.state}</p></td>
                                    <td><p><@iconBoolean e.sent/></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no emails defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../shared/footer.ftl' />