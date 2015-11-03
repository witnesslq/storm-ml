jQuery.fn.passwordStrength = function() {
    $(this).on('keyup', function() {
        var pass = $.trim($(this).val());
        var numericTest = /[0-9]/;
        var lowerCaseAlphaTest = /[a-z]/;
        var upperCaseAlphaTest = /[A-Z]/;
        var symbolsTest = /[.,!@#$%^&*()}{:<>|]/;
        var score = 0;

        if (numericTest.test(pass)) {
            score++;
        }
        if (lowerCaseAlphaTest.test(pass)) {
            score++;
        }
        if (upperCaseAlphaTest.test(pass)) {
            score++;
        }
        if (symbolsTest.test(pass)) {
            score++;
        }
        
        if (pass.length == 0 || pass=='' || pass==null) {
        	$("#pwdPower").hide(); 
        }else{
           var s_level=score;
           switch(true){           
           		case s_level==1 :
           			$("#pwdPower").show(); 
           			$("#pwdPower").attr({"class":"lenbox lenL"});
                 break; 
                 
                case s_level==2 :
                	$("#pwdPower").show(); 
       				$("#pwdPower").attr({"class":"lenbox lenM"});		    
           	      break;
           	      
                case s_level==3 :
                	$("#pwdPower").show(); 
       				$("#pwdPower").attr({"class":"lenbox lenH"});		    
           	      break;  
           	      
                case s_level==4 :
                	$("#pwdPower").show(); 
                	$("#pwdPower").attr({"class":"lenbox lenH"});		    
                break;  
                
           	     default :
           	    	 $("#pwdPower").attr({"style":"display:none"});   
           }        
        }
    });
};