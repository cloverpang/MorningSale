$(function() {
    /**
    * Define the module:
    */

    module("ARC Template Tests");


    /**
    * Create ArcTemplateItem, verify created:
    */
    test("ArcTemplateItem", function() {
        expect(1);
		
        var opt = {
            Name: 'Materials Index'
			};
			
        obj = new ArcTemplateItem(opt);
        equals(typeof obj, "object", "Verifying existence of ArcTemplateItem object");
 
    });




    /**
    * Create ArcTemplateItem, Set some params, check returned object:
    */
    test("ArcTemplateItem Param Test", function() {
        expect(4);
		
        var opt = {
				Name: 'Materials Index',
				Section:'DL',
				Tbl:'#test'
			};
			
        obj = new ArcTemplateItem(opt);
        equals(typeof obj, "object", "Verifying existence of ArcTemplateItem object");
		same(obj.opts.Name, "Materials Index", "Test Passes objects have same content");
		same(obj.opts.Section, "DL", "Test Passes objects have same content");
		same(obj.opts.Tbl, "#test", "Test Passes objects have same content");
    });



	/**
    * Create ArcTemplateItem, Test Methods:
    */
	 test("ArcTemplateItem SetRowRount Method Test", function() {
        expect(2);
		
        var opt = {
				Name: 'Materials Index',
				Section:'DL',
				Tbl:'#test'
			};
			
        obj = new ArcTemplateItem(opt);
        equals(typeof obj, "object", "Verifying existence of ArcTemplateItem object");
		
		// call row count method
		obj.SetRowRount(100);
		// check internal value
		same(obj.opts.TotalRows, 100, "SetRowRount Test Passes objects have same content");
		
		
		
	
    });



/**
    * Create ArcTemplateItem, Test Methods:
    */
	 test("ArcTemplateItem getContextValue Method Test", function() {
        expect(2);
		
        var opt = {
				Name: 'Materials Index',
				Section:'DL',
				Tbl:'#test'
			};
			
        obj = new ArcTemplateItem(opt);
        equals(typeof obj, "object", "Verifying existence of ArcTemplateItem object");
		
		
		
		// test that internal getContextValue function returns correct values
		same(obj.getContextValue("opts.Name",obj),"Materials Index","getContextValue Test Passes");
		
	
    });


	
	
	/**
    * Create ArcTemplateItem, Test Methods:
    */
	 test("ArcTemplateItem UpdateOptions Method Test", function() {
        expect(3);
		
        var opt = {
				Name: 'Materials Index',
				Section:'DL',
				Tbl:'#test'
			};
			
        obj = new ArcTemplateItem(opt);
        equals(typeof obj, "object", "Verifying existence of ArcTemplateItem object");
		
		
		
		// test value of object
		same(obj.getContextValue("opts.Name",obj),"Materials Index","getContextValue Test Passes");
		
		
		var newopts={
			Name:'New Name value'
		}
		
		obj.UpdateOptions(newopts);
		
		// test that internal value is updated 
		same(obj.getContextValue("opts.Name",obj),"New Name value","UpdateOptions Test Passes");
		
	
    });
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	





});