function simpleCount(options){
	this.second=options.second;//倒计时一共多少秒
	this.before=options.before;//倒计时之前执行事件
	this.duringTime=options.duringTime;//倒计时中的某秒
	this.during=options.during;//倒计时中间某秒执行事件
	this.after=options.after;//倒计时完成执行事件
	this.countMethod=options.countMethod;//每秒倒计时自定义事件
	this.postfix=options.postfix;//cookie后缀
	this.interval=null;
	this.cSecond="cSecond"+this.postfix;
	this.cTime="cTime"+this.postfix;
}
simpleCount.prototype={
	start : function(){
		this.before();
		this.setTime(parseInt(this.getTime()||0)+1);
		if(this.isExistCookie(this.cSecond)){
			this.secondDecline();
		}
		else{
			this.setSecond(this.second+1);
			this.secondDecline();
		}
	},
	restart : function(){
		this.clearSecond();
		this.start();
	},
	clearSecond : function(){
		this.clearCookie(this.cSecond);
	},
	setSecond : function(value){
		this.setCookie(this.cSecond,value);
		return this;
	},
	getSecond : function(){
		return this.getCookie(this.cSecond);
	},
	setTime : function(value){
		this.setCookie(this.cTime,value);
		return this;
	},
	getTime : function(){
		return this.getCookie(this.cTime);
	},
	setCookie : function(name,value){
		var isExist=this.isExistCookie(name);
		var time=new Date();
		time.setMinutes(time.getMinutes()+this.second);
		document.cookie=name+"="+value+";path=/;expires="+time.toUTCString();
	},
	getCookie : function(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	    if(arr=document.cookie.match(reg))
	        return unescape(arr[2]);
	    else 
	        return null; 
	},
	isExistCookie:function(name){
		return document.cookie.indexOf(name)==-1?false:true;
	},
	clearCookie : function(name){
		if(this.isExistCookie(name)){
			var exp = new Date();  
    		exp.setTime(exp.getTime()- 1);
			document.cookie=name+"="+this.getCookie(name)+";path=/;expires="+exp.toGMTString();
		}
	},
	secondDecline : function(){
		clearInterval(this.interval);
		if(this.isExistCookie(this.cSecond)&&this.getSecond()>0){
			this.setSecond(parseInt(this.getSecond())-1);
			if(parseInt(this.getSecond())==this.duringTime){
				this.during();
			}
			this.countMethod();
			var _this=this;
			this.interval=setTimeout(function(){_this.secondDecline();},1000);
		}
		else{
			this.after();
		}
	},
	allStop : function(){
		this.setSecond(0);
		this.setTime(parseInt(this.getTime())-1||0);
	}
};