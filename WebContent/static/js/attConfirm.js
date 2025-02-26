var attEntryBtn = document.getElementById('attEntryBtn');
attEntryBtn.addEventListener('click', function() {

//	let i;
//	const seatArray = [];
//	var table = document.getElementById('studentsTable');
//
//	//学生座席テーブルのseatNoを配列に格納
//	for(i=1; i<table.rows.length; i++){
//		seatArray.push(table.rows[i].cells[2].firstElementChild.value);
//	}
//
//	//入力座席数最大値設定
//	let numOfSeats = document.getElementById('numOfSeats').value;
//	const aryMax = function (a, b) {return Math.max(a, b);}
//	let seatMax = seatArray.reduce(aryMax);
//
//	//重複値未入力flag
//	var isInputNull = false;
//	//重複値配列を降順にsort
//	var duplicateValue = seatArray.filter(function (x, i, self) {
//		return self.indexOf(x) !== self.lastIndexOf(x);
//	}).sort((a, b) => b - a);
//	//重複値が未入力であればflagを立てる
//	if ((duplicateValue[0] === '') || (duplicateValue[0] === null)) {
//		isInputNull = true;
//	}
//
//	//座席番号チェック
//	if ( isDuplicated(seatArray) && !isInputNull) {
//		//値が入力&重複ならエラーメッセージ
//		alert(" 座席番号に重複があります　重複番号: " + duplicateValue[0]);
//	}　else if (numOfSeats < seatMax) {
//		//座席数最大値チェック
//		alert("座席番号が最大値(" + numOfSeats + ")を超えています");
//	} else {
//		//エラーがなければ登録処理確認
	    var result = window.confirm('登録しますか？');
	    if( result ) {
	    	//DB登録処理
	    	attEntryForm.submit()
	    }
	    else {
	    	//画面に戻る
	    }
//	}

})




