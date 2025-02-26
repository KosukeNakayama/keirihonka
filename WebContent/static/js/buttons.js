//キャンセルボタン処理
let cancelBtn = document.getElementById('cancelBtn');
cancelBtn.addEventListener('click', cancelInput);

function cancelInput(){
	document.getElementById('js-overlay').classList.remove('is-show')
    document.querySelectorAll('.js-modal').forEach((modal) => {
    	modal.style.display = 'none'
    })
}

//const selectElem = document.getElementById("className");
//
//selectElem.addEventListener("change", () => {
//  const index = selectElem.selectedIndex;
//  alert("index:", index);
//  if (index == 0) {
//	  alert("クラスが選択されていません");
//  }
//});

