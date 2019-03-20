  function BrowserType()  
  {  
      var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
      var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器  
       
      var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
      
      if (isIE)   
      {  
           var reIE = new RegExp("MSIE (\\d+\\.\\d+);");  
           reIE.test(userAgent);  
           var fIEVersion = parseFloat(RegExp["$1"]);  
           if(fIEVersion < 9)  
           { alert('浏览器过低，请升级ie浏览器。')}              
       }
   }

   function GetCookieValue(name) {
     var cookieValue = null;
     if (document.cookie && document.cookie != '') {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
      var cookie = jQuery.trim(cookies[i]);
      if (cookie.substring(0, name.length + 1) == (name + '=')) {
    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
    break;
      }
    }
     }
     return cookieValue;
   }
   function DelCookie(name) {
     var exp = new Date();
     exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
     var cval = GetCookieValue(name);
     // document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
     // document.cookie=name + "=" + cval + ";expires="+exp.toGMTString()+";path=/;domain=ajzhan.com";
     document.cookie=name + "=" + cval + ";expires="+exp.toGMTString()+";path=/;domain=www.ajzhan.com";
   }  


$(function() {
  // 如果不支持placeholder，用jQuery来完成
  if(!isSupportPlaceholder()) {
    // 遍历所有input对象, 除了密码框
    $('input').not("input[type='password']").each(
      function() {
        var self = $(this);
        var val = self.attr("placeholder");
        input(self, val);
      }
    );
    
    /**//* 对password框的特殊处理
     * 1.创建一个text框 
     * 2.获取焦点和失去焦点的时候切换
     */
    $('input[type="password"]').each(
      function() {
        var pwdField    = $(this);  
        var pwdVal      = pwdField.attr('placeholder');  
        var pwdId       = pwdField.attr('id');  
        // 重命名该input的id为原id后跟1
        pwdField.after('<input id="' + pwdId +'1" type="text" value='+pwdVal+' autocomplete="off" class="passwordinput mt16"/>');  
        var pwdPlaceholder = $('#' + pwdId + '1');  
        pwdPlaceholder.show();  
        pwdField.hide();  
          
        pwdPlaceholder.focus(function(){  
          pwdPlaceholder.hide();  
          pwdField.show();  
          pwdField.focus();  
        });  
          
        pwdField.blur(function(){  
          if(pwdField.val() == '') {  
            pwdPlaceholder.show();  
            pwdField.hide();  
          }  
        });  
      }
    );
  }
});

// 判断浏览器是否支持placeholder属性
function isSupportPlaceholder() {
  var input = document.createElement('input');
  return 'placeholder' in input;
}

// jQuery替换placeholder的处理
function input(obj, val) {
  var $input = obj;
  var val = val;
  $input.attr({value:val});
  $input.focus(function() {
    if ($input.val() == val) {
      $(this).attr({value:""});
    }
  }).blur(function() {
    if ($input.val() == "") {
            $(this).attr({value:val});
    }
  });
}
//点击弹出登录弹框
$(function(){
    $('.login-showpan').click(function(){
         layer.open({
           type: 2,
           title: false,
           shadeClose: false,
           shade: 0.5,
           area: ['320px', '369px'],
           content: '/passport/loginframe', //iframe的url
           cancel: function(index, layero){ 
             DelCookie('user_id');                                 
           }    
         });
    }) 
})