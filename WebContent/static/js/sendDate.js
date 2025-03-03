var i = 1 ;
function sendDate() {
  var sendDate = document.createElement('input');
  input_data.type = 'hidden';
  input_data.id = 'inputDate';
  input_data.value = document.getElementById('currentEl');
  var parent = document.getElementById('form_area');
  parent.appendChild(input_data);
  i++ ;
}