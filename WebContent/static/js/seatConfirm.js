var seatEntryBtn = document.getElementById('seatEntryBtn');
seatEntryBtn.addEventListener('click', function() {

	let i;
	const seatArray = [];
	var table = document.getElementById('studentsTable');

	//学生座席テーブルのseatNoを配列に格納
	for(i=1; i<table.rows.length; i++){
		seatArray.push(table.rows[i].cells[2].firstElementChild.value);
	}

	//入力座席数最大値設定
	let numOfSeats = document.getElementById('numOfSeats').value;
	const aryMax = function (a, b) {return Math.max(a, b);}
	let seatMax = seatArray.reduce(aryMax);

	//座席番号チェック
	if (isDuplicated(seatArray)) {
		alert("座席番号に重複があります");
	}　else if (numOfSeats < seatMax) {
		alert("座席番号が最大値(" + numOfSeats + ")を超えています");
	} else {
		//エラーがなければ登録処理確認
	    var result = window.confirm('登録しますか？');
	    if( result ) {
	    	//DB登録処理
	    	seatEntryForm.submit()
	    }
	    else {
	    	//画面に戻る
	    }
	}

})

//重複チェック用関数
function isDuplicated(elements) {
  // Setを使って、配列の要素を一意で格納
  const setElements = new Set(elements);
  //元配列の要素数とSet要素数を比較
  //Setの要素は一意なので、重複があると要素数が異なる
  return setElements.size !== elements.length;
}



