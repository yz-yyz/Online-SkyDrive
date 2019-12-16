$(function () {
    function loadfilselist() {
    	/*注释*/
        $.ajax({
            url: 'Filelist',
            type: 'GET',
            dataType: 'text',
            async: true,
            timeout: 50000,
            success: function (data) {
                document.getElementById('files').innerHTML = data;
                $('.file').each(function (index, element) {
                    // element == this
                    var filename = $('.file').eq(index).children('.filename').text();
                    filename = filename.trim();
                   // $('.file').eq(index).children().filter('.links').attr("href", "DownLoad?" + filename);
                  //  $('.file').eq(index).children().filter('.links1').attr("href", "Delete?" + filename);
                });

            },
            error: function () {
                var errorMsg = '无法获取内容';
                document.getElementById('files').innerHTML = errorMsg;
            }
        });
    }
  /*  $('.links1').click(function (e) { 
        loadfilselist();
       
   });*/
    $('.leftArea').click(function () { 
        var n = $('.leftArea ').index(this);
        $('.rightArea').removeClass('selected');
        $('.rightArea').eq(n).addClass('selected');;
        $('.leftArea').removeClass('select');
        $(this).addClass('select');
    });
   
    $('#download').click(function () { 
    	 loadfilselist();
        
          $('.file').each(function (index, element) {
              
                var filename = $('.file').eq(index).children('.filename').text();
                filename=filename.trim();

             // $('.file').eq(index).children().filter('.links').attr("href", "DownLoad?"+filename);
             // $('.file').eq(index).children().filter('.links1').attr("href", "Delete?"+filename);
           });
    });
    /*$('.links1').click(function (e) { 
        $.ajax({
            url: 'Filelist',
            type: 'GET',
            dataType: 'text',
            async: true,
            timeout: 50000,
            success: function (data) {
                document.getElementById('files').innerHTML = data;
                $('.file').each(function (index, element) {
                    // element == this
                    var filename = $('.file').eq(index).children('.filename').text();
                    filename = filename.trim();
                    

                    //$('.file').eq(index).children().filter('.links').attr("href", "DownLoad?" + filename);
                  //  $('.file').eq(index).children().filter('.links1').attr("href", "Delete?" + filename);
                });

            },
            error: function () {
                var errorMsg = '无法获取内容';
                document.getElementById('files').innerHTML = errorMsg;
            }
        });
        
    });*/
    $('.record').mouseenter(function () { 
        $(this).children.css("color","#09aaff");
    });
    $('.record').mouseleave(function () {   
        $(this).children.css("color", "blank");
    });
    $('#checkrecords').click(function (e) { //记录
        $.ajax({
            url: 'ReturnRecord',
            type: 'GET',
            dataType: 'text',
            async: true,
            timeout: 50000,
            success: function (data) {
                document.getElementById('records').innerHTML = data;

            },
            error: function () {
                var errorMsg = '无法获取记录';
                document.getElementById('records').innerHTML = errorMsg;
            }
        });
        
    });
 
   
});
 