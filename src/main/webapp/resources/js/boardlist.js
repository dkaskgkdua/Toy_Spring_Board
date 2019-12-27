function go(page) {
   var limit = $('#viewcount').val();
   var search_select = $("#search_select").val();
   var search_text = $("#search_text").val();
   var data = "limit=" + limit + "&page=" + page + "&search_select=" + search_select
         + "&search_text=" + search_text;
   ajax(data);
}

function setPaging(href, digit) {
   output += "<li class=page-item>";
   gray = "";
   if (href == "") {
      gray = "gray";
   }
   anchor = "<a class='page-link " + gray + "'" + href + ">" + digit
         + "</a></li>";
   output += anchor;
}

function ajax(data) {
   console.log(data)
   output = "";
   $.ajax({
      type : "POST",
      data : data,
      url : "BoardListAjax.bo",
      dataType : "json",
      cache : false,
      success : function(data) {
         $("#viewcount").val(data.limit); // 한 페이지에 나타낼 글 수
         $("table").find("font").text("글 개수 : " + data.listcount); // 총 리스트
                                                      // 수

         if (data.listcount > 0) { // 총갯수가 1개 이상인 경우
            $("tbody").remove();
            var num = data.listcount - (data.page - 1) * data.limit; // page
                                                         // = 1
            console.log(num)
            output = "<tbody>";
            $(data.boardlist).each(
                  // <c:forEach var ="b" items="${boardlist}">
                  function(index, item) {
                     output += '<tr><td>' + (num--) + '</td>'
                     blank_count = item.board_RE_LEV * 2 + 1;
                     blank = '&nbsp;';
                     for (var i = 0; i < blank_count; i++) {
                        blank += '&nbsp;&nbsp;'
                     }
                     img = "";
                     if (item.board_RE_LEV > 0) {
                        img = "<img src='resources/Image/AnswerLine.gif'>";
                     }

                     output += "<td><div>" + blank + img
                     output += '<a href="./BoardDetailAction.bo?num='
                           + item.board_NUM + '&page=' + data.page
                           + '">'

                     output += item.board_SUBJECT + '</a></div></td>'
                     output += '<td><div>' + item.board_NAME
                           + '</div></td>'
                     output += '<td><div>' + item.board_DATE
                           + '</div></td>'
                     output += '<td><div>' + item.board_READCOUNT
                           + '</div></td></tr>'
                  })
            output += '</tbody>'

            $('table').append(output) // table 완성

            $(".pagination").empty(); // 페이징 처리
            output = "";

            digit = '이전&nbsp;'
            href = "";
            if (data.page > 1) {
               href = 'href=javascript:go(' + (data.page - 1) + ')';
            }
            setPaging(href, digit);

            for (var i = data.startpage; i <= data.endpage; i++) {
               digit = i;
               href = "";
               if (i != data.page) {
                  href = 'href=javascript:go(' + i + ')';
               }
               setPaging(href, digit);
            }
            digit = '다음&nbsp;';
            href = "";
            if (data.page < data.maxpage) {
               href = 'href=javascript:go(' + (data.page + 1) + ')';
            }
            setPaging(href, digit);

            $('.pagination').append(output)
         } // if(data.listcount) end
         else {
            $(".container table").remove();
            $(".center-block").remove();
            $(".container").append("<font size=5>등록된 글이 없습니다.</font>");
         }
      }, // success end
      error : function() {
         console.log('에러')
      }
   }); // ajax end
}; // function ajax end


function show() {
	if ($('#filevalue').text() == '') {
		// 파일 이름이 있는 경우 remove 이미지를 보이게 하고 없는 경우는 보이지 않게 한다.
		$(".remove").css('display', 'none');
	} else {
		$(".remove").css('display', 'inline-block');
	}
};
function getList() {
	$.ajax({
		type:"POST",
		url : "CommentList.bo",
		data : {"BOARD_RE_REF" : $("#BOARD_RE_REF").val()},
		dataType : "json",
		success:function(rdata) {
			if(rdata.length >0) {
				
			}
		}
		
	})
}

$(function() {
	$("form")
			.submit(
					function() {
						if ($.trim($("#board_pass").val()) == "") {
							alert("비밀번호를 입력하세요");
							$("#board_pass").focus();
							return false;
						}
						if ($.trim($("#board_subject").val()) == "") {
							alert("제목을 입력하세요");
							$("#board_subject").focus();
							return false;
						}
						if ($.trim($("#board_content").val()) == "") {
							alert("내용을 입력하세요");
							$("#board_content").focus();
							return false;
						}
						
					});
	$("#comment table").hide();
	$('#upfile').change(function() {
		var inputfile = $(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length - 1]);
		show();
	});

	$('.remove').click(function() {
		$('#filevalue').text('');
		$(this).css('display', 'none');
		$("input[name=BOARD_ORIGINAL]").val('');
		$("input[name=BOARD_FILE").val('');
	});

	// 남은 글자 수 표시
	$("#board_content").keyup(function() {
		var content = $(this).val();
		$("#counter").html(content.length + '/500');
		
		if(content.length >= 500){
			$(this).val(content.substr(0, 500));
			$("#counter").css('background', 'red');
		}else{
			$("#counter").css('background', 'rgba(255, 0, 0, 0.5)');
		}
	});
	 $("#viewcount").change(function() {
	      go(1); // 보여줄 페이지를 1페이지로 설정한다.
	   }) // change end
	   $('#addBoard_Button').click(function() {
	      location.href = "BoardWrite.bo";
	   });
	   
	   $("#search_btn").click(function(){
	      go(1);
	   })
});
