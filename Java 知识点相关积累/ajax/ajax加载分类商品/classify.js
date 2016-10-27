/*
 *2016-10-15  创建  *
 * 分类   *
 * lijianfu *
 * update *
 */

$(document).ready(function(){//
//	alert(catid);
	var data1 = "",
		config = {//提交字段
			pages:2,//页码
			catids:catid
		},
		fage = true;//放置重复加载
//	alert(config.catids);
	$(window).scroll(function () {//滚动条滚动
		var scrolls = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop,//获取滚动高度
			_btm = $('.load_go').height(),//获取加载中的高度
			dicHeight = $(document).height()-$(window).height() - _btm -1;//获取需要加载的页面高度
		if(scrolls > dicHeight && fage == true){
			fage = false;
			ajaxs();
		}
	})
	function ajaxs(){//滚动加载ajax
		
		$.ajax({
   			type: "post",//请求方式
   			url: ctx + "/api/shop/distributionStoreGoodsApiAction!listGoodsJsonforDistributorAll.do?ajax=yes",//请求地址
   			data: config,//提交字段
   			dataType: "json",//json格式
   			beforeSend:function(){//正在加载
   				$('.load_go em').html('已经在飞速加载了');
   		    },
   		    //complete: function(data){//请求完成的处理
   		    success:function(data){//请求成功的处理
   				if(data.result == 1){
   					htmls = '';
   					for(var i = 0;i < data.data.length; i++){
   						data1 = data.data[i].name;
   						htmls += '<a href="goods_detail.html?goodsid=' + data.data[i].goods_id + '&catid=' + catid + '" class="go_nei">';
   						htmls += 	'<p class="shopHome_list_ap1">';
   						htmls +=		'<img src="'+data.data[i].small+'">';
   						htmls +=	'</p>';
   						htmls +=	'<p class="shopHome_list_ap2">';
   						htmls +=		'<span class="shopHome_list_ap2_sp1">';
   						htmls +=			'<i>' + data.data[i].sourceCountryImage + '</i>';
   						htmls +=			'<em>'+data.data[i].name+'</em>';
						htmls +=		'</span>';
						htmls +=		'<span class="shopHome_list_ap2_sp2">';
						htmls +=			'<font>¥<i>'+data.data[i].sellprice.toFixed(2)+'</i></font>';
						htmls +=			'<em>立即抢购<i></i></em>';
						htmls +=		'</span>';
						htmls +=	'</p>';
						htmls +='</a>';
   					}
   					if(data.data.length < 10){//少于规定每次加载条数（10）表示没有更多商品了
						$('.load_go em').html('没有更多商品了！');
						$('.load_go span').html('<img src="' + ctx + '/themes/store/images/jz_end.png"/>');
   					}else{
   						config.pages++;
   						fage = true;
						$('.load_go em').html('上拉加载');
						$('.load_go span').html('<img src="' + ctx + '/themes/store/images/jz.gif"/>');
   					}
   					$('.commodity_list_sp').append(htmls);
   					
   				}else{
   				}
   			},
   		    error:function(){//网络错误
   		    	//alert("网络错误");
   		    }
   		});
	}
})

