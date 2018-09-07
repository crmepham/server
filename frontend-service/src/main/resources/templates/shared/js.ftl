<script src="/js/html5shiv.min.js" type="text/javascript"></script>
<script src="/js/jquery.min.js" type="text/javascript"></script>
<script src="/js/popper.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/respond.min.js" type="text/javascript"></script>
<script src="/js/common.js" type="text/javascript"></script>
<script src="/js/toastr.min.js" type="text/javascript"></script>
<#if styles?? && styles?size gt 0>
    <#list styles as style>
        <script src="/js/${style}.js" type="text/javascript"></script>
    </#list>
</#if>