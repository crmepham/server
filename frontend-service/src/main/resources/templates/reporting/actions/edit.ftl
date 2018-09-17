<#include '../../shared/header.ftl' />

<@pageHeader title='Actions' uri='/reporting/actions' />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <#if item??>
                        <input type="hidden" value="${item.id}" name="id">

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Class Name</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.className}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Object Reference</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.objectReference!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">State</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.state}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Result Message</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.resultMessage!''}" readonly></div>
                        </div>

                        <@commonMeta item=item/>

                        <a href="/reporting/actions"><button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Back</button></a>
                    <#else>
					<div class="alert alert-warning">There is no action information to display</div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />