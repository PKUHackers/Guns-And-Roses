

/**
 * User Login
 */
function login(){
	var userName = document.getElementsByName("user_name_login")[0].value;
	var passWord = document.getElementsByName("passwd_login")[0].value;
	if(userName == ""){
		alert("ID should not be null !");
		return false;
	}
	if(passWord == ""){
		alert("Password should not be null !");
		return false;
	}
	var res = "ej_qe_eu_ee_er";
	for(var i = 0; i < passWord.length; i++){
		if(i%2 == 0){
			res = "e" + passWord[i] + "_" + res;
		}
		else{
			res = passWord[i] + "e_" + res;
		}
	}
	res = "ea_qs_fd_kf_bg_" + res;
	var url = "/NRS/EnglishAction.do?method=Login&lucy=" + userName + "&lily=" + res;
	window.location.href=url;
}

/**
 * User Login
 */
function onLoginKeyUp(){
	if(event.keyCode == 13){
		login();
	}
}

/**
 * User Register
 */
function registe(){
	var email = document.getElementsByName("email")[0].value;
	var userName = document.getElementsByName("user_name")[0].value;
	var passWord1 = document.getElementsByName("passwd")[0].value;
	var passWord2 = document.getElementsByName("repasswd")[0].value;
	if(userName == ""){
		alert("ID should not be null !");
		return false;
	}
	if(email == ""){
		alert("Email should not be null !");
		return false;
	}
	if(passWord1 == ""){
		alert("Password should not be null !");
		return false;
	}
	if(passWord2 == ""){
		alert("Confirm you password!");
		return false;
	}
	if(passWord1 != passWord2){
		alert("Password do not match !");
		return false;
	}
	
	var res = "ej_qe_eu_ee_er";
	for(var i = 0; i < passWord1.length; i++){
		if(i%2 == 0){
			res = "e" + passWord1[i] + "_" + res;
		}
		else{
			res = passWord1[i] + "e_" + res;
		}
	}
	res = "ea_qs_fd_kf_bg_" + res;
	var url = "/NRS/EnglishAction.do?method=Registe&lucy=" + userName + "&lily=" + res + "&jim=" + email;
	window.location.href=url;
}

/**
 * User Registe
 */
function onRegisteKeyUp(){
	if(event.keyCode == 13){
		registe();
	}
}

/**
 * Search
 */
function search(){
	var keyWords = document.getElementsByName("s")[0].value;
	if(keyWords == ""){
		alert("Keyword should not be null !");
		return false;
	}
	var encoded = Url.encode(keyWords);
	var str = "";
	for(var i = 0; i < encoded.length; i++){
		if(encoded[i] == '%'){
			str += "_";
		}
		else{
			str += encoded[i];
		}
	}
	var url = "/NRS/EnglishAction.do?method=Search&keyWords=" + str;
	window.location.href=url;
}

/**
 * concept similarity
 */
function calculate(){
	var concept1 = document.getElementsByName("concept1")[0].value;
	if(concept1 == ""){
		alert("Concept 1 should not be null!");
		return false;
	}
	
	var concept2 = document.getElementsByName("concept2")[0].value;
	if(concept2 == ""){
		alert("concept 2 should not be null!");
		return false;
	}
	
	var encoded1 = Url.encode(concept1);
	var str1 = "";
	for(var i = 0; i < encoded1.length; i++){
		if(encoded1[i] == '%'){
			str1 += "_";
		}
		else{
			str1 += encoded1[i];
		}
	}
	
	var encoded2 = Url.encode(concept2);
	var str2 = "";
	for(var i = 0; i < encoded2.length; i++){
		if(encoded2[i] == '%'){
			str2 += "_";
		}
		else{
			str2 += encoded2[i];
		}
	}
	
	var url = "/NRS/EnglishAction.do?method=ConceptSimilarity&concept1=" + str1 + "&concept2=" + str2;
	window.location.href=url;
}

/**
 * Click Key word
 */
function keyWordSearch(keyWords){
	var encoded = Url.encode(keyWords);
	var str = "";
	for(var i = 0; i < encoded.length; i++){
		if(encoded[i] == '%'){
			str += "_";
		}
		else{
			str += encoded[i];
		}
	}
	var url = "/NRS/EnglishAction.do?method=Search&keyWords=" + str;
	window.location.href=url;
}

/**
 * mark
 * @param divId
 */
function judgeMark(divId){
	
	var div = document.getElementById(divId);
	var str = div.innerHTML;
	var selected = "<a href=\"javascript:judgeMark(\'" + divId + "\')\" class=\"post-view\">1</a>";
	var non_selected = "<a href=\"javascript:judgeMark(\'" + divId + "\')\" class=\"post-view\">0</a>";
	
	if(str == selected){
		div.innerHTML = non_selected;
	}
	else{
		div.innerHTML = selected;
	}
}

/**
 * evaluation
 * @param newsCnt
 */
function evaluation(newsCnt){
	alert("Thank you for your participation !");
	var i = 0;
	var str = "";
	var input, value, selected, non_selected;
	for(i = 0; i < newsCnt; i++){
		input = document.getElementsByName("rateMark" + i)[0].value;
		value = document.getElementById("rateDiv" + i).innerHTML;
		selected = "<a href=\"javascript:judgeMark(\'" + "rateDiv" + i + "\')\" class=\"post-view\">1</a>";
		non_selected = "<a href=\"javascript:judgeMark(\'" + "rateDiv" + i + "\')\" class=\"post-view\">0</a>";
		
		if(str != ""){
			str += ";";
		}
		if(value == selected){
			str += "1";
		}
		else{
			str += "0";
		}
	}
	
	var url = "/NRS/EnglishAction.do?method=SendJudge&result=" + str;
	window.location.href=url;
}

/**
 * dataMarking
 * @param newsCnt
 */
function dataMarking(newsCnt){
	alert("Thank you for your participation !");
	var i = 0;
	var str = "";
	var mark;
	for(i = 0; i < newsCnt; ++i){
		mark = document.getElementsByName("newsSelect" + i)[0].value;
		
		if(str != ""){
			str += ";";
		}
		str += mark;
	}
	
	var redirect = "/NRS/EnglishAction.do?method=SendDataMarking&result=" + str;
	window.location.href=redirect;
}

/**
 * Show Category
 */
function showCategory(category){
	var encoded = Url.encode(category);
	var str = "";
	for(var i = 0; i < encoded.length; i++){
		if(encoded[i] == '%'){
			str += "_";
		}
		else{
			str += encoded[i];
		}
	}
	var url = "/NRS/EnglishAction.do?method=ShowCategory&category=" + str;
	window.location.href=url;
}


/**
 * Send Comment
 * @param userId
 * @param spanId
 * @returns {Boolean}
 */
function sendComment(newsId, userId){
	if(userId == null || userId == ""){
		alert("Login first !");
		return false;
	}
	
	var comment = document.getElementsByName("comment")[0].value;
	if(comment == ""){
		alert("Comment should not be null !");
		return false;
	}
	var encoded = Url.encode(comment);
	var str = "";
	for(var i = 0; i < encoded.length; i++){
		if(encoded[i] == '%'){
			str += "_";
		}
		else{
			str += encoded[i];
		}
	}
	var url = "/NRS/EnglishAction.do?method=SendComment&comment=" + str + "&id=" + newsId;
	window.location.href = url;
}

/**
 * Send Message
 * @param userId
 * @param spanId
 * @returns {Boolean}
 */
function sendMessage(userId){
	if(userId == null || userId == ""){
		alert("Login first !");
		return false;
	}
	
	var subject = document.getElementsByName("si_contact_subject")[0].value;
	var message = document.getElementsByName("si_contact_message")[0].value;
	if(subject == ""){
		alert("Subject should not be null !");
		return false;
	}
	if(message == ""){
		alert("Message should not be null !");
		return false;
	}
	var encodedSub = Url.encode(subject);
	var encodedMsg = Url.encode(message);
	var str1 = "";
	for(var i = 0; i < encodedSub.length; i++){
		if(encodedSub[i] == '%'){
			str1 += "_";
		}
		else{
			str1 += encodedSub[i];
		}
	}
	var str2 = "";
	for(var i = 0; i < encodedMsg.length; i++){
		if(encodedMsg[i] == '%'){
			str2 += "_";
		}
		else{
			str2 += encodedMsg[i];
		}
	}
	var url = "/NRS/EnglishAction.do?method=SendMessage&subject=" + str1 + "&message=" + str2;
	window.location.href = url;
}

/**
 * add novelty and diversity in recommendation
 * @param algorithmName
 */
function novelty_diversity(algorithmName){
	var noveltyCheck = document.getElementsByName("noveltyCheck")[0];
	var diversityCheck = document.getElementsByName("diversityCheck")[0];
	var str = "";
    if(noveltyCheck.checked == true){
    	str += "&novelty=true";
    }
    else{
    	str += "&novelty=false";
    }
    if(diversityCheck.checked == true){
    	str += "&diversity=true";
    }
    else{
    	str += "&diversity=false";
    }
    
    // similarity name
    var simNameSelect = document.getElementsByName("simNameSelect")[0];
    var simName = "";
    if(simNameSelect != null){
    	simName = simNameSelect.value;
    }
    var url = "/NRS/EnglishAction.do?method=ShowCompare&algorithmName=" + algorithmName + str + "&simName=" + simName;
    window.location.href = url;
}


/**
 * go top
 */
function goTopEx(divId){
    var obj = document.getElementById(divId);
    function getScrollTop(){
    	return document.documentElement.scrollTop;
    }
    function setScrollTop(value){
    	document.documentElement.scrollTop = value;
    }
    window.onscroll = function(){
    	getScrollTop() > 0 ? obj.style.display = "" : obj.style.display = "none";
    };
    obj.onclick = function(){
    	setScrollTop(0);
        //var goTop = setInterval(scrollMove, 10);
        //function scrollMove(){
        //	setScrollTop(getScrollTop() / 100);
        //	if(getScrollTop() < 1)
        //		clearInterval(goTop);
       // }
    };
}


var Url = {
	// public method for url encoding
	encode : function (string) {
		return escape(this._utf8_encode(string));
	},
	// public method for url decoding
	decode : function (string) {
		return this._utf8_decode(unescape(string));
	},
	// private method for UTF-8 encoding
	_utf8_encode : function (string) {
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
		}
		return utftext;
	},
	// private method for UTF-8 decoding
	_utf8_decode : function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
};


