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

function isEmpty(objID) {
    return $.trim($('#'+objID).val()).length
}
