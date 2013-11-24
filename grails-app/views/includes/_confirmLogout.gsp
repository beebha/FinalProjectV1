<div data-role="popup" id="popupDialog" data-overlay-theme="a" data-theme="c" class="ui-corner-all">
    <div data-role="header" data-theme="a" class="ui-corner-top">
        <h1>Confirm Logout</h1>
    </div>
    <div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
        <p>
            Click <b>Logout</b> to logout without saving.<br>
            Click <b>Cancel</b> to cancel logout.<br>
        </p>
        <center>
            <g:link controller="logout" action="logout">
                <input type="button" value="Logout" data-mini="true" data-inline="true" data-theme="c">
            </g:link>
            <a href="#" data-role="button" data-mini="true" data-inline="true" data-rel="back" data-transition="flow" data-theme="b">Cancel</a>
        </center>
    </div>
</div>