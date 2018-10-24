<#include '../shared/header.ftl' />

<@pageHeader title='Todos' uri='/applications/todos' action="Create" actionUri="/applications/todos/create"/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Todos</div>
                <div class="card-body">
                    <#if todos?size gt 0>
                        <table class="data-table" class="display">
                            <thead>
                                <tr>
                                    <th width="100%">Instruction</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#list todos as t>
                                <tr>
                                    <td><p><a href="/applications/todos/${t.id}">${t.instruction}</a></p></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <table><tr><td>There are currently no todos defined.</td></tr></table>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />