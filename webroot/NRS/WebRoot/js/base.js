//在状态栏中不显示链接

//禁止复制页面.
//	document.oncontextmenu=function(){return false};

//    document.ondragstart=function(){return false};

//    document.onselectstart =function(){return false};

//    document.onselect=function(){document.selection.empty();};

//    document.oncopy=function(){document.selection.empty();};

//    document.onbeforecopy=function(){return false};

//    document.onmouseup=function(){document.selection.empty();};

/*
function IE(e) 
//traps right-click in IE
{
     if (navigator.appName == "Microsoft Internet Explorer" && (event.button == "2" || event.button == "3"))
     {
	  	alert("不能使用右键，请不要试图复制资料！");		 
        return false;
     } 
}
function NS(e) 
//traps right-click in Netscape/firefox, et al
{
     if (document.layers || (document.getElementById && !document.all))
     {
          if (e.which == "2" || e.which == "3")
          {
			  	alert("不能使用右键，请不要试图复制资料！");
                return false;
          }
     }
}

function handlePress(e) {
//traps ctrl and alt keys
  var evtobj=window.event? event : e;
  if (evtobj.ctrlKey || evtobj.altKey) {
	  alert("不能使用此键！");
	return false;
  }
}			


document.onmousedown=IE;
document.onmouseup=NS;
document.oncontextmenu=new Function("return false");
document.onkeydown=handlePress;

//禁止复制页面.

*/

//用于时间过滤
function timeFliter()
{
	
	var startTime = document.getElementsByName("starttime")[0].value;
	var endTime =  document.getElementsByName("endtime")[0].value;
	var url = "/InfoUpdatingAction.do?method=timeFliting&starttime="+startTime+"&endtime="+endTime;
   
    MyAjax.getDataAndDisplay(url,"personIncomeList","");
}

//用于调整窗口大小
function setscreendiv()
{
    var clientHeight =768;
    var bo = $('NavManagerMenu');
    var iframe = $('main');
    if(navigator.userAgent.toLowerCase().indexOf('opera') != -1)
    {
        clientHeight = document.documentElement.clientHeight+190;
    }
    else
    { 
        clientHeight = document.documentElement.clientHeight-25;
    }
    if(navigator.userAgent.toLowerCase().indexOf('msie') != -1)
    {
        bo.style.height = clientHeight+'px';
        //alert(clientHeight);
    }
    else
    {
        bo.style.minHeight = clientHeight+'px';
    }
    
    var clientHeight =768;
      
    if(navigator.userAgent.toLowerCase().indexOf('opera') != -1)
    {
        clientHeight = document.documentElement.clientHeight+186;
    }
    else
    {
        clientHeight = document.documentElement.clientHeight-27;
    }
    iframe.style.height = clientHeight +'px';
    
    var navmanagermenu = $('NavManagerMenu');
    //alert(clientHeight);
    navmanagermenu.style.height = clientHeight +2+'px';
    navmanagermenu.style.minHeight = clientHeight +2+'px';
   
    window.onresize = function(){setscreendiv();} 
    window.onscroll = function(){setscreendiv();} 
}
//对字符串的操作
var MyString = {
    Left : function (str,n)
    {
        if(str.length > 0)
        {
            if(n>str.length) n = str.length;
            return str.substr(0,n)
        }
        else
        {
         return;
        }
    },
    
    Right : function (str,n)
    {
        if(str.length > 0)
        {
            if(n>=str.length) return str;
            return str.substr(str.length-n,n);
        }
        else
        {
            return;
        }
    },

    Trim : function (str) 
    {
        if (typeof str == 'string') return str.replace(/(^\s*)|(\s*$)/g, '');
    },

    Ltrim : function (str) 
    { 
        if (typeof str == 'string') return str.replace(/(^\s*)/g, '');
    },

    Rtrim : function (str) 
    { 
        if (typeof str == 'string') return str.replace(/(\s*$)/g, '');
    },
    /*
	
    */
    stripTags : function(str) {
        if (typeof str == 'string')return str.replace(/<\/?[^>]+>/gi, '').replace(/(^\s*)|(\s*$)/g, '');
    }
};
//操作时间的类
var DateOperator={
	previousDateString:function(date,frequency){
	if(date==''){
		alert('传入时间为空');
		return '';
	} 
	var dateArray = date.split("-");
	var year=parseInt(dateArray[0]);
	if(frequency=='YearBased'){
		return parseInt(dateArray[0])-1;
	}else{
		var temp=dateArray[1];
		if(dateArray[1].indexOf('0')==0){
		temp=dateArray[1].substring(1,dateArray[1].length);
		}
		var mq=parseInt(temp);
		var previousMq = mq - 1;
		if(frequency=='MonthBased'){
		 	if (previousMq == 0) {
				var nextYear = year - 1;
				return nextYear + "-12";
			} else if (previousMq >= 10) {
				return year + '-'+previousMq;
			}else if (previousMq < 10) {
				return year+ "-0" + previousMq;
			}
		}else if(frequency=='QuarterBased'){
			if (previousMq > 0) {
				return year+ "-0" + previousMq;
			}else if (previousMq == 0) {
				var nextYear = year - 1;
				return nextYear + "-04";
			} 
		} 
	}
	},
	
	nextDateString:function(date,frequency){
	if(date==''){
		alert('传入时间为空');
		return '';
	} 
	var dateArray = date.split("-");
	var year=parseInt(dateArray[0]);
	if(frequency=='YearBased'){
		return parseInt(dateArray[0])+1;
	}else{
		var temp=dateArray[1];
		if(dateArray[1].indexOf('0')==0){
		temp=dateArray[1].substring(1,dateArray[1].length);
		}
		var mq=parseInt(temp);
		var previousMq = mq + 1;
		if(frequency=='MonthBased'){
		 	 if (previousMq < 10) {
				return year+ "-0" + previousMq;
			}else if (previousMq <= 12) {
				return year + '-'+previousMq;
			}else if(previousMq == 13){
				var nextYear = year + 1;
				return nextYear + "-01";
			} 
		}else if(frequency=='QuarterBased'){
			if (previousMq <= 4) {
				return year+ "-0" + previousMq;
			}else if (previousMq == 5) {
				var nextYear = year + 1;
				return nextYear + "-01";
			} 
		} 
	}
	
	},
	
	getTimeArrayByFromAndTo:function(from,to,frequency){
	if(!this.isTimeValid(from,to,frequency)){
		return null;
	}
	 var  dateArray = new Array();
	 dateArray.push(from);
	 var nextDate = from;
	 while (nextDate != '' && nextDate!=to) {
		nextDate =this.nextDateString(nextDate, frequency);
		 dateArray.push(nextDate);
	 }
	return dateArray;
	},
	
	isTimeValid:function(from,to,frequency){
	var dateArrayFrom= from.split("-");
	var dateArrayTo= to.split("-");
	var fromYear=parseInt(dateArrayFrom[0]);
	var toYear=parseInt(dateArrayTo[0]);
	if(frequency=='YearBased'){
		if(fromYear<=toYear) return true;
		else return false;
	}else{
		var mqFrom=parseInt(dateArrayFrom[1]);
		var mqTo=parseInt(dateArrayTo[1]);
		if((fromYear>toYear)||((fromYear==toYear)&&(mqFrom>mqTo))) {
			alert("请选择正确的时间");
			return false;
		}else return true;
	}
	}
};




//验证类  均返回boolean型
var MyValidate = {
	
    IsNumber : function(str){
        if (/^\d+$/.test(str)){return true;}else{return false;}
    },

    IsInt : function(str){
        if (/^(\+|-)?\d+$/.test(str)){return true;}else{return false;}
    },
    
    IsDouble : function(str){
    	if (/^(\+|-)?\d+(\.\d+)?$/.test(str)){return true;}else{return false;}
    },
    
    //是不是中文字符
    IsChinese : function(str)
    {
        if (/^[\u4e00-\u9fa5]+$/.test(str)){return true;}else{return false;}
    },
	//是不是英文字母
    IsLower : function(str)
    {
         if (/^[A-Za-z]+$/.test(str)){return true}else{return false;}
    },
    IsEmail : function(str)
    {
        var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;    
        if(myReg.test(str)){return true;}else{return false;}
    },
    IsMobile : function(str)
    {
        var regu =/(^[1][3][0-9]{9}$)|(^0[1][3][0-9]{9}$)/;   
        var re = new RegExp(regu);   
        if (re.test(str)){return true;}else{return false;}
    }
};
/*
	Ajax操作类    
*/
var MyAjax = {
	GetXMLHttpRequest : function() 
	{
		if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} 
		else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	},
	/*
	将ActionForm中的数据格式化成url字符串
    */
	getFormAsString : function (formName) {
		returnString = "";
		var currentForm=document.forms[formName];
		if(currentForm!=null){
			formElements = currentForm.elements;
			for (var i = formElements.length - 1; i >= 0; --i) {
				returnString = returnString + "&" + escape(formElements[i].name) + "=" +encodeURI(encodeURI(formElements[i].value));
			}
			return returnString;
		}
		return "";
	},
	/*
		
		@url		要请求的url地址
		@spanId		要将返回的数据显示在网页中的div或span
		@formName	要使用的ActionForm  此参数可以为空
    */
	getDataAndDisplay : function(url, spanId,formName) {
		var hostUrl=window.location.protocol+'//'+window.location.host;
		var pathname=window.location.pathname; //
		var tempPathArray=pathname.split('/');
		var realUrl=hostUrl+'/'+tempPathArray[1]+url;
		var sendUrl=realUrl.substring(0,realUrl.indexOf("?"));
		
		
		
		
		realUrl +=this.getFormAsString(formName)+"&tempTime="+new Date().getTime()+"&isRequestTypeAjax=1";
		
//		alert("realUrl="+realUrl);
//	
//		alert(spanId);
		var param=realUrl.substring(realUrl.indexOf("?")+1,realUrl.length);
		
			
		
		var xmlHttp = this.GetXMLHttpRequest();
		xmlHttp.open("POST", sendUrl, false);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		var flag=1;
		 var w3 = new WaitingTip({innerHTML:"<img alt='running...' src='../images/w2.gif' />正在加载，请稍候..."});
       	 var objSpanId = document.getElementById(spanId);   
       	 
		xmlHttp.onreadystatechange = function () {
			 if  (xmlHttp.readyState  ==  4)  { 
			 	if(xmlHttp.status == 200||xmlHttp.status ==0||xmlHttp.status ==500){
				 	if(spanId!=""){
						if(flag==0){
							w3.hide();
						}
						var text = xmlHttp.responseText.split("<body>");
						if(text.length<2){
							if(xmlHttp.responseText=='userNotLoginFromAjaxError'){
								alert("您没有登录，请登录后进行操作!");
								document.location=getPath()+"sysadmin/login.jsp";
							}else{
//								alert("对不起，出错了,请重试或联系管理员");
								return;
							}
						}
						var result = text[1].replace("</body></html>", "");
						
						document.getElementById(spanId).innerHTML = result;
					}
				 }else  if  (xmlHttp.status  ==  404)  { 
                  	 alert ("对不起，您访问的URL不存在."); 
	              }else  if  (xmlHttp.status  ==  403)  { 
	                  alert("对不起，访问被拒绝."); 
	              } //else if  (xmlHttp.status  ==  500)  { 
                  	// alert ("对不起，程序运行出现错误!."); 
                  //	}
	              else
	                  alert("对不起，您的请求无法响应，程序运行返回状态码为："  +  xmlHttp.status); 
			}else{ //alert("readyState is  "  +  xmlHttp.readyState);
				if(flag==1){//alert("readyState is  "  +  xmlHttp.readyState);
				 w3.show(objSpanId,"up");
				 flag=0;//
				}
			}
		};
		
		xmlHttp.send(param);
		
		
	}
};

//复选框中的options左右移动
 function moveSelected(oSourceSel,oTargetSel)
 {
     //建立存储value和text的缓存数组
     var arrSelValue = new Array();
     var arrSelText = new Array();
     //此数组存贮选中的options，以value来对应
     var arrValueTextRelation = new Array();
     var index = 0;//用来辅助建立缓存数组
     
     //存储源列表框中所有的数据到缓存中，并建立value和选中option的对应关系
     for(var i=0; i<oSourceSel.options.length; i++)
     {
         if(oSourceSel.options[i].selected && oSourceSel.options[i].text!="")
         {
             //存储
             arrSelValue[index] = oSourceSel.options[i].value;
             arrSelText[index] = oSourceSel.options[i].text;
             //建立value和选中option的对应关系
             arrValueTextRelation[arrSelValue[index]] = oSourceSel.options[i];
             index ++;
         }
     }
     
     //增加缓存的数据到目的列表框中，并删除源列表框中的对应项
     for(var i=0; i<arrSelText.length; i++)  
     {
         //增加
         var oOption = document.createElement("option");
         oOption.text = arrSelText[i];
         oOption.value = arrSelValue[i];
         oTargetSel.add(oOption);
         //删除源列表框中的对应项
         oSourceSel.removeChild(arrValueTextRelation[arrSelValue[i]]);
     }
 }
 

//复选框中的options向上移动
function moveUp(selectObj) 
{ 
    var theObjOptions=selectObj.options;
    for(var i=1;i<theObjOptions.length;i++) {
        if( theObjOptions[i].selected && !theObjOptions[i-1].selected ) {
            swapOptionProperties(theObjOptions[i],theObjOptions[i-1]);
        }
    }
} 
//复选框中的options向下移动
function moveDown(selectObj) 
{ 
    var theObjOptions=selectObj.options;
    for(var i=theObjOptions.length-2;i>-1;i--) {
        if( theObjOptions[i].selected && !theObjOptions[i+1].selected ) {
            swapOptionProperties(theObjOptions[i],theObjOptions[i+1]);
        }
    }
} 
//复选框中的options置顶
function moveToTop(selectObj){
    var theObjOptions=selectObj.options;
    var oOption=null;
    for(var i=0;i<theObjOptions.length;i++) {
        if( theObjOptions[i].selected && oOption) {
            selectObj.insertBefore(theObjOptions[i],oOption);
        }
        else if(!oOption && !theObjOptions[i].selected) {
            oOption=theObjOptions[i];
        }
    }
}
//复选框中的options置底
function moveToBottom(selectObj){
    var theObjOptions=selectObj.options;
    var oOption=null;
    for(var i=theObjOptions.length-1;i>-1;i--) {
        if( theObjOptions[i].selected ) {
            if(oOption) {
                oOption=selectObj.insertBefore(theObjOptions[i],oOption);
            }
            else oOption=selectObj.appendChild(theObjOptions[i]);
        }
    }

}
//选中复选框中的所有options
function selectAllOption(selectObj){
    var theObjOptions=selectObj.options;
    for(var i=0;i<theObjOptions.length;i++){
        theObjOptions[0].selected=true;
    }
}

/* private function *///交换复选框中的两个option
function swapOptionProperties(option1,option2){
    //option1.swapNode(option2);
    var tempStr=option1.value;
    option1.value=option2.value;
    option2.value=tempStr;
    tempStr=option1.text;
    option1.text=option2.text;
    option2.text=tempStr;
    tempStr=option1.selected;
    option1.selected=option2.selected;
    option2.selected=tempStr;
}
//调整obj的宽度
function resetAutoWidth(obj){
    var tempWidth=obj.style.getExpression("width");
    if(tempWidth!=null) {
        obj.style.width="auto";
        obj.style.setExpression("width",tempWidth);
        obj.style.width=null;
    }
}
//创建xun下拉列表（旬-tendays）
function   makeXun()   {   
 	var arrXun=["上旬","中旬","下旬"];   
    var   xun   =   document.getElementsByName("xun"); 
    for(var j=0;j<xun.length;j++){
       if(xun[j].options.length==0){
        for(var   i=0;i<3;i++)   
        {   
          xun[j][xun[j].options.length]   =   new   Option(arrXun[i],i+1);  
        }  
        }} 
  } 
//创建week下拉列表
function   makeWeek()   
  {   
        var   week   =   document.getElementsByName("week"); 
       for(var j=0;j<week.length;j++){
       if(week[j].options.length==0){
        for(var   i=0;i<52;i++)   
        {   
          week[j][week[j].options.length]   =   new   Option(i+1+"周",i+1);  
        }  
        }} 
  } 
//创建month下拉列表
  function makeMonth(){   
       var month = document.getElementsByName("month"); 
       for(var j=0;j<month.length;j++){
       	if(month[j].options.length==0){
        	for(var   i=0;i<12;i++){   
                month[j][month[j].options.length] = new Option(i+1+"月",i+1);  
        	}  
        }
        } 
  } 
  //创建quarter下拉列表  
    function   makeQuarter()   
	  {   
	        var   quarter   =   document.getElementsByName("quarter");   
	          for(var j=0;j<quarter.length;j++){
	          if(quarter[j].options.length==0){
		        for(var   i=0;i<4;i++)   
		        {   
		                quarter[j][quarter[j].options.length]   =   new   Option(i+1+"季度",i+1);   
		        }
		      }   
	        }
	  } 
  function   makeYear()   
  {    var   dtime   =   new   Date();
     var   year   =   document.getElementsByName("year"); 
     var now=dtime.getYear();
      now = now<1900?(1900+now):now;
    for(var j=0;j<year.length;j++){
     if(year[j].options.length==0){//只对原来没有生成option的selection对象生成新的option，added by Sky.  
	     for(var   i=1995;i<=now;i++)   
	        {   
	           year[j][year[j].options.length]   =   new   Option(i+"年",i);   
	        }
	  }  
    }
  }
  //有的时候，路径不对，在这里进行统一处理是否需要'../',主要是window.open函数需要需要使用，使用Ajax的不需要
function getPath(){
//当前路径
	var pathname=window.location.pathname;
	var temp=pathname.split('/');
	var path='';
	if(temp.length==4){//例如'/Fengxian/***/***.jsp'
		path='../';
	}
	//if(temp.length==3)//例如'/Fengxian/***.do' ,则默认为 path=''
	return path;
}

/////////////////////////////////////提示js开始///////////////////////////////////////////////////////
function getposition(obj) {
	var r = new Array();
	r['x'] = obj.offsetLeft;
	r['y'] = obj.offsetTop;
	while(obj = obj.offsetParent) {
		r['x'] += obj.offsetLeft;
		r['y'] += obj.offsetTop;
	}
	return r;
}
//显示提示层代码
function showhintinfo(obj, objleftoffset,objtopoffset, title, info , objheight, showtype ,objtopfirefoxoffset)
{
   
   var p = getposition(obj);
   
   if((showtype==null)||(showtype =="")) 
   {
       showtype =="up";
   }
   document.getElementById('hintiframe'+showtype).style.height= objheight + "px";
   
   
   //document.getElementById('hinttitle').innerHTML = title;
   var inHTML=info;
   if(info.indexOf('span')==0){
   inHTML=document.getElementById(info).innerHTML;
   }
   document.getElementById('hintinfo'+showtype).innerHTML = inHTML;
   document.getElementById('hintdiv'+showtype).style.display='block';
   
   if(objtopfirefoxoffset != null && objtopfirefoxoffset !=0 && !isie())
   {
        document.getElementById('hintdiv'+showtype).style.top=p['y']+parseInt(objtopfirefoxoffset)+"px";
   }
   else
   {
        if(objtopoffset == 0)
        { 
			if(showtype=="up")
			{
				 document.getElementById('hintdiv'+showtype).style.top=p['y']-document.getElementById('hintinfo'+showtype).offsetHeight-40+"px";
			}
			else
			{
				 document.getElementById('hintdiv'+showtype).style.top=p['y']+obj.offsetHeight+5+"px";
			}
        }
        else
        {
			document.getElementById('hintdiv'+showtype).style.top=p['y']+objtopoffset+"px";
        }
   }
   document.getElementById('hintdiv'+showtype).style.left=p['x']+objleftoffset+"px";
}
//隐藏提示层代码
function hidehintinfo()
{
    document.getElementById('hintdivup').style.display='none';
    document.getElementById('hintdivdown').style.display='none';
}
/////////////////////////////////////提示层js结束///////////////////////////////////////////////////////


///////////////////////////////////////ajax等待提示////////////////////////////////////////////////////////
/**
 *author: Hal
 */

/**
 * @praam <String>containerElId=" ____waiting____随机数" 指定一个容器的id
 * @param <String>styleClassName 容器的css样式类
 * @param <String>innerHTML ＝"<img alt='running...' src='/images/waiting.gif' /> "  内容
 * @param <String>anchor ="down"  停放位置 ["down","up","left","right","center"];
 * @param <Number>gap =2与参照节点位置的间距
 */
function WaitingTip (options){
    if(!options){
             options = {
             containerElId: null,
             styleClassName: null,
             innerHTML:null,
             anchor:null,
             gap:null
      }
    }
    var id = options.containerElid ||" ____waiting" + Math.floor(Math.random() * 1000000);
    this.getWaitEl = function(){
        return document.getElementById(id);
    }
    
    var anchor = options.anchor ? options.anchor.toLowerCase() : "down" ;
    this.getAnchor = function(){
        return anchor;
    }
    
    var gap = options.gap || 2;
    this.getGap = function(){
        return gap;
    }
    
    var init = function(){
        var div = document.createElement("span") 
        div.id = id;
        div.style.position = "absolute";
        div.style.display = "none";
        if(options.styleClassName)div.className = styleClassName;            
        document.body.appendChild(div);
        if(options.innerHTML){
            div.innerHTML = options.innerHTML;
        }
        else{
            var waitingImg = document.createElement("img");
            waitingImg.src = "/images/waiting.gif";
            waitingImg.alt = "running...";
            div.appendChild(waitingImg);
        }
       // searchingEl  = div;
    }
    init();
}

/**
 *获取某个HTML Element绝对位置
 *@private
 */
WaitingTip.prototype.GetAbsoluteLocation = function (element)
{
    if ( arguments.length != 1 || element == null )
    {
        return null;
    }
    var offsetTop = element.offsetTop;
    var offsetLeft = element.offsetLeft;
    var offsetWidth = element.offsetWidth;
    var offsetHeight = element.offsetHeight;
    while( element = element.offsetParent )
    {
        offsetTop += element.offsetTop;
        offsetLeft += element.offsetLeft;
    }
    return { absoluteTop: offsetTop, absoluteLeft: offsetLeft,
        offsetWidth: offsetWidth, offsetHeight: offsetHeight };
}
    
/**
 *隐藏
 *@public
 */
WaitingTip.prototype.hide = function(){
	var obj= this.getWaitEl();
	document.body.removeChild(obj); 
}    
    
   
/**
 *显示
 *@public
 *@param <String> relativelyElId 参照节点的id
 *@param <String>anchor  默认为初始化设置时值
 */
WaitingTip.prototype.show = function(relativelyEl,anchor){
    var p = this.GetAbsoluteLocation(relativelyEl);
    var waitEl = this.getWaitEl();
    var gap = this.getGap();
    var _anchor = anchor || this.getAnchor();
    waitEl.style.display = "block";
    switch(_anchor){
        case "down":
            waitEl.style.top = p.absoluteTop + p.offsetHeight + gap + "px";
            waitEl.style.left = p.absoluteLeft + "px";
            break;
        case "right":
            waitEl.style.top = p.absoluteTop + "px";
            waitEl.style.left = p.absoluteLeft + p.offsetWidth + gap +  "px";            
            break;
        case "left":
             waitElpos = this.GetAbsoluteLocation(waitEl);
            waitEl.style.top = p.absoluteTop + "px";
            waitEl.style.left = p.absoluteLeft - gap - waitElpos.offsetWidth +  "px";                        
            break;
        case "up":
             waitElpos = this.GetAbsoluteLocation(waitEl);
            waitEl.style.top = p.absoluteTop - gap -  waitElpos.offsetHeight +  "px";
            waitEl.style.left = p.absoluteLeft +  "px";             
            break;
        case "center":
            try{
               waitElpos = this.GetAbsoluteLocation(waitEl);
              waitEl.style.top = p.absoluteTop  + Math.floor((p.offsetHeight - waitElpos.offsetHeight)  / 2) +  "px";
              waitEl.style.left = p.absoluteLeft +  Math.floor((p.offsetWidth  - waitElpos.offsetWidth)  / 2) + "px";                      
            }
            catch(error){
                waitEl.style.top = p.absoluteTop  + Math.floor(p.offsetHeight  / 2) +  "px";
                waitEl.style.left = p.absoluteLeft +  Math.floor(p.offsetWidth   / 2) + "px";                      
            }
            break;
    }
}

//////////////////////////////////////////ajax等待提示//////////////////////////////////////////////
