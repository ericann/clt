
var platformInfo = (function() {
	var userAgent = navigator.userAgent,
	isMobile = /Android|webOS|iPhone|iPod|BlackBerry/i.test(userAgent) ? true : false,
	isWechat = /MicroMessenger/i.test(userAgent) ? true : false;

	return {
		isMobile: isMobile,
		isWechat: isWechat
	}
})();

if(platformInfo.isMobile) {
	var meta = document.createElement("meta");
	meta.name = "viewport";
	meta.content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0";
	document.head.appendChild(meta);
}
