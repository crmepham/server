<#include 'shared/header.ftl' />
<div class="body-wrapper">
<#if session_dashboard??>
    <@session_dashboard.content?interpret />
</#if>
</div>

<#include 'shared/footer.ftl' />