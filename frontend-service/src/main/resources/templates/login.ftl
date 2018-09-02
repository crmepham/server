<#include 'shared/header.ftl' />
    <div class="container h-100">
        <div class="row h-100 align-items-center">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        Login
                    </div>
                    <div class="card-body">
                        <#if SPRING_SECURITY_LAST_EXCEPTION??>ddddd
						<div class="alert alert-danger btn-override">${SPRING_SECURITY_LAST_EXCEPTION['message']!'nope'}</div>
                        </#if>
                        <form id="login" action="/login" method="post">
                            <div class="form-group">
                                <div>
                                    <input type="text" class="form-control" name="username" value="" placeholder="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="password" class="form-control" name="password" placeholder="password">
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary float-right">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#include 'shared/footer.ftl' />