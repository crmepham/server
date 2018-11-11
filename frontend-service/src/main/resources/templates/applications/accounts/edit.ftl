<#include '../../shared/header.ftl' />

<script src="/js/accounts.js" type="text/javascript"></script>

<@pageHeader title='Accounts' uri='/applications/accounts' action="Add Transaction" modal=true/>

<#include 'modal.ftl' />

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <form name="account" action="/applications/accounts/update" method="post">

                        <#if item.id??>
                            <input type="hidden" name="id" value="${item.id}"/>
                        </#if>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Name*</label></div>
                            <div class="col-10"><input name="name" class="form-control no-padding" type="text" placeholder="Name" value="${item.name!''}"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right"></label></div>
                            <div class="col-10">* denotes required fields.</div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right"><#if item.id??>Update<#else>Create</#if></button>
                        <a href="/applications/accounts">
                            <button type="button" class="btn btn-warning btn-sm float-right">Cancel</button>
                        </a>
                        <#if item.id??>
                            <a href="/applications/accounts/${item.id}/delete">
                                <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                            </a>
                        </#if>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#if item.id?? && item.transactions?size gt 0>
        <div class="row">
            <div class="col-md-12">
                <div class="card margin-bottom">
                    <div class="card-header">Summary</div>
                    <div class="card-body">
                        <table class="table normal-table">
                            <thead>
                                <tr>
                                    <th>Incoming</th>
                                    <th>Outgoing</th>
                                    <th>Remaining</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><p>£${item.getTotal("incoming")}</p></td>
                                    <td><p>£${item.getTotal("outgoing")}</p></td>
                                    <td><p>£${(item.getTotal("incoming") - item.getTotal("outgoing")!0)}</p></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </#if>

    <#if item.id?? && item.getTotal("incoming") gt 0>
        <div class="row">
            <div class="col-md-12">
                <div class="card margin-bottom">
                    <div class="card-header">Incoming</div>
                    <div class="card-body">
                        <table class="table normal-table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Value</th>
                                <th>Percent</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list item.getTransactions("incoming") as t>
                                <tr>
                                    <td><p>${t.name}</p></td>
                                    <td><p>£${t.value}</p></td>
                                    <td><p>${t.percent}%</p></td>
                                    <td>
                                        <a href="/applications/accounts/${item.id?c}/transaction/${t.id?c}/delete">
                                            <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                                        </a>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </#if>

    <#if item.id?? && item.getTotal("outgoing") gt 0>
        <div class="row">
            <div class="col-md-12">
                <div class="card margin-bottom">
                    <div class="card-header">Outgoing</div>
                    <div class="card-body">
                        <table class="table normal-table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Value</th>
                                <th>Percent</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list item.getTransactions("outgoing") as t>
                            <tr>
                                <td><p>${t.name}</p></td>
                                <td><p>£${t.value}</p></td>
                                <td><p>${t.percent}%</p></td>
                                <td>
                                    <a href="/applications/accounts/${item.id?c}/transaction/${t.id?c}/delete">
                                        <button type="button" class="btn btn-danger btn-sm float-right">Delete</button>
                                    </a>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </#if>
</div>

<#include '../../shared/footer.ftl' />