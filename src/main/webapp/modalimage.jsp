<%-- 
    Document   : modalimage
    Created on : Nov 21, 2022, 2:44:39 PM
    Author     : letua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <style>
          .container{
            width: 500px;
            margin: auto;
            text-align: center;
          }
          #myImg {
              border-radius: 5px;
              cursor: pointer;
              transition: 0.3s;
          }

          #myImg:hover {opacity: 0.7;}

          /* định dạng phần nền của modal */
          .modal {
              display: none; /* mặc định được ẩn đi */
              position: fixed; /* vị trí được cố định */
              z-index: 1; /* ưu tiên hiển thị đầu tiên */
              padding-top: 100px; 
              left: 0;
              top: 0;
              width: 100%; 
              height: 100%; 
              background-color: rgb(0,0,0); 
              background-color: rgba(0,0,0,0.9); 
          }

          /* Phần nội hình ảnh của modal */
          .modal-content {
              margin: auto;
              display: block;
              width: 80%;
              max-width: 700px;
          }

          /* phần caption của modal image */
          #caption {
              margin: auto;
              display: block;
              width: 80%;
              max-width: 700px;
              text-align: center;
              color: #ccc;
              padding: 10px 0;
              height: 150px;
          }

          /* Hiệu ứng chuyển động*/
          .modal-content, #caption {    
              -webkit-animation-name: zoom;
              -webkit-animation-duration: 0.6s;
              animation-name: zoom;
              animation-duration: 0.6s;
          }

          /* Button đóng Modal */
          .close-banner {
              position: absolute;
              top: 60px;
              right: 390px;
              color: red;
              font-size: 40px;
              font-weight: bold;
              transition: 0.3s;
          }

          .close-banner:hover,
          .close-banner:focus {
              color: #bbb;
              text-decoration: none;
              cursor: pointer;
          }
        </style>
    </head>
    <body> 
        <!-- The Modal -->
        <div id="myModal" class="modal">
          <span class="close-banner">&times;</span>
          <img class="modal-content" id="img01">
        </div>
    </body>
    <script>
      // Lấy phần Modal
      var modal = document.getElementById('myModal');

      // Lấy đường dẫn của hình ảnh và gán vào trong phần Modal
      var img = "/img/g11banner.png";
      var modalImg = document.getElementById("img01");
      modal.style.display = "block";
      modalImg.src = img;

      // lấy button span có chức năng đóng Modal
      var span = document.getElementsByClassName("close-banner")[0];

      //Khi button được click, đóng modal
      span.onclick = function() { 
          modal.style.display = "none";
      }
    </script>
</html>