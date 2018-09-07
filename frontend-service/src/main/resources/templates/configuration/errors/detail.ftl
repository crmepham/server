<#include '../../shared/header.ftl' />

<@pageHeader title='Errors' uri='/configuration/errors' />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <#if item??>
                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">ID</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.id}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Entity ID</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.entityId!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Entity Reference</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.entityReference!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Entity</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.entity!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Exception</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.exception!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Stacktrace</label></div>
                            <div class="col-10"><textarea id="content" readonly>${item.stackTrace!''}</textarea></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Created</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.created?string('dd/MM/yyyy HH:mm:ss')!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Created User</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.createdUser!''}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Last Updated</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.lastUpdated?string('dd/MM/yyyy HH:mm:ss')}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Last Updated User</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.lastUpdatedUser!''}" readonly></div>
                        </div>

                        <a href="/configuration/errors"><button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Back</button></a>
                    <#else>
					<div class="alert alert-warning">There is no error information to display</div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />