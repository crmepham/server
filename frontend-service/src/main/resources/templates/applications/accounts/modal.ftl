<div class="modal fade" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h5 class="modal-title">Create Transaction</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body" style="background: #f1f8ff;">

                <div class="alert alert-danger modal-error" role="alert">
                    Please ensure all required fields are filled in.
                </div>
                <div class="alert alert-success modal-success" role="alert">
                    Successfully created new account transaction.
                </div>

                <form name="account_transaction" action="/applications/accounts/transaction/create" method="post">

                    <input type="hidden" value="${item.id}" id="account-transaction-account-id" />

                    <div class="form-group row">
                        <div class="col-3"><label class="col-form-label right">Name*</label></div>
                        <div class="col-9"><input id="account-transaction-name" class="form-control no-padding" type="text" placeholder="Name" required></div>
                    </div>

                    <div class="form-group row">
                        <div class="col-3"><label class="col-form-label right">Value*</label></div>
                        <div class="col-9"><input id="account-transaction-value" class="form-control no-padding" type="number" value="0" required></div>
                    </div>

                    <div class="form-group row">
                        <div class="col-3"><label class="col-form-label right">Percent*</label></div>
                        <div class="col-9"><input id="account-transaction-percent" class="form-control no-padding" type="number" value="100" required></div>
                    </div>

                    <div class="form-group row">
                        <div class="col-3"><label class="col-form-label right">Type*</label></div>
                        <div class="col-9">
                            <select id="account-transaction-type" class="select">
                                <option disabled>Type</option>
                                <option selected value="incoming">Incoming</option>
                                <option value="outgoing">Outgoing</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-3"><label class="col-form-label right"></label></div>
                        <div class="col-9">* denotes required fields.</div>
                    </div>
                </form>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="account-transaction-create-btn">Create</button>
            </div>

        </div>
    </div>
</div>