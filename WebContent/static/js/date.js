//■Global変数
var currentDate = new Date();   //当日
var year;                       //当日（年）
var month;                      //当日（月）
var date;                       //当日（日）

//当日表示要素
let currentDisp = document.getElementById('currentEl');

//前日表示要素
let beforeBtn = document.getElementById('beforeEl');
beforeBtn.addEventListener('click', dayBefore);

//翌日表示要素
let nextBtn = document.getElementById('nextEl');
nextBtn.addEventListener('click', dayNext);

//曜日表示用配列
const days = [ "日", "月", "火", "水", "木", "金", "土",  ];

//Global変数ここまで

//■初期画面表示処理
window.onload = function(){

    //当日を取得し"yyyy/mm/dd"形式に整形し表示
    let today = getDisplayDate( currentDate );

    //画面に当日を表示
    currentDisp.textContent = today;
}

//■前日ボタン押下時処理
function dayBefore(){

//    resetForm();
//	colorSet();
//
//    //前日日付取得
//    const prevDate = new Date(currentDate.getTime());
//    prevDate.setDate(prevDate.getDate()-1);
//
//    //前日を"yyyy/mm/dd"形式に整形し表示
//    let yesterday = getDisplayDate( prevDate );
//
//    //画面表示日付を1日前に再表示
//    currentDate = prevDate;
//    document.getElementById('currentEl').textContent = yesterday;
}

//■翌日ボタン押下時処理
function dayNext(){
//
////    resetForm();
//	colorSet();
//
//    //当日日付取得
//    const nextDate = new Date(currentDate.getTime());
//    nextDate.setDate(nextDate.getDate()+1);
//
//    //翌日を"yyyy/mm/dd"形式に整形し表示
//    let tomorrow = getDisplayDate( nextDate );
//
//    //画面表示日付を1日後に再表示
//    currentDate = nextDate;
//    document.getElementById('currentEl').textContent = tomorrow;
}

//■表示形式整形
function getDisplayDate( dispDate ) {

    //年・月・日・曜日を個別に取得
    year = dispDate.getFullYear();
    month = dispDate.getMonth()+1;
    date = dispDate.getDate();
    dayOfWeek = days[dispDate.getDay()];

    //表示形式に整形しreturn
    let dispDay = year + "/" + month + "/" + date +"(" + dayOfWeek + "）";
    return dispDay;
}

//■formリセット
//function resetForm() {
//    let inForm = document.getElementById('seats');
//    inForm.style.backgroundColor = 'white';
////    inForm.submit();
//    // alert("reset");
//}

function colorSet() {
	let tbl = document.getElementById("seats");
	let rows = tbl.rows;

	for(i=0; i<rows.length; i++){
		let cells = rows[i].cells;
		for(j=0; j<cells.length; j++){
			let cell = cells[j];
			cell.style.backgroundColor = 'white';
		}
	}
}



