if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$.fn.extend({
    sliderLabels: function(left,right) {
        var $this = $(this);
        var $sliderdiv= $this.next("div.ui-slider[role='application']");
        $sliderdiv
            .css({'font-weight': 'normal'});
        $sliderdiv
            .prepend('<span class="ui-slider-inner-label" style="position: absolute; left:0px; top:20px; text-shadow:none; color:black; font-weight:normal">'+left+ '</span>')
            .append('<span class="ui-slider-inner-label" style="position: absolute; right:0px; bottom:20px; text-shadow:none; color:black; font-weight:normal">'+right+ '</span>');
    }
});

$('#slider').sliderLabels('LEFT','RIGHT');

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
            $('#removeOptionBtn').hide();
            $('#moreOptions').html("");
            optionsCnt = 2;
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

function additionalInfoSurveyStep2(btnName) {
    $('#submitBtnClicked').val(btnName);
    $('#totalOptions').val(optionsCnt);
}

function editQn(qnID) {
    console.log(qnID);
}

function isEmpty(objID) {
    return $.trim($('#'+objID).val()).length;
}
