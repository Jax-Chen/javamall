/*
 *2016-10-15  创建  *
 * 商品详情   *
 * lijianfu *
 * update *
 */

$(document).ready(function(){//

	$('.productDetails_div3_div2_div1 a').live('click',function(){//商品详情切换
		if($(this).hasClass('on')){
			return false;
		}else{
			var _dataType = $(this).attr('data-type');
			$(this).addClass('on').siblings('a').removeClass('on');
			$('.'+_dataType+'').removeClass('none').siblings('div').addClass('none');
			loads();
		}
	})
	$('.productDetails_div2_div5_div p em a').live('click',function(){//商品规格选择
		if($(this).hasClass('on')){
			return false;
		}else{
			$(this).addClass('on').siblings('a').removeClass('on');
			loads();
		}

	})
	
	var datasss = {
			'status':'success',
	    	"product": {
		        "productid": 1,
		        "specs": "红色、xxl",
		        "price":200,
		        "store":500,
       			"mtkprice":600
	   		},
	    	"result": 1
		},
	
		config={
			specvid:'',
			specs:'',
			goodsid:goodsid
				
		};
	
	function loads(){
		config.specs = '';
		var len = $('.productDetails_div2_div5_div p').length,//选项种类
			onlen = $('.productDetails_div2_div5_div p em .on').length;//已选择种类
		if(len == onlen){
			config.specvid = '';
			for(var i = 0;i < len;i++){
				var spid = $('.productDetails_div2_div5_div p').eq(i).children('em').children('.on').attr('specvid');
				var title = $('.productDetails_div2_div5_div p').eq(i).children('em').children('.on').attr('title');
				if(i == len - 1){
					config.specvid = config.specvid + spid;
					config.specs = config.specs + title;
				}else{
					config.specvid = config.specvid + spid +',';
					config.specs = config.specs + title +'、';
				}
			}
			ajaxs();
		}
	}
	
	
	function ajaxs(){
		$.ajax({
   			type: "post",//请求方式
   			url: ctx + "/api/shop/distributionStoreGoodsApiAction!getProductBySpec.do?ajax=yes",//请求地址
   			data: config,//提交字段
   			dataType: "json",//json格式
   			beforeSend:function(){//正在加载
   				$('.load_go em').html('已经在飞速加载了');
   		    },
   		    	//complete: function(data){//请求完成的处理
   			success:function(data){//请求成功的处理
   				var datas = datasss;
   				if(datas.status === 'success'){
   					$('.productDetails_div2_div2_sp1 em i').html("￥" + data.product.price.toFixed(2));//销售价
   					//$('.productDetails_div2_div2_sp1 font i').html(data.product.mtkprice);//原价
   					$('.productDetails_div2_div3_p2 em').html(data.product.enable_store);//库存
   					$('.productDetails_div2_div5_div input').val(data.product.product_id);
   				}else{
   				}
   			},
   		    error:function(){//网络错误
   		    	//alert("网络错误");
   		    }
   		});
	}
	
})

function buy(){
	var len = $('.productDetails_div2_div5_div p').length,//选项种类
		onlen = $('.productDetails_div2_div5_div p em .on').length;//已选择种类
	if(len == onlen) {

		$("#goodsform").ajaxSubmit({
			url: ctx + '/api/shop/dcartApiAction!addProductToCart.do',
			type: 'post',//提交的方式
			dataType:'json',
			timeout: 1000,
			cache: false,
			data : "",
			success : function(data) {
				var result = data.result;
				if(result==1){
					location.href = ctx +"/cart.html";
				}
			},
		});
	}
	else
	{
		$("html,body").animate({scrollTop:($(".productDetails_div2_div5").offset().top) - ($(window).width())/7.5*0.86},1000);
		alert("请选择规格.");
	}
}

function addCart(){

	var len = $('.productDetails_div2_div5_div p').length,//选项种类
		onlen = $('.productDetails_div2_div5_div p em .on').length;//已选择种类
		if(len == onlen) {
			$("#goodsform").ajaxSubmit({
				url: ctx + '/api/shop/dcartApiAction!addProductToCart.do',
				type: 'post',//提交的方式
				dataType:'json',
				timeout: 1000,
				cache: false,
				data : "",
				success : function(data) {
					var result = data.result;
					if(result==1){
						alert(data.message);
					}
				},
			});
		}
		else
		{
			$("html,body").animate({scrollTop:($(".productDetails_div2_div5").offset().top) - ($(window).width())/7.5*0.86},1000);
			alert("请选择规格.");
		}

}



