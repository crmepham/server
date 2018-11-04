<#include '../shared/header.ftl' />

<@pageHeader title='Uploader' uri='/applications/uploader'/>

<div class="container h-100">
    <div class="row h-100">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Files</div>
                <div class="card-body">

                    <div class="alert alert-primary" role="alert">
                        Use this form to upload multiple files in one go. Once the uploading is complete the newly
                        uploaded files will be accessible from <a href="/applications/files">here</a>. The new files
                        will be given a temporary reference of <em>undefined-#-filename</em>. These new files will need to be
                        updated manually.
                    </div>

                    <form name="files" action="/applications/files/multiple/upload" method="post" enctype="multipart/form-data">

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">Path Suffix</label></div>
                            <div class="col-10"><input name="pathSuffix" class="form-control no-padding" type="text" placeholder="Path suffix"></div>
                        </div>

                        <div class="form-group row">
                            <div class="col-2"><label class="col-form-label right">File(s)</label></div>
                            <div class="col-10"><input type="file" name="files" multiple/></div>
                        </div>

                        <button class="btn btn-primary btn-sm float-right">Upload</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<#include '../shared/footer.ftl' />