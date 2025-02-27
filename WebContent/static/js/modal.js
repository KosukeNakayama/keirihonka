
(function () {

    let trgEl

    //modal設定
    document.querySelectorAll('.js-modalInput').forEach( (el) => {
        el.addEventListener('click', (ev) => {
            trgEl = el
            const target = el.getAttribute('data-modal')
            document.getElementById('js-overlay').classList.add('is-show')
            document.getElementById(target).style.display = 'block'

           //登録済みのメモを表示
           var tdID = trgEl.getAttribute("id");
           let textBox = document.getElementById('text-value');
           let entryMemo = document.getElementById(tdID).dataset.memo;
           if (entryMemo != null) {
        	   textBox.textContent = entryMemo;
        	   textBox.value = entryMemo;
           }
        })
    })

    //modal内でのform設定
    let entryBtn = document.getElementById('entryBtn');
    entryBtn.addEventListener('click', function(e){

        //form再読み込み禁止（入力を残す）
        e.preventDefault();

        //選択statusを取得
        let statusArray = document.getElementsByName('status');
        let statusValue = '';

        for (var i=0; i<statusArray.length; i++){

            //指定セルの値を取得
            var tdID = trgEl.getAttribute("id");
            var tdValue = document.getElementById(tdID).textContent;
            //[99 大原 太郎]で姓のみ取り出し氏名が入力されているかで判定
            var tdValueArray = tdValue.split(' ');

            //指定したstatusを登録
            if (statusArray[i].checked) {

                //学生名が表示されているか確認
                if (tdValueArray[1] == null) {

                    alert("学生が登録されていない座席です");

                } else {

                    statusValue = statusArray[i].value;
                    switch(statusValue) {
                        case '0': trgEl.style.backgroundColor='white'; break;           //出席
                        case '1': trgEl.style.backgroundColor='red'; break;             //欠席
                        case '2': trgEl.style.backgroundColor='yellow'; break;          //遅刻
                        case '3': trgEl.style.backgroundColor='violet'; break;          //早退
                        case '4': trgEl.style.backgroundColor='greenyellow'; break;     //他欠
                        case '5': trgEl.style.backgroundColor='orange'; break;          //遅刻早退
                    }
                }
            }
        }

        //出欠状況コメント入力欄データ処理
        let textBox = document.getElementById('text-value');
        let textValue = textBox.value;
        console.log("textValue:",textValue);

        //指定セルのstudentIdとstatusを取得
        let studentIdAtt = document.getElementById(tdID).dataset.student;
        let attStatus = statusValue + "," + textValue;

        //入力状況を文字列にまとめてattRegDsp.jspのattEntry要素にセット
        let attEntry = document.getElementById('attEntry');
        attEntry.value = attEntry.value + ";" + studentIdAtt.trim() + "," + attStatus + " ";

        console.log(attEntry.value);

        //入力値初期化
        studentIdAtt = "";
        attStatus = "";
        textBox.value = "";

        document.getElementById('js-overlay').classList.remove('is-show');
        // document.querySelectorAll('.js-modal').forEach((modal) => {
        //     modal.style.display = 'none';
        // })

    })

}());


