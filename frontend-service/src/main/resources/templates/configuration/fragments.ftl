<#include '../shared/header.ftl' />

<@pageHeader title="Fragments" uri="/configuration/fragments"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">&nbsp;</div>
                <div class="card-body">
                    <#if fragments?size gt 0>
                        <table class="data-table" class="display" style="width:100%;">
                            <thead>
                                <tr>
                                    <th width="70%">Fragment</th>
                                    <th width="70%">URI</th>
                                    <th class="text-right" width="80%">Enabled</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list fragments as f>
                                <tr>
                                    <td><p><a href="/configuration/fragments/${f.id}">${f.title!externalReference!''}</a></p></td>
                                    <td><p>${f.uri}</p></td>
                                    <td class="text-right"><p><@iconBoolean f.enabled /></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no fragments defined.</td></tr></table>
                    </#if>
                    <br>
                    <a href="/configuration/fragments/create">
                        <button class="btn btn-primary btn-sm float-right">Create</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />