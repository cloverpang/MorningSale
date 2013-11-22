(function($){
  $.formatDate = function(date,pattern) {
    var result = [];
    while (pattern.length>0) {
      $.formatDate.patternParts.lastIndex = 0;
      var matched = $.formatDate.patternParts.exec(pattern);
      if (matched) {
        result.push($.formatDate.patternValue[matched[0]].call(this,date));
        pattern = pattern.slice(matched[0].length);
      }
      else {
        result.push(pattern.charAt(0));
        pattern = pattern.slice(1);
      }
    }
    return result.join('');
  };

  $.formatDate.patternParts =
    /^(yy(yy)?|M(M(M(M)?)?)?|d(d)?|EEE(E)?|a|H(H)?|h(h)?|m(m)?|s(s)?|S)/;

  $.formatDate.monthNames = [
    'January','February','March','April','May','June','July',
    'August','September','October','November','December'
  ];

  $.formatDate.dayNames = [
    'Sunday','Monday','Tuesday','Wednesday','Thursday','Friday',
    'Saturday'
  ];

  $.formatDate.patternValue = {
    yy: function(date) {
      return $.toFixedWidth(date.getFullYear(),2);
    },
    yyyy: function(date) {
      return date.getFullYear().toString();
    },
    MMMM: function(date) {
      return $.formatDate.monthNames[date.getMonth()];
    },
    MMM: function(date) {
      return $.formatDate.monthNames[date.getMonth()].substr(0,3);
    },
    MM: function(date) {
      return $.toFixedWidth(date.getMonth()+1,2);
    },
    M: function(date) {
      return date.getMonth()+1;
    },
    dd: function(date) {
      return $.toFixedWidth(date.getDate(),2);
    },
    d: function(date) {
      return date.getDate();
    },
    EEEE: function(date) {
      return $.formatDate.dayNames[date.getDay()];
    },
    EEE: function(date) {
      return $.formatDate.dayNames[date.getDay()].substr(0,3);
    },
    HH: function(date) {
      return $.toFixedWidth(date.getHours(),2);
    },
    H: function(date) {
      return date.getHours();
    },
    hh: function(date) {
      var hours = date.getHours();
      return $.toFixedWidth(hours>12 ? hours - 12 : hours,2);
    },
    h: function(date) {
      return date.getHours()%12;
    },
    mm: function(date) {
      return $.toFixedWidth(date.getMinutes(),2);
    },
    m: function(date) {
      return date.getMinutes();
    },
    ss: function(date) {
      return $.toFixedWidth(date.getSeconds(),2);
    },
    s: function(date) {
      return date.getSeconds();
    },
    S: function(date) {
      return $.toFixedWidth(date.getMilliseconds(),3);
    },
    a: function(date) {
      return date.getHours() < 12 ? 'AM' : 'PM';
    }
  };

  $.toFixedWidth = function(value,length,fill) {
    if (!fill) fill = '0';
    var result = value.toString();
    var padding = length - result.length;
    if (padding < 0) {
      result = result.substr(-padding);
    }
    else {
      for (var n = 0; n < padding; n++) result = fill + result;
    }
    return result;
  };

})(jQuery);

Date.prototype.toISO8601String = function (format, offset) {
    /* accepted values for the format [1-6]:
     1 Year:
       YYYY (eg 1997)
     2 Year and month:
       YYYY-MM (eg 1997-07)
     3 Complete date:
       YYYY-MM-DD (eg 1997-07-16)
     4 Complete date plus hours and minutes:
       YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
     5 Complete date plus hours, minutes and seconds:
       YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
     6 Complete date plus hours, minutes, seconds and a decimal
       fraction of a second
       YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)
    */
    if (!format) { var format = 6; }
    if (!offset) {
        var offset = 'Z';
        var date = this;
    } else {
        var d = offset.match(/([-+])([0-9]{2}):([0-9]{2})/);
        var offsetnum = (Number(d[2]) * 60) + Number(d[3]);
        offsetnum *= ((d[1] == '-') ? -1 : 1);
        var date = new Date(Number(Number(this) + (offsetnum * 60000)));
    }

    var zeropad = function (num) { return ((num < 10) ? '0' : '') + num; }

    var str = "";
    str += date.getUTCFullYear();
    if (format > 1) { str += "-" + zeropad(date.getUTCMonth() + 1); }
    if (format > 2) { str += "-" + zeropad(date.getUTCDate()); }
    if (format > 3) {
        str += "T" + zeropad(date.getUTCHours()) +
               ":" + zeropad(date.getUTCMinutes());
    }
    if (format > 5) {
        var secs = Number(date.getUTCSeconds() + "." +
                   ((date.getUTCMilliseconds() < 100) ? '0' : '') +
                   zeropad(date.getUTCMilliseconds()));
        str += ":" + zeropad(secs);
    } else if (format > 4) { str += ":" + zeropad(date.getUTCSeconds()); }

    if (format > 3) { str += offset; }
    return str;
}

Date.prototype.setISO8601 = function(dString){  
   
var regexp = /(\d\d\d\d)(-)?(\d\d)(-)?(\d\d)(T)?(\d\d)(:)?(\d\d)(:)?(\d\d)(\.\d+)?(Z|([+-])(\d\d)(:)?(\d\d))/;  
   
if (dString.toString().match(new RegExp(regexp))) {  
var d = dString.match(new RegExp(regexp));  
var offset = 0;     
this.setUTCDate(1);  
this.setUTCFullYear(parseInt(d[1],10));  
this.setUTCMonth(parseInt(d[3],10) - 1);  
this.setUTCDate(parseInt(d[5],10));  
this.setUTCHours(parseInt(d[7],10));  
this.setUTCMinutes(parseInt(d[9],10));  
this.setUTCSeconds(parseInt(d[11],10));  
if (d[12])  
this.setUTCMilliseconds(parseFloat(d[12]) * 1000);  
else  
this.setUTCMilliseconds(0);  
if (d[13] != 'Z') {  
offset = (d[15] * 60) + parseInt(d[17],10);  
offset *= ((d[14] == '-') ? -1 : 1);  
this.setTime(this.getTime() - offset * 60 * 1000);  
}  
 }  
else {  
this.setTime(Date.parse(dString));  
}  
return this;  
};  