<#include 'shared/header.ftl' />

<@pageHeader title='Search' uri='/search'/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header"><#if searchTerm?has_content>Search results for <strong><i>${searchTerm!''}.</i></strong><#else>&nbsp;</#if></div>
                <div class="card-body">
                    <#if !empty>
                        <#assign secrets = results['Secrets']/>
                        <#if secrets?size gt 0>
                            <h3>Secrets (${secrets?size})</h3>
                            <table class="table normal-table">
                                <thead>
                                <tr>
                                    <th width="40%">Context</th>
                                    <th width="40%">Description</th>
                                    <th width="20%">Type</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list secrets as s>
                                    <tr>
                                        <td><a href="/secrets/${s.id}">${s.context}</a></td>
                                        <td>${s.description!''}</td>
                                        <td>${s.type!''}</td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </#if>

                        <br>

                        <#assign reminders = results['Reminders']/>
                        <#if reminders?size gt 0>
                            <h3>Reminders (${reminders?size})</h3>
                            <table class="table normal-table">
                                <thead>
                                <tr>
                                    <th width="40%">Instruction</th>
                                    <th width="40%">Context</th>
                                    <th width="20%">Action Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list reminders as r>
                                <tr>
                                    <td><a href="/reminders/${r.id}">${r.instruction}</a></td>
                                    <td>${r.context!''}</td>
                                    <td><p>${r.day}/${r.month}</p></td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </#if>

                    <#else>
                        <p>No results found.</p>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include 'shared/footer.ftl' />