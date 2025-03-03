var attEntryBtn = document.getElementById('attEntryBtn');
attEntryBtn.addEventListener('click', function() {

    var result = window.confirm('登録しますか？');
    if( result ) {
    	//DB登録処理
    	attEntryForm.submit()
    }
    else {
    	//画面に戻る
    }
})




