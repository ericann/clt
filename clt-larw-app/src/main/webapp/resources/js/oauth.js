window.clt = window.clt || {};

clt.default = {
	popWindow: null,
	interval: null,
    type: ["webserver", "useragent", "usernamepassword"],
    clear: null,
    error: "Unknown Error.",
    httpsAlert: "You should input \"https\" not \"http\" under browser address to use this service.",
    url: window.location.protocol + "//" + window.location.host + "/clt",
    orgType: null,
    webserver: {
        buttons: ["Start"],
        fields: [{
            label: "client_id",
            type: "text"
        }, {
            label: "client_secret",
            type: "text"
        }, {
            label: "redirect_uri",
            type: "text",
            error: "You must agree it with notices."
        }]
    },

    useragent: {
        buttons: ["Start"],
        fields: [{
            label: "client_id",
            type: "text"
        }, {
            label: "redirect_uri",
            type: "text",
            error: "You must agree it with notices."
        }]
    },
    
    usernamepassword: {
        buttons: ["Start"],
        fields: [{
            label: "client_id",
            type: "text"
        }, {
            label: "client_secret",
            type: "text",
            error: "You must agree it with notices."
        }, {
            label: "username",
            type: "text"
        }, {
            label: "password",
            type: "text"
        }, {
            label: "secritytoken",
            type: "text"
        }]
    },
    
    currentStep: -1
};

clt.data = {
    username: null,
    password: null,
    secritytoken: null,
    client_id: null,
    client_secret: null,
    redirect_uri: null,
    flow: null,
    pre_domain: null
};

clt.template = {
    
    createInput: function(field, text) {
        var html = '<input type="{0}" {1} name="{2}" required="true" id="{3}" {5}="{4}">';
        
        html = html.replace("{0}", field.type);
        
        html = html.replace("{1}", field.readonly ? 'readonly="readonly"' : "");
        
        html = html.replace("{2}", field.label.replace(" ", "").toLowerCase());

        html = html.replace("{3}", field.label);

        html = html.replace("{4}", text);

        if(field.type == "checkbox" && text) {
            html = html.replace("{5}", "checked");
        } else {
            html = html.replace("{5}", "value");
        }

        return html;
    },

    createDIV: function(label, text) {
        var html = '<div name="{0}" id="{1}">{2}</div>';

        //Replace name
        html = html.replace("{0}", label.replace(" ", "").toLowerCase());
        //Replace id
        html = html.replace("{1}", label);
        //Replace id
        html = html.replace("{2}", text);

        return html;
    },
        
    createContent: function(field) {
        var html = '<div class="field">' +
                        '<div class="f_label">' +
                            '<label>{0}</label>' +
                        '</div><div class="f_content" >' +
                            '{1}' +
                            '<a href="{2}" target="_blank">help</a>' + 
                        '</div>' +
                        '<div class="helptext">{3}</div>' +
                    '</div>',
            name = field.label.replace(" ", "").toLowerCase();
            text = clt.data[name] || field.default || "",
            help = field.help || "",
            content = "";

        switch(field.type) {

            case "div":
                content = this.createDIV(field.label, field.default);
                break;
            default:
                content = this.createInput(field, text);
        }

        html = html.replace("{0}", field.label);

        html = html.replace("{1}", content);

        if(field.link) {
            html = html.replace("{2}", field.link);
        } else {
            html = html.replace('<a href="{2}" target="_blank">help</a>', "");
        }

        html = html.replace("{3}", help);
        
        return html;
    },

    createButton: function(label) {
        var html = '<div class="f_button" id={0}>{1}</div>';
        return html.replace("{0}", label.toLowerCase()).replace("{1}", label);
    },

    createSection: function(step) {
        //console.log(clt.default[step]);
        var stepNode = clt.default[step];
        var html = '<div class="section">' +
                        '<div class="error_title"></div>' +
                        '<div class="title">{0}</div>' + 
                        '{1}' +
                        '<div class="f_action">{2}</div>' + 
                    '</div>';
        html = html.replace("{0}", stepNode.label || "");
        var content = '';
        var buttons = '';
        var readonly = (clt.default.currentStep == 2 ? 'readonly="readonly"' : '');

        for(var i = 0; i < stepNode.fields.length; i++) {
            //var infor = stepNode.fields[i].link || stepNode.fields[i].help;

            var field = stepNode.fields[i];
            content += this.createContent(field);
        }

        for(var i = 0; i < stepNode.buttons.length; i++) {
            buttons += this.createButton(stepNode.buttons[i]);
        }
        
        //return html.replace("{1}", content).replace("{2}", buttons);
        html = html.replace("{1}", content).replace("{2}", buttons);
        return clt.action.parseToDom(html);
    },
    
    createResultSection: function(data) {
    	data = JSON.parse(data);
        var html = '<div class="section">' +
                        '<div class="error_title"></div>' +
                        '<div class="title">{0}</div>' + 
                        '{1}' +
                        '<div class="f_action">{2}</div>' + 
                    '</div>';
        html = html.replace("{0}", "Result");
        var content = '';
        var buttons = '';
        var readonly = (clt.default.currentStep == 2 ? 'readonly="readonly"' : '');
        var keys = Object.keys(data);
        for(var i = 0; i < keys.length; i++) {
            var field = {
            		label: keys[i],
            		default: data[keys[i]],
            		type: "div"
            };
            content += this.createContent(field);
        }

        html = html.replace("{1}", content).replace("{2}", buttons);
        return clt.action.parseToDom(html);
    }
    
};

