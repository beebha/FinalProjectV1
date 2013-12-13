<div data-role="popup" id="popupDeleteDialog" data-position-to="window" data-overlay-theme="a" data-theme="c" class="ui-corner-all">
    <div data-role="header" data-theme="a" class="ui-corner-top">
        <h1>Confirm Delete</h1>
    </div>
    <div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
        <p>
            Click <b>Delete</b> to delete survey.<br>
            Click <b>Cancel</b> to cancel.<br>
        </p>
        <center>
            <g:link controller="survey" action="deleteSurvey" params="[surveyID: surveyInstance?.id]">
                <input type="button" value="Delete" data-mini="true" data-inline="true" data-theme="c">
            </g:link>
            <a href="#" data-role="button" data-mini="true" data-inline="true" data-rel="back" data-transition="flow" data-theme="b">Cancel</a>
        </center>
    </div>
</div>