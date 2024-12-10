(function () {
    let trgEl

    document.querySelectorAll('.js-modalInput').forEach( (el) => {
        el.addEventListener('click', (ev) => {
            trgEl = el
            const target = el.getAttribute('data-modal')
            document.getElementById('js-overlay').classList.add('is-show')
            document.getElementById(target).style.display = 'block'
        })
    })

    document.querySelectorAll('.js-modal input').forEach( (el) => {
        // el.addEventListener('submit', (ev) => {
        el.addEventListener('change', (ev) => {        
        // el.addEventListener('click', (ev) => {
            // trgEl.value = el.value

            switch(el.value) {
                case "0": trgEl.style.backgroundColor='white'; break;           //出席
                case "1": trgEl.style.backgroundColor='red'; break;             //欠席
                case "2": trgEl.style.backgroundColor='yellow'; break;          //遅刻
                case "3": trgEl.style.backgroundColor='violet'; break;          //早退
                case "4": trgEl.style.backgroundColor='greenyellow'; break;     //他欠
                case "5": trgEl.style.backgroundColor='#db8449'; break;    		//遅刻早退
            }

            document.getElementById('js-overlay').classList.remove('is-show')
            document.querySelectorAll('.js-modal').forEach((modal) => {
                modal.style.display = 'none'
            })
        })
    })

    console.log("Start!" );
}())

/* 参考サイト
https://codepen.io/kandai/pen/GRgJVqz
*/


