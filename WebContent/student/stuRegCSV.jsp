<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Include Sample</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->

    <input type="file" id="fileInput" accept=".csv" style="display: none;">
    <button id="loadButton">CSVファイルを読み込む</button>
    <table id="csvTable" border="1">
    </table>

    <script>
        document.getElementById('loadButton').addEventListener('click', function() {
            document.getElementById('fileInput').click();
        });

        document.getElementById('fileInput').addEventListener('change', function(e) {
            const file = e.target.files[0];
            const reader = new FileReader();
            reader.onload = function(event) {
                const text = event.target.result;
                const rows = text.split('\n').map(row => row.split(','));
                let table = '<tr>';
                for(let header of rows[0]) table += `<th>${header}</th>`;
                table += '</tr>';
                for(let i = 1; i < rows.length; i++) {
                    table += '<tr>';
                    for(let cell of rows[i]) table += `<td>${cell}</td>`;
                    table += '</tr>';
                }
                document.getElementById('csvTable').innerHTML = table;
            };
            reader.readAsText(file);
        });
    </script>




<p><input type="submit" value="登　録"></p>

<a href="">メニュー</a>

</body>
</html>