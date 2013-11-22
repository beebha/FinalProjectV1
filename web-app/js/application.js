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

var optionsCnt = 2;

function questionTypeChange(allQnTypes)
{
    $.each(allQnTypes, function(index, value) {
        if($('#questionTypeMenu').val() == value)
        {
            if($('#questionTypeMenu').val() != "Comment")
            {
                $('#answerSection').show();
                if($('#questionTypeMenu').val() == "Discrete Rating Scale" || $('#questionTypeMenu').val() == "Numerical Slider Scale")
                {
                    $('#answerSectionForNumericalSliderAndDiscreteRating').show();
                    $('#answerSectionForMultipleChoiceAndRanking').hide();
                    if($('#questionTypeMenu').val() == "Discrete Rating Scale") {
                        $("#scaleMenuTxt").html("Discrete Rating Scale");
                        $("#scaleStartLblTxt").html("Discrete Rating Scale Start Label");
                        $("#scaleEndLblTxt").html("Discrete Rating Scale End Label");
                    } else {
                        $("#scaleMenuTxt").html("Numerical Slider Scale");
                        $("#scaleStartLblTxt").html("Numerical Slider Scale Start Label");
                        $("#scaleEndLblTxt").html("Numerical Slider Scale End Label");
                    }
                    $("#scaleMenu").prop("required", true);
                    $("#scaleStartLbl").prop("required", true);
                    $("#scaleEndLbl").prop("required", true);
                    $("#option1").prop("required", false);
                    $("#option2").prop("required", false);
                } else {
                    $('#answerSectionForNumericalSliderAndDiscreteRating').hide();
                    $('#answerSectionForMultipleChoiceAndRanking').show();
                    $("#scaleMenu").prop("required", false);
                    $("#scaleStartLbl").prop("required", false);
                    $("#scaleEndLbl").prop("required", false);
                    $("#option1").prop("required", true);
                    $("#option2").prop("required", true);
                }
            } else {
                $('#answerSection').hide();
                $('#answerSectionForNumericalSliderAndDiscreteRating').hide();
                $('#answerSectionForMultipleChoiceAndRanking').hide();
                $("#scaleMenu").prop("required", false);
                $("#scaleStartLbl").prop("required", false);
                $("#scaleEndLbl").prop("required", false);
                $("#option1").prop("required", false);
                $("#option2").prop("required", false);
            }
            $('#moreOptions').html("");
            optionsCnt = 2;
        }
    });
}

function addOption() {
    optionsCnt++;
    var optionID = "option"+optionsCnt;
    var optionHTMl = "<div data-role='fieldcontain'>" +
        "<label for='"+optionID+"'>Option "+optionsCnt+"</label>" +
        "<input name='"+optionID+"' id='"+optionID+"' data-mini='true' required>" +
        "</div>";
    $('#moreOptions').append(optionHTMl).trigger("create");
}

function isEmpty(objID) {
    return $.trim($('#'+objID).val()).length
}
