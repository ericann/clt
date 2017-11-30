window.clt = window.clt || {};

clt.default = {

	base_url: window.location.protocol + "//" + window.location.host + "/clt",
	objects: {
        welcome: {
            title: "Welcome",
            buttons: []
        },
        sfdc: {
            title: "Sfdc",
            buttons: []
        },
        wechat: {
            title: "Wechat",
            buttons: []
        }   
    },

    type: ["login", "welcome", "sfdc", "wechat"],
    current: 0,
	
	field: function() {
		return {
			label: null,
			default: null,
			type: null
		}
	},
	
	object: function() {
		return {
			title: null,
			buttons: [],
			fields: []
		}
	}
};

clt.data = {};

clt.template = {
    
    createInput: function(field, text) {
        var html = '<input type="{type}" {readonly} name="{name}" required="true" id="{id}" {key}="{value}">';
        
        html = html.replace("{type}", field.type);
        
        html = html.replace("{readonly}", field.readonly ? 'readonly="readonly"' : "");
        
        html = html.replace("{name}", field.label.replace(" ", "").toLowerCase());

        html = html.replace("{id}", field.label);

        html = html.replace("{value}", text);

        if(field.type == "checkbox" && text) {
            html = html.replace("{key}", "checked");
        } else {
            html = html.replace("{key}", "value");
        }

        return html;
    },
    
    createImage: function(label, src) {
        var html = '<div name="{0}" id="{1}"><img src="{2}"></img></div>';

        //Replace name
        html = html.replace("{0}", label.replace(" ", "").toLowerCase());
        //Replace id
        html = html.replace("{1}", label);
        //Replace id
        html = html.replace("{2}", src);

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
        var html = '<div class="f_field">' +
                        '<div class="f_label">' +
                            '<label>{label}</label>' +
                        '</div>' +
                        '<div class="f_values" >' +
                            '<div class="f_value">{content}</div>' +
                            '<div class="f_link"><a href="{href}" target="_blank">help</a></div>' + 
                            '<div class="f_help">{helptext}</div>' +
                        '</div>' +
                    '</div>',
            name = field.label.replace(" ", "").toLowerCase();
            text = clt.data[name] || field.default || "",
            help = field.help || "",
            content = "";
    
        switch(field.type) {
            
            case "img":
                content = this.createImage(field.label, field.default);
                break;
            case "div":
                content = this.createDIV(field.label, field.default);
                break;
            default:
                content = this.createInput(field, text);
        }
    
        html = html.replace("{label}", field.label);
    
        html = html.replace("{content}", content);
    
        if(field.link) {
            html = html.replace("{href}", field.link);
        } else {
            html = html.replace('<a href="{href}" target="_blank">help</a>', "");
        }
    
        html = html.replace("{helptext}", help);
        
        return html;
    },
    
    createButton: function(label) {
        var html = '<div class="f_button" id={0}>{1}</div>';
        html = html.replace("{0}", label.toLowerCase()).replace("{1}", label);
        return html;
    },
    
    createSection: function(obj) {
        
        var html = '<div class="section">' +
                        '<div class="error_info"></div>' +
                        '<div class="title">{0}</div>' + 
                        '<div class="content">' + 
                            '{1}' +
                        '</div>' +
                        '<div class="f_action">{2}</div>' + 
                    '</div>';
        html = html.replace("{0}", obj.title || "");
        var content = '';
        var buttons = '';
    
        for(var i = 0; i < obj.fields.length; i++) {
            var field = obj.fields[i];
            content += this.createContent(field);
        }
    
        for(var i = 0; i < obj.buttons.length; i++) {
            buttons += this.createButton(obj.buttons[i]);
        }
        
        html = html.replace("{1}", content).replace("{2}", buttons);
        return clt.action.parseToDom(html);
    },
    
    createSections: function(obj) {
    	
    },

    createTopMenus: function(objs) {
        var html = '<div class="menu">' + 
                        '<div class="error_msg"></div>' + 
                        '<div class="t_title">{title}</div>' + 
                        '<div class="t_content">' + 
                            '{info}' +
                        '</div>' + 
                        //'<div class="t_action">{action}</div>' + 
                    '</div>';
        var fields = [];
        var keys = Object.keys(objs);
        for(var i = 0; i < keys.length; i++) {
            fields[i] = {
                label: i + "",
                default: objs[keys[i]].title,
                type: "div",
            }
        }

        html = html.replace("{title}", "Menu");
        var content = '';
    
        for(var i = 0; i < fields.length; i++) {
            var field = fields[i];
            content += this.createContent(field);
        }
        html = html.replace("{info}", content);
        return clt.action.parseToDom(html);
    }
};

clt.action = {
	
	//Single
	changeDataTest: function(data) {
		data = data.data;
		
		var obj = {},
			objKeys = Object.keys(data);
		
		for(var i = 0; i < objKeys.length; i++) {
			obj[objKeys[i]] = new clt.default.object();
			var o = obj[objKeys[i]];
			o.title = objKeys[i];
			o.buttons.push("Edit");
			
			for(var k in data[objKeys[i]]) {
				var field = new clt.default.field();
				field.label = k;
				field.type = "div";
				field.default = data[objKeys[i]][k];
				o.fields.push(field);
			}
		}
		
		return obj;
	},

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
    }
};

clt.test = function() {
	//var json = '{"data":{"button":{"buttonId":"5737F000000Xb4a","displayInfo":"There are currently no agents online, try again later pls.","bid":"ec3f3ea6-7a24-44bf-8009-0e7bd9e88b48"},"basicConfig":{"wechatAccount":"gh_ffdefb93090b","endPoint":"https://d.la1-c1-ukb.salesforceliveagent.com/chat/rest/","bcId":"beae81d8-4956-47d1-9de7-15bb8e79127d","createTime":"2017-06-05 13:33:32.0","deploymentId":"5727F000000Xcpk","appId":"wx085888a3e559f8ac","appSecret":"KnZtN9mX0TEe","orgId":"00D7F000000qSxk"},"contact":{"contactId":"8b847d45-50cd-457c-840f-a14942f7fd7b","companyName":"Eric","mobile":"15210063460","email":"yxeysy@126.com"},"account":{"accountId":"690d3fc3-a44b-4887-a21d-17f848e849bd","companyName":"Collection"}},"errCode":"0","errMsg":"success."}';
	//clt.default.objects = clt.action.changeDataTest(JSON.parse(json));
	//var body = document.body;
	var keys = Object.keys(clt.default.objects);
	for(var i = 0; i < keys.length; i++) {
		document.body.appendChild(clt.template.createSection(clt.default.objects[keys[i]]));
	}
}

clt.init = function() {
    clt.action.selectChange();
    clt.action.checkHttps();
}
