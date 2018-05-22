Ajax = (function() {
	this.options = {
		timeout: 30000,
		async: true,
		dataType: "text"
	};

	var BaseRequest = function(ajax) {
		var xhr = null;

		/*var error = {
			url: "Please check your url of params",
			type: "Please check your type of params"
		}*/

		var

		init = function() {
			xhr = window.XMLHttpRequest ? 
				(function() {
					return new window.XMLHttpRequest();
				})() :
				(function() {
					try {
	                    return new window.ActiveXObject("Microsoft.XMLHTTP");
	                } catch(e) {
	                	console.log("Ajax - XMLHttpRequest cannot be used with the brower.");
	                }
				})();

			if(xhr) {
				xhr.onreadystatechange = callback;
				xhr.timeout = ajax.timeout ? ajax.timeout : options.timeout;
				xhr.ontimeout = (ajax.timeout && typeof ajax.timeout === "function") ? ajax.timeout : null;
			}
		},

		execute = function() {
			xhr.open(ajax.type, 
					ajax.url, 
					ajax.async ? ajax.async : options.async);
			
			if(ajax.headers) {
				var keys = Object.keys(ajax.headers);
				for(var i = 0; i < keys.length; i++) {
					xhr.setRequestHeader(keys[i], ajax.headers[keys[i]]);
				}
			}

			if(ajax.type.toUpperCase() == "POST") {
				if(ajax.requestHeader) {
					xhr.setRequestHeader("Content-Type", ajax.requestHeader);
				} else{
					xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				}
			} else if(ajax.type.toUpperCase() == "GET") {
				ajax.data = null;
			}

			try {
				xhr.send(ajax.data);
			} catch(e) {
				console.log("Ajax send error:" + e);
			}
		},

		callback = function() {
			switch(xhr.readyState) {
				case 0:
					break;  
		    	case 1:
		    		if(ajax.beforeSend && typeof ajax.beforeSend === "function") {
		    			ajax.beforeSend();
		    		}
		    		break;
		    	case 2:
		    		break;
		    	case 3:
		    		break;
		    	case 4: 
			        var message = xhr.responseText; 
			        if(xhr.status === 200) {
			        	if(ajax.success && typeof ajax.success === "function") {
			            	ajax.success(message);  
			        	}
			        } else {
			        	if(ajax.error && typeof ajax.error === "function") {
			        		ajax.error(xhr); 
			        	}
			        }  

			        if(ajax.complete && typeof ajax.complete === "function") {
			        	ajax.complete(message); 
			        }
			        break;
			    default: break;
		    }  
		};

		return {

			getField: function(field) {
				return xhr[field];
			},

			auto: function() {
				init();
				execute();
			}
		}
	};
	
	return {
		
		/*  post、get、getJson Method的params template
			var obj = {
				url: ["http://...", "https://..."],
				dataType: ["json", "text"]
				data: data,
				success: function() {},
				error: function() {},
				async: [true, false],
				headers: [{key:"key",value:"value"},...]
			}
		*/

		post: function(ajax) {
			ajax.type = "POST";
			new BaseRequest(ajax).auto();
		},

		get: function(ajax) {
			ajax.type = "GET";
			new BaseRequest(ajax).auto();
		},

		getJson: function(ajax) {

		},

		configuration: this.options
	}
})();