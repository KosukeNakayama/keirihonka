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

//読み込み時、ポップアップの判定
$(document).ready(()=>{
	if($("#isPopup").val()){
		showPopup();
	}
});

//ポップアップイン
function showPopup() {
    $("#modal")[0].style.display = 'block';
    $("#popup")[0].style.display = 'block';
}

//ポップアップアウト
function hidePopup() {
    $("#modal")[0].style.display = 'none';
    $("#popup")[0].style.display = 'none';
    $("#studentId").val($("input[name='stuIds']:checked").val());
}

window.addEventListener('click', function(event) {
	  if (event.target === document.getElementById('modal')) {
	    hidePopup();
	  }
	});