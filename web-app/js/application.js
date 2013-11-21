if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function clearButtonClicked(usernameID, passwordID)
{
    console.log("clearButtonClicked");
    $('#'+usernameID).val('');
    $('#'+passwordID).val('');
}

function questionTypeChange(allQnTypes)
{
    $.each(allQnTypes, function(index, value) {
        if($('#questionTypeMenu').val() == value) {
            console.log("Found selected type that matches: "+ $('#questionTypeMenu').val());
        }
    });
}

function isEmpty(objID) {
    return $.trim($('#'+objID).val()).length
}
