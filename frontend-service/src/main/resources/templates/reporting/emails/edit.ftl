<#include '../../shared/header.ftl' />

<@pageHeader title='Emails' uri='/reporting/emails' />

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card margin-bottom">
                <div class="card-header">Detail</div>
                <div class="card-body">
                    <#if item??>
                        <input type="hidden" value="${item.id}" name="id">

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">To</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.to}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">From</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.from}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">State</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.state}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Subject</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.subject}" readonly></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Body</label></div>
                            <div class="col-10"><textarea readonly>${item.body}</textarea></div>
                        </div>

                        <div class="form-group row">
							<#assign checked = item.sent?string('checked', '')/>
                            <div class="col-2"><label class="col-form-label right">Sent</label></div>
                            <div class="col-10"><input type="checkbox" ${checked} disabled></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Retry Count</label></div>
                            <div class="col-10"><input class="form-control no-padding" type="text" value="${item.retryCount}" readonly></div>
                        </div>

                        <#if item.failureReason??>
                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Failure Reason</label></div>
                            <div class="col-10"><textarea readonly>${item.failureReason!''}</textarea></div>
                        </div>
                        </#if>

                        <@commonMeta item=item/>

                        <a href="/reporting/emails"><button type="button" id="button-cancel" class="btn btn-warning btn-sm float-right">Back</button></a>
                    <#else>
					<div class="alert alert-warning">There is no email information to display</div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#include '../../shared/footer.ftl' />