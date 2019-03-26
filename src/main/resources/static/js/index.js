$(function(){
        $.ajax({
                url:'/user/welcome',
                dataType:"html",
                cache:false,
                success:function(data){
                   $(".welcome").prepend(data);
                }
         });

      $('.ajz,.help_ajz').click(function(){
              $('.login_box').show();
              var set_l =  $('.login_box').offset().left+249;
              var set_h =  $('.login_box').offset().top+8;
              var paper = Raphael(set_l, set_h, 26, 26);
              var c=paper.image("/images/index/close.png",0,0,26,26);
              c.attr("z-index","4");
              c.attr("cursor","pointer");
              c.hover(function (event) {
                    this.animate({rotation:360}, 1000, "<>");
                    }, function (event) {
                    this.animate({rotation:0}, 1000, "<>");
             });
              c.click(function (event) {
                    c.hide();
                    $('.login_box').hide();
             });

     });

   $("#tips").mouseover(function(){
          $("#loginbtninput").addClass("tips_on");
          $(this).next().show();
    });

    $("#tips").mouseout(function(){
          $("#loginbtninput").removeClass("tips_on");
          $(this).next().hide();
    });

        var n=0;
        var obj=document.getElementById("AdLayer");
        if(!obj){
          return false;
        }
        var x=0;
        var fe=$("#AdLayer");
        window.onscroll=function(){
            obj.style.top=(document.body.scrollTop||document.documentElement.scrollTop)+n+'px';
            x=(document.body.scrollTop||document.documentElement.scrollTop)+n;
            if(x==0){fe.fadeOut().hide()}else{fe.fadeIn().show()};
        };
        window.onresize=function(){
        obj.style.top=(document.body.scrollTop||document.documentElement.scrollTop)+n+'px'
        };
});
			
 function showImg(i){
		$("#bannerlist .b_left_img")
			.eq(i).stop(true,true).fadeIn(600)
			.siblings(".b_left_img").hide()
			.eq(i+1).stop(true,true).fadeOut(600);
		//$(contentDom).stop(true,true).animate({ top:i*(-255)}, "slow");
		$(".b_right li")
			.eq(i).addClass("on")
			.siblings("li").removeClass("on");
}

function a_email(){
         $.ajax({
            url:'/user/a_email',
            dataType:"html",
            cache:false,
            success:function(data){
                $("#UserName").val(data);
				if(data!=""){
				$("#Password").focus();
				}else{
				$("#UserName").focus();
				}
            },
            error:function(e){

            }
        });
}

 function AddFavorite(sURL, sTitle) {
    try {   
        window.external.addFavorite(sURL, sTitle);
    } catch (e) {
        try {
            window.sidebar.addPanel(sTitle, sURL, "");
        } catch (e) {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}

function SetHome(obj, vrl) {
    try {
        obj.style.behavior = 'url(#default#homepage)';
        obj.setHomePage(vrl);
    } catch (e) {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager
                        .enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
            }
            var prefs = Components.classes['@mozilla.org/preferences-service;1']
                    .getService(Components.interfaces.nsIPrefBranch);
            prefs.setCharPref('browser.startup.homepage', vrl);
        }
    }
}

