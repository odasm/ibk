$(function(){

    var page = 0;
    var i = 4;
    //向后 按钮
    $(".wallpaper .next_p a").click(function(){
        var $parent = $(this).parent().parent();
        var $scroll = $parent.find(".w_con ul");
        var $scroll_item = $parent.find(".w_con");
        var v_width = $scroll_item.width();
        var len = $scroll.find("li").length;
        var page_count = Math.ceil(len / i);
        if( !$scroll.is(":animated") ){
            page++;
            if( page < page_count){
                $scroll.animate({
                    left : '-='+v_width
                }, "slow");
            }else{
                page = page_count;
            }
        }
    });
    //往前 按钮
    $(".wallpaper .prev_p a").click(function(){
        var $parent = $(this).parent().parent();
        var $scroll = $parent.find(".w_con ul");
        var $scroll_item = $parent.find(".w_con");
        var v_width = $scroll_item.width();
        var len = $scroll.find("li").length;
        var page_count = Math.ceil(len / i);
        if( !$scroll.is(":animated") ){
            page--;
            if( page <= 0 ){
                $scroll.animate({
                    left :"0px"
                }, "slow");
                page = 0;
            }else{
                $scroll.animate({
                    left : '+='+v_width
                }, "slow");
            }
        }
    });

    $(".tell_me").click(function(){
        var dialog = art.dialog({
            id: 'N3690',
            title: false,
            fixed:true
        });

        // jQuery ajax
        $.ajax({
            url: '/user/thanks',
            success: function (data) {
                dialog.content(data);
            },
            cache: false
        });
    });

    
});


