</body>
<#if showNavigation!true>
<footer>
    <#if session_dashboard??>
        <a href="/configuration/dashboards/${session_dashboard.id}">
            <button type="submit" class="btn btn-primary float-right full-height">Edit</button>
        </a>
    <#else>
        <a href="/configuration/dashboards">
            <button type="submit" class="btn btn-primary float-right full-height">Override</button>
        </a>
    </#if>
</footer>
</#if>
<script>
    $(document).ready(function() {

        maybeToast();
    });

    function maybeToast() {
        var message = "${toast!''}";
        if (message.length > 0) {
            toastr.info(message);
        }
    }
</script>
</body>
</html>