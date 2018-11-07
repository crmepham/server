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
                                        <td><a href="/applications/secrets/${s.id}">${s.context}</a></td>
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
                                    <td><a href="/applications/reminders/${r.id}">${r.instruction}</a></td>
                                    <td><p>${r.context!''}</p></td>
                                    <td><p>${r.day}/${r.month}</p></td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </#if>

                        <br>

                        <#assign todos = results['Todos']/>
                        <#if todos?size gt 0>
                            <h3>Todos (${todos?size})</h3>
                            <table class="table normal-table">
                                <thead>
                                <tr>
                                    <th width="60%">Instruction</th>
                                    <th width="40%">Context</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list todos as t>
                                <tr>
                                    <td><a href="/applications/todos/${t.id}">${t.instruction}</a></td>
                                    <td>${t.context!''}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </#if>

                        <br>

                        <#assign files = results['Files']/>
                        <#if files?size gt 0>
                            <h3>Files (${files?size})</h3>
                            <table class="table normal-table">
                                <thead>
                                <tr>
                                    <th width="80%">Title</th>
                                    <th width="20%">Type</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list files as f>
                                <tr>
                                    <td><a href="/applications/files/${f.id}">${f.title}</a></td>
                                    <td>${f.type}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </#if>

                        <br>

                        <#assign fileProperties = results['File Properties']/>
                        <#if fileProperties?size gt 0>
                            <h3>File Properties (${fileProperties?size})</h3>
                            <table class="table normal-table">
                                <thead>
                                <tr>
                                    <th width="40%">Name</th>
                                    <th width="60%">Value</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list fileProperties as fp>
                                <tr>
                                    <td><a href="/applications/files/${fp.fileId}">${fp.name}</a></td>
                                    <td>${fp.value}</td>
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