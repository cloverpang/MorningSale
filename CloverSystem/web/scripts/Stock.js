function NewDocType(inputType) {
    if (inputType == "NAv") {
        return "Price Change";
    } else {
        return "Research Report";
    }
}

function NewMovementArrow(inputType) {
	if (inputType == "Upgrade" || inputType == "Up") {
        return "img_arrowUP";
       } else if (inputType == "Downgrade" || inputType == "Down") {
        return "img_arrowDOWN";
    } else {
        return "img_arrowUnchanged";
    }
}

function updateDocType(inputType) {    
    if (inputType == "research") {
        return "Research Report";
    }

    else if (inputType == "digestAsx300") {
        return "Announcement";
    } else if (inputType == "pricechange") {
        return "Price Change";
    } else {
        return "Undefined";
    }
}

function updateSiteSection(inputType) {    
    return null;
}


function updateMovementArrow(inputType) {
	if (inputType == "Upgrade" || inputType == "Up") {
        return "img_arrowUP";
    } else if (inputType == "Downgrade" || inputType == "Down") {
        return "img_arrowDOWN";
    } else {
        return "img_arrowUnchanged";
    }
}

function updateFileDateFormat(inputType) {
	var dateWrittenOrder = inputType;
	//inputtype must be yyyyMMdd
	return dateWrittenOrder.substring(0, 4) + '-' + dateWrittenOrder.substring(4, 6) + '-' + dateWrittenOrder.substring(6, 8);
}

function displayFullSignalG(feedId) {
    var divName = '#body_sng_' + feedId;
    var divName2 = '#body_sng_' + feedId + '_2';
    var targetlink = "/Stock/GetAnnouncement?docId=" + feedId;
    $(divName).html('<img src="/Content/Images/ajax-loader.gif" border="0"/>&nbsp;&nbsp;');
    $.post(targetlink,
		    		function(data) {
		    		    if (typeof data == 'string') {
		    		        data = $.parseJSON(data);
		    		    }
		    		    if (data.root.signalgannouncement.Body != undefined) {
		    		        $(divName).hide('fast');
		    		        var content = data.root.signalgannouncement.Body;
		    		        var targetPDF = "/Stock/AnnouncementDoc/" + data.root.signalgannouncement.RelDate + "/" + reformatFileId(data.root.signalgannouncement.FileId);
		    		        content += '<span class="T_pdf">&nbsp;<a href="#' + divName2 + '" onclick=showHide(\'' + divName + '\',\'' + divName2 + '\')>Hide</a></span>';
		    		        content += '<div class="R"><div class="block T_pdf"><a href="' + targetPDF + '" target="_blank" alt="Original PDF">Announcement</a></div><div class="img_pdf"></div></div>';
		    		        $(divName).html('<span class="T_more"><a href="#' + divName + '" onclick=showHide(\'' + divName2 + '\',\'' + divName + '\')>Summary</a></span>');
		    		        $(divName2).html(content);
		    		        $(divName2).show('fast');
		    		    }
		    		}
		    	);

}

