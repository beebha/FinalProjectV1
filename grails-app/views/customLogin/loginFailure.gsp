<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<script>

    function clearButtonClicked()
    {
        console.log("clearButtonClicked");
        $('#username').val('');
        $('#password').val('');
    }

</script>
<!-- Home -->
<div data-role="page" id="loginPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project
        </h3>
    </div>
    <div data-role="content">
        <h2>
            Surveys Galore
        </h2>
        <center><div id="errorMsg">Invalid username and/or password.</div></center>
        <g:render template='../customLogin/loginContent'/>
    </div>
</div>
</body>
</html>
