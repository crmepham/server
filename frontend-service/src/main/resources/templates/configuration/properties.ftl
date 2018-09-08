<#include '../shared/header.ftl' />

<@pageHeader title="Properties" uri="/configuration/properties"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if properties?? && properties?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="25%">Reference</th>
                                    <th width="25%">Value</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list properties as p>
                                <tr>
                                    <td><p><a href="/configuration/properties/${p.externalReference}">${p.externalReference!''}</a></p></td>
                                    <td><p>${p.value!''}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no properties defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../shared/footer.ftl' />