function displayResearchEvent(asxcode, eventId) {
    var divName = '#body_research_' + eventId;
    var divName2 = '#body_research_' + eventId+'_2';
    var targetlink = "/Stock/GetResearchEventDetails/" + asxcode + "/" + eventId;
    $(divName).html('&nbsp;&nbsp;<img src="/Content/Images/ajax-loader.gif" border="0"/>');
    $.post(targetlink,
		    		function(data) {

		    		    if (typeof data == 'string') {
		    		        data = $.parseJSON(data);
		    		    }
		    		    var description = "There is no description for this event.";

		    		    if (data.root.company.eventheader.eventdescription != undefined)
		    		        description = data.root.company.eventheader.eventdescription;

		    		    $(divName).hide('fast');
		    		    $(divName).html('<div class="block T_pdf Dash">/</div><span class="T_more"><a href="#' + divName + '" onclick=showHide(\'' + divName2 + '\',\'' + divName + '\')>Event</a></span>');
		    		    $(divName2).html(description + '<span class="T_pdf">&nbsp;<a href="#' + divName2 + '" onclick=showHide(\'' + divName + '\',\'' + divName2 + '\')>Hide</a></span>');
		    		    $(divName2).show('fast');
		    		}
		    	);
		  }
		  
  function showHide(show, hide) {
      $(show).show('fast');
      $(hide).hide('fast');
  }
    
  function reformatFileId(fileId) {
      var newFormat = '';
      fileId = '00000000' + fileId;
      newFormat = fileId.substr(fileId.length - 8);
      return newFormat;
  }

  function updatePriceChangeColor(pMovement) {
      if (isNaN(pMovement)) {
          return 'img_arrowUNCHANGED2';  
      } else if (pMovement > 0) {
          return 'img_arrowUP2';
      } else if (pMovement < 0) {
          return 'img_arrowDOWN2';
      } else if (pMovement == 0) {
          return 'img_arrowUNCHANGED2';
      }
  }

  function selectSectionStockOfTheWeek(inputType) {
      if (inputType == "3") {
          return "aust";
      } else if (inputType == "1") {
          return "world";
      } else {
          return "aust";
      }
  }

  // For downloading CSV file with parameters as follows
  // tableName: the name of table to export; be noted we need **thead and **tbody tag
  // exportName: name of the file to export
  // enableDownloadCheckbox: (ONLY true/false) if you have check box for downlaod at the first td element with name **download
  // prefix: content to put before export data
  // postfix: content to put after export data
  function export2csv(tableName, exportName, enableDownloadCheckbox, prefix, postfix) {
      var csv = "";
      var MMyyFormat = /^[0-1][1-9][\/{1}][0-9][0-9]$/;
      //gets column headers
      $("#" + tableName).find("thead tr td").each(function(index) {
          if (enableDownloadCheckbox == 'true' && (index == 0 || $(this).is(':hidden'))) { return true; }
          var text = $.trim($(this).text()).replace(/^\s+|\s+$/g, "").replace(/(\")/gim, "\\\"\\\"");
          if (MMyyFormat.test(text)) {
              // put '=' to force excel not to format date value  
              csv += "=\"" + text + "\"" + ",";
          } else {
            csv += "\"" + text +"\"" + ",";
          }          
      });
      csv += "\n";

      //gets content
      $("#" + tableName).find("tbody tr").each(function(indexR) {
          if (enableDownloadCheckbox == 'false' || $(this).find("input:checkbox[name='download']:first").is(':checked')) {
              $(this).find("td").each(function(index) {
              if (enableDownloadCheckbox == 'true' && index == 0) { return true; }

                  var text = $.trim($(this).text());
                  $(this).find("input:hidden").each(function() {
                      text = $(this).val();
                  })

                  //IE does not treat &nbsp; as part of \s, so strip them both  
                  text = text.replace(/^(\s| )+|(\s| )+$/g, "").replace(/(\")/gim, "\\\"\\\"")
                  if (MMyyFormat.test(text)) {
                      // put '=' to force excel not to format date value
                      csv += "=\"" + text + "\"" + ",";
                  } else {
                      csv += "\"" + text + "\"" + ",";
                  }     
              });
              csv += "\n";
          }
      });
      if ($("#exportSumbit").length == 0) {
          $("<form id='exportSumbit' action='/Base/ExportCsv' method='post'></form>").appendTo("body");
      }
      if ($("#csv_filename").length == 0) {
          $("<input type='hidden' name='csv_filename' id='csv_filename'>").appendTo("body");
      }
      if ($("#csv_text").length == 0) {
          $("<input type='hidden' name='csv_text' id='csv_text'>").appendTo("body");
      }
      $("#csv_filename").val(exportName).appendTo($("#exportSumbit"));
      $("#csv_text").val(prefix + csv + postfix).appendTo($("#exportSumbit"));
      $("#exportSumbit").submit();
  }

  function updateMorningstarResearch(inputType) {
  	return (inputType.toLowerCase() == 'y') ? true : false;
}

function CheckExtension(inputType) {
    var ext = inputType.split('.').pop();
    if (ext == 'xls' || ext == 'csv') {
        return 'excel'
    } else if (ext == 'pdf') {
        return 'pdf'
    } else { 
        return ''
    }
}
  
  