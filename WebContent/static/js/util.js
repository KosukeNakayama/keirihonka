/*jQueryエリア*/
$(function(){

	//アコーディオンメニューFAQ
	$('.list').click(function(){
		let $content = $(this).find('.content');
		console.log($content);
		console.log($content.hasClass('Open'));

		if($content.hasClass('Open')){
			$content.slideUp('Open');
			$content.removeClass('Open');
			$(this).find('img').attr('src','/keirihonka/static/img/down_triangle.png');
		}else{
			$content.slideDown('Open');
			$content.addClass('Open');
			$(this).find('img').attr('src','/keirihonka/static/img/right_triangle.png')
		}
	});

	$("input[name='selectClass']").click(function(){
		$("#className").val($(this).parents("label").attr("id"));
	});

	$("#allCheck").click(function(){
		console.log("allCheck");
		if($(this).prop("checked")){
			$("input[name='stuIds']").prop("checked",true);
		}else{
			$("input[name='stuIds']").prop("checked",false);
		}
	});

});