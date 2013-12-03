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
    $('#'+usernameID).val('');
    $('#'+passwordID).val('');
}

var optionsCnt = 2;

function questionTypeChange(allQnTypes, totalOptionsCnt, qnView)
{
    $.each(allQnTypes, function(index, value) {
        if($('#questionTypeMenu'+qnView).val() == value)
        {
            if($('#questionTypeMenu'+qnView).val() != "Comment")
            {
                $('#answerSection').show();
                if($('#questionTypeMenu'+qnView).val() == "Discrete Rating Scale" || $('#questionTypeMenu'+qnView).val() == "Numerical Slider Scale")
                {
                    $('#answerSectionForNumericalSliderAndDiscreteRating').show();
                    $('#answerSectionForMultipleChoiceAndRanking').hide();
                    if($('#questionTypeMenu'+qnView).val() == "Discrete Rating Scale") {
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
            $('#removeOptionBtn').hide();
            $('#moreOptions').html("");
            optionsCnt = totalOptionsCnt;
        }
    });
}

function addOption()
{
    optionsCnt++;
    var optionID = "option"+optionsCnt;
    var mainOptionHtml = "<div data-role='fieldcontain'>" +
        "<label for='"+optionID+"'>Option "+optionsCnt+"</label>" +
        "<input name='"+optionID+"' id='"+optionID+"' data-mini='true' required>" +
        "</div>";

    if($('#'+optionID+'HTML').length > 0) {
        $('#'+optionID+'HTML').html(mainOptionHtml).trigger("create");
    } else {
        $('#moreOptions').append("<div id='"+optionID+"HTML'>"+mainOptionHtml+"</div>").trigger("create");
    }
    $('#removeOptionBtn').show();
}

function removeOption()
{
    $('#option'+optionsCnt+'HTML').html("");
    optionsCnt --;
    if(optionsCnt == 2) {
        $('#removeOptionBtn').hide();
    }
}

function additionalInfoSurveyStep2(btnName)
{
    $('#submitBtnClicked').val(btnName);
}

function setRatingValues(id, count)
{

}

function isEmpty(objID) {
    return $.trim($('#'+objID).val()).length;
}
