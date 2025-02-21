//キャンセルボタン処理
let cancelBtn = document.getElementById('cancelBtn');
cancelBtn.addEventListener('click', cancelInput);

function cancelInput(){
	document.getElementById('js-overlay').classList.remove('is-show')
    document.querySelectorAll('.js-modal').forEach((modal) => {
    	modal.style.display = 'none'
    })
}



