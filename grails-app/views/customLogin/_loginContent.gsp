<g:formRemote name='loginUserForm' url="[controller: 'customLogin', action: 'verifyUser']" method="POST">
    <div data-role="fieldcontain">
        <label for="username">
            Email
        </label>
        <input name="j_username" id="username" placeholder="" value="" type="email">
    </div>
    <div data-role="fieldcontain">
        <label for="password">
            Password
        </label>
        <input name="j_password" id="password" placeholder="" value="" type="password">
    </div>
    <center>
        <input type="submit" data-inline="true" value="Submit">
        <input type="button" data-inline="true" value="Clear" onclick="clearButtonClicked();">
    </center>
    <div>
        <g:link controller="register">
            Register
        </g:link>
    </div>
    <div>
        <g:link mapping="forgotPassword">
            Forgot Password
        </g:link>
    </div>
</g:formRemote>