clt.action = {

    selectChange: function() {
        var select = document.getElementById("type");
        if(select) {
        	clt.data.flow = select.value;
        	 
            select.addEventListener("change", function(e) {
            	e = e.currentTarget;
                clt.data.flow = e.value;
                clt.action.resetCurrentStep();
            }, false);
        }
        
        var orgSelect = document.getElementById("org");
        if(orgSelect) {
            clt.default.orgType = orgSelect.value;
        
            orgSelect.addEventListener("change", function(e) {
                e = e.currentTarget;
                clt.data.pre_domain = e.value;
            }, false);
        }
        
        var clear = document.getElementById("clear");
        if(clear) {
        	clt.default.clear = clear.checked;
        	
        	clear.addEventListener("change", function(e) {
                e = e.currentTarget;
                clt.default.clear = e.checked;
            }, false);
        }
    },

    resetCurrentStep: function() {
        var select = document.getElementById("type");
        if(select) {
            clt.default.currentStep = parseInt(select.value);
            clt.action.changeSection();
        }
    },

    doCall: function(url, data, success, error) {
        var obj = {
                url: url,
                data: data,
                success: success,
                error: error
        };
        Ajax.post(obj);
    },
    
    save: function() {
        var step = clt.default.type[clt.default.currentStep],
            fields = clt.default[step].fields;
            
        for(var i = 0; i < fields.length; i++) {
            var f = fields[i],
                d = document.getElementById(f.label);
            clt.data[f.label] = d.value;
        }
    },

    start: function() {
        clt.action.save();

        var obj = {
            url: clt.default.url + "/sfdc/transfer",
            data: JSON.stringify(clt.data),
            success: success,
            error: error
        }; 
        
        Ajax.post(obj);
        clt.action.loading();
        
        //for test
        //success("{\"errCode\":\"0\",\"errMsg\":\"success\",\"data\":\"http://localhost:8080/test/pages/register.html?code=aPrxshT49BthlzK4hSxVxdOoLwofyAyRI0wRotM2BTeCicAbUDaUS5.F3gCumhJK.pALQd24nw%3D%3D\"}");
        //success("{\"errCode\":\"0\",\"errMsg\":\"success\",\"data\":\"http://localhost:8080/test/pages/register.html#access_token=00D7F000000refy%21AQcAQKeMJw7VFP49zfJTohk5nwrjZwzPq0XE2f4VX5GOPomSt0M2OFhOPZ.qr1a2Z_6yubey9ST2KW97kYGbQ8QmkynQt0Rp&instance_url=https%3A%2F%2Fap5.salesforce.com&id=https%3A%2F%2Flogin.salesforce.com%2Fid%2F00D7F000000refyUAA%2F0057F000000ZGu0QAG&issued_at=1497841367151&signature=YSKiv9NR%2Fh3oEV9SJK8%2By7%2Bj4YH8H3FQ9Tw2DAIHyT0%3D&scope=id+api+web+full+chatter_api+visualforce+openid+custom_permissions+wave_api+eclair_api&token_type=Bearer\"}");

        function success(result) {
            //console.debug("Ajax post success");
            //console.debug(result);
            
            result = JSON.parse(result);
            if(parseInt(result.errCode) === 0) {
            	
            	switch(clt.data.flow) {
            		case "0":
            			clt.default.popWindow = window.open(result.data);
                        var flow = clt.data.flow;
                        clt.default.interval = setInterval("clt.action.closeWindow(" + flow + ",'" + clt.data.pre_domain + "')",50);
            			break;
            		case "1":
            			clt.default.popWindow = window.open(result.data);
            			var flow = clt.data.flow;
                        clt.default.interval = setInterval("clt.action.closeWindow(" + flow + ")",50);
            			break;
            		case "2":
            			var d = clt.template.createResultSection(result.data);
            			document.body.appendChild(d);
            			clt.action.clearDomById("loading");
            			//console.debug(result.data);
            			break;
            		default:
            			clt.action.clearDomById("loading");
            			//console.debug(result.data);
            			//break;
            	}
            } else {
            	var error = document.getElementsByClassName("error_title");
	            error[0].innerHTML = result.errMsg ? result.errMsg : clt.default.error;
            	clt.action.clearDomById("loading");
            }
        }

        function error(result) {
            clt.action.clearDomById("loading");
            var error = document.getElementsByClassName("error_title");
            error[0].innerHTML = result.errMsg ? result.errMsg : clt.default.error;
        }
    },
    
    closeWindow: function(flow, pre_domain) {
    	
    	try {  
    		console.debug("location: " + clt.default.popWindow.location.href);
    		var data = clt.default.popWindow.location.href;
    		
    		if(data === "about:blank") {
    			return;
    		}
    		
    		clt.default.popWindow.close();
			window.clearInterval(clt.default.interval);
			
    		if(flow == "0") {
    			var code = data.split("=")[1];
    			var req_data = {
    				code: code,
    				redirect_uri: clt.data.redirect_uri,
    				client_id: clt.data.client_id,
    				client_secret: clt.data.client_secret,
    				pre_domain: pre_domain
    			}
    			var obj = {
    		            url: clt.default.url + "/sfdc/oauth/ws",
    		            data: JSON.stringify(req_data),
    		            success: success,
    		            error: error
		        }; 
		        Ajax.post(obj);

		        function success(result) {
		            console.debug("Ajax post success");
		            console.debug(result);
		            result = JSON.parse(result);
		            if(parseInt(result.errCode) === 0) {
		            	clt.action.setResultSection(result.data);
		            	clt.action.clearDomById("loading");
		            } else {
		            	var error = document.getElementsByClassName("error_title");
		            	error[0].innerHTML = result.errMsg ? result.errMsg : clt.default.error;
		            }
		            clt.action.clearDomById("loading");
		        }

		        function error(result) {
		            clt.action.clearDomById("loading");
		            var error = document.getElementsByClassName("error_title");
		            error[0].innerHTML = result.errMsg ? result.errMsg : clt.default.error;
		        }
    		} else {
    			var fields = clt.action.getFields_UA(data);
    			clt.action.setResultSection(fields);
    			clt.action.clearDomById("loading");
    		}
    	} catch (e) {    
    	
    	} 
    },
    
    getFields_UA(data) {
    	var fields = {};
		var originS = data.split("#")[1];
		var arr = originS.split("&");
		for(var i = 0; i < arr.length; i++) {
			
			var o = arr[i].split("=");
			var label = o.shift();
			var value = null;
			if(o.length > 2) {
				value = o.join("=");
			} else {
				value = o.shift();
			}
			fields[label] = decodeURIComponent(value);
		}
		
		return fields;
    },
    
    setResultSection(json) {
    	if(typeof json != "string") {
    		json = JSON.stringify(json);
    	}
    	
    	var d = clt.template.createResultSection(json);
		document.body.appendChild(d);
    },
    
    reset: function() {
        for(var f in clt.data) {
        	if(f == "flow") continue;
            clt.data[f] = null;
        }
    },
    
    setData: function(currentStep) {
    	//clt.data.url = clt.default.url.replace("{0}", clt.default.orgType) + clt.default[currentStep].url;
    	clt.data.pre_domain = clt.default.orgType;
    },
    
    loading: function() {
        //var html = '<div class="loading" id="loading"></div>';
        var d = document.createElement("div");
        d.classList.add("loading");
        d.id = "loading";
        //var newdom = clt.action.parseToDom(html);
        document.body.appendChild(d);
    },
    
    clearDomById: function(id) {
        var d = document.getElementById(id);
        if(d) {
            d.outerHTML = "";
        }
    },
    
    parseToDom: function(domStr) {
        var d = document.createElement("div");
        d.innerHTML = domStr;
        return d.childNodes[0];
    },

    removeSection: function() {
        var sections = document.getElementsByClassName("section");
        for(var i = sections.length - 1; i >= 0; i--) {
            document.body.removeChild(sections[i]);
        }
        //document.body.removeChild(sections[sections.length - 1]);
        //document.body.innerHTML = document.body.innerHTML.replace(document.body.lastElementChild.outerHTML, "");
    },

    changeSection: function() {
        clt.action.removeSection();
        //可扩展功能，tab切换清除数据
        if(clt.default.clear) {
        	clt.action.reset();
        }
        
        var currentStep = clt.default.type[clt.default.currentStep];
        var html = clt.template.createSection(currentStep);
        
        //set Data
        clt.action.setData(currentStep);
        //document.body.innerHTML += html;
        document.body.appendChild(html);
        
        if(clt.default[currentStep].buttons) {
            var buttons = clt.default[currentStep].buttons;

            for(var i = 0; i < buttons.length; i++) {
                var b = document.getElementById(buttons[i].toLowerCase());

                if(b) {
                    b.addEventListener("click", clt.action[buttons[i].toLowerCase()], false);
                    //b.nextSibling.addEventListener("mouseover", function(){alert("msg")}, false);
                }
            }
        }
    },
    
    checkHttps: function() {
    	if(window.location.protocol !== "https:") {
    		//alert(clt.default.httpsAlert);
    		window.location.protocol = "https:";
    	}
    }
};

clt.init = function() {
    clt.action.selectChange();
    //clt.action.checkHttps();
}