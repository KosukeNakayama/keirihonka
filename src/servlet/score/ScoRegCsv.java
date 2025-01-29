package servlet.score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Score;
import dao.ScoreDao;

@MultipartConfig
@WebServlet(urlPatterns={"/score/ScoRegCsv"})
public class ScoRegCsv extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // アップロードされたファイルを取得
        Part filePart = request.getPart("csvFile");
        InputStream fileContent = filePart.getInputStream();

        // CSV内容を保存するリスト
        List<String[]> csvData = new ArrayList<>();
        Score s = new Score();
        ScoreDao dao =new ScoreDao();
        // ファイルの内容を読み込む
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
            String line;
            String csvSplitBy = ",";

            while ((line = reader.readLine()) != null) {
                // カンマで分割して各列の値を取得
                String[] data = line.split(csvSplitBy);
                s.setStudentId(data[0]);
                s.setSubjectId(data[2]);
                s.setScore(Integer.parseInt(data[3]));
                s.setMonth(Integer.parseInt(request.getParameter("month")));
                s.setYear(2024);
                int line2=dao.insert(s);
                if(line2>0){
                	System.out.println("登録できました。");
                }
                csvData.add(data);
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        // 読み込んだCSVデータをリクエストに保存
        request.setAttribute("csvData", csvData);
        // 月を保存
        request.setAttribute("month", request.getParameter("month"));

        // JSPページにフォワードして、結果を表示
        request.getRequestDispatcher("scoRegCsv.jsp").forward(request, response);
    }
}