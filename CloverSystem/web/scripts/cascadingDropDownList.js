function bindDropDownList(e, targetDropDownList) {
   // debugger;
    var key = this.value;
    var allOptions = targetDropDownList.allOptions;
    var option;
    var newOption;
    if (allOptions != null) {   
    targetDropDownList.options.length = 0;
    if (key == null) key = e.value;
        for (var i = 0; i < allOptions.length; i++) {
            option = allOptions[i];
            if (option.key == key) {
                newOption = new Option(option.text, option.value);
                //if (option.selected) newOption.selected = true;
                targetDropDownList.options.add(newOption);
            }
        }
    }
    testcal();
}

function testcal()
 {

     $(function() {
         $("#Date").datepicker({
             dateFormat: 'dd/mm/yy'
         });
     });
   

}