$(function () {
   
    $('.leftArea').click(function () { 
        var n = $('.leftArea ').index(this);
        $('.rightArea').removeClass('selected');
        $('.rightArea').eq(n).addClass('selected');;
        $('.leftArea').removeClass('select');
        $(this).addClass('select');
    });
   
    $('#download').click(function () {
          
          $('.file').each(function (index, element) {
               // element == this
                var filename = $('.file').eq(index).children('.filename').text();
                filename=filename.trim();
                filename= "DownLoad?"+filename
                 
                $('.file').eq(index).children().filter('a').attr("href", filename);
           });
    });
    $('.record').mouseenter(function () { 
        this.children().css("color","#09aaff");
    });
    $('.record').mouseleave(function () { 
        this.children().css("color", "blank");
    });
 
   
});
 