<#include '../shared/header.ftl' />

<@pageHeader title='Files' uri='/applications/files' action="Create" actionUri="/applications/files/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Files</div>
                <div class="card-body">
                    <#if files?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="80%">External Reference</th>
                                    <th width="20%">Type</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list files as f>
                                <tr>
                                    <td><p><a href="/applications/files/${f.id?c}">${f.title!f.externalReference}</a></p></td>
                                    <td><p>${f.type}</p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no files defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />