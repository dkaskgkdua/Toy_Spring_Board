<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../board/header.jsp" />
<meta charset="UTF-8">
<script>
function go2(page){
	   var search_select = $('#viewcount').val();
	   var search_word = $('#search_word').val();
	   var limit = $('#viewcount3').val();
	   var data = "limit=" + limit + "&page=" + page+"&search_select="+search_select+"&search_word="+search_word;
	   ajax2(data);
}
function setPaging(href, digit){
	   output += "<li class=page-item>";
	   gray="";
	   if(href==""){
	      gray = "gray";
	   }
	   anchor = "<a class='page-link " + gray + "'" + href + ">" + digit + "</a></li>";
	   output += anchor;
	}
/* 멤버 상세조회 ajax */
function getView(member_id) {
	
	$.ajax({
		type: "post",
		url : 'member_info.net',
		data : {"id" : member_id},
		dataType:"json",
		success:function(rdata) {
				$("#updateMember_id").val(rdata.id);
				$("#updateMember_pass").val(rdata.password);
				$("#updateMember_name").val(rdata.name);
				$("#updateMember_age").val(rdata.age);
				$("#updateMember_email").val(rdata.email);
				var gender = rdata.gender;
				if(gender == 1) {
					$('#Umale').attr('checked','checked');
				} else {
					$('#Ufemale').attr('checked','checked');
				}
		}, 
		error : function() {
        	console.log('에러')
        }
	});
};
/* 멤버 정보 수정 ajax*/
function updateMember() {
	var allData = $('#updateMember_form').serialize();
	$.ajax({
		type:"post",
		url : 'updateProcess.net',
		data : allData,
		success:function() {
			$('#member_view_Modal').modal("hide");
			go2(1);
		}, error : function() {
			console.log('에러')
        }
	})
}
/* 회원 목록 필터링 ajax */
function ajax2(data) {
	console.log(data)
	output="";
	$.ajax({
            type:"POST",
            data: data,
            url : "member_listAjax.net",
            dataType:"json",
            cache:false,
            success:function(data){
                    $("#viewcount3").val(data.limit);
                    $('#viewcount').val(data.search_select).prop("selected", true);
                    $('#search_word').val(data.search_word);
                    $(".t1").find("font").text(data.listcount+"명");
                    
                    if(data.listcount>0) { //총갯수가 1개 이상인 경우
                    	$('.tb1').remove();
                    	var num = data.listcount - (data.page-1) * data.limit;
                    	console.log(num)
                    	output = "<tbody class = 'tb1'>";
                    	$(data.memberlist).each(   
                    			function(index, item) {
                    				output += '<tr><td>' + (num--) + '</td>';
                    				output += '<td><div><a><button type="button" class="btn memberDetail"'
                    					  + ' data-toggle="modal" data-target="#member_view_Modal">' + item.id +'</button></a></div></td>';
                    				output += "<td><div>" + item.name +"</td></div>";
                    				if(item.id != 'admin') {
                    					output += '<td><a href="member_delete.net?id=' + item.id +'" style="color:red" >삭제</a></td>';
                    				}
                    			}
                    	);
                    	output += '</tbody>'
                    	$('.t1').append(output) //table 완성
                    	$(".pa1").empty();  //페이징 처리
                    	output= "";
                    	digit = '이전&nbsp;'
                    	href="";
                    	if(data.page >1) {
                    		href = 'href=javascript:go2(' + (data.page - 1) + ')';
                    	}
                    	setPaging(href, digit);

                    	for (var i = data.startpage; i<= data.endpage; i++) {
                    				digit = i;
                    				href= "";
                    				if(i != data.page){
                    					href='href=javascript:go2('+i+')';
                    				}
                    				setPaging(href, digit);
                    			}
                    			digit = '다음&nbsp;';
                    			href="";
                    			if(data.page < data.maxpage){
                    				href='href=javascript:go2('+(data.page+1)+')';
                    			}
                    			setPaging(href, digit);

                    			$('.pa1').append(output)
                    } //if(data.listcount) end
                    else {
                    	$(".t1").remove();
                    	$(".block1").remove();
                    	$(".container1").append("<font size=5>등록된 글이 없습니다.</font>");
                    }
            }, //success end
            error : function() {
            	console.log('에러')
            }
	}); //ajax end
} //function ajax end

	$(function() {
		var sel = "${search_select}";
		$("#viewcount").val(sel).prop("selected", true);
		
		 $("#viewcount3").change(function(){
		      go2(1); 
		   }) //change end
		   $(".del").click(function(event) {
				var answer = confirm("정말 삭제하시겠습니까?");
				if(!answer) {
					event.preventDefault();
				}
			});
		 $('#search_btn').click(function() {
				if($("#search_word").val() == '') {
					alert("검색어를 입력하세요");
					return false;
				}
			});
		 $("#viewcount").change(function() {
				selectedValue = $(this).val();
				$("#search_word").val('');
				if(selectedValue=='3') {
					$("#search_word").attr("placeholder", "여 또는 남을 입력하세요");
				}
			});
		 $(".memberDetail").click(function() {
				var member_id = $(this).text();
				getView(member_id);
			});
		 $("#updateMember_button").click(function() {
				updateMember();
			})
	})
</script>
<c:if test="${listcount > 0 }">
	<div class="center-block">
		<form class="search_member size" action="member_list.net">
			<div class="input-group select-wrapper">

				<select id="viewcount" name="search_select">
					<option value="0">ID</option>
					<option value="1" selected>이름</option>
					<option value="2">이메일</option>
					<option value="3">성별</option>
				</select> <input id="search_word" name="search_word" type="text"
					class="form-control" placeholder="Search" value="${search_word}">
				<button id="search_btn" class="btn btn-primary small" type="submit">검색</button>

			</div>
		</form>
	</div>

	<%-- 회원이 있는 경우 --%>
	<div class="container container1">

		<table class="table t1">
			<caption>회원 목록</caption>
			<thead>
				<tr>
					<th colspan="2"><select class="form-control" id="viewcount3">
							<option value="1">1</option>
							<option value="3">3</option>
							<option value="5">5</option>
							<option value="7">7</option>
							<option value="10" selected>10</option>
					</select></th>
					<th colspan="1"></th>
					<th colspan="1"><font size=2>${listcount }명</font></th>
				</tr>
				<tr>
					<th width="20%">번호</th>
					<th width="35%">아이디</th>
					<th width="25%">이름</th>
					<th width="20%">삭제</th>
				</tr>
			</thead>
			<tbody class="tb1">
				<c:set var="num" value="${listcount-(page-1)*10}" />
				<%-- listAction에 limit 변경시 곱하는 값도 같이 변경해야함 --%>
				<c:forEach var="m" items="${memberlist}">
					<tr>
						<td><c:out value="${num }" /> <%-- num 출력 --%> <c:set
								var="num" value="${num-1}" /> <%-- num = num-1 의미함 --%></td>
						<td>
							<div>
								<a><button type="button" class="btn memberDetail"
										data-toggle="modal" data-target="#member_view_Modal">${m.id}</button></a>
								<%--
					 <a href="member_info.net?id=${m.id }">
					 	${m.id}
					 </a>
					  --%>
							</div>
						</td>
						<td>
							<div>${m.name}</div>
						</td>
						<td><c:if test="${m.id != 'admin'}">
								<a href="member_delete.net?id=${m.id}" style="color: red">삭제</a>
								<%-- onclick = "delchk(); 붙여도 가능 --%>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="center-block block1">
		<div class="row">
			<div class="col huWidth">
				<ul class="pagination pa1 huWidth">
					<c:if test="${page <= 1 }">
						<li class="page-item"><a class="page-link" href="#">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1 }">
						<li class="page-item"><a
							href="member_list.net?page=${page-1}&search_select=${search_select}&search_word=${search_word}"
							class="page-link">이전</a>&nbsp;</li>
					</c:if>

					<c:forEach var="a" begin="${startpage }" end="${endpage }">
						<c:if test="${a == page }">
							<li class="page-item"><a class="page-link current"
								href="member_list.net?page=${a }&search_select=${search_select}&search_word=${search_word}">${a }</a>
							</li>
						</c:if>
						<c:if test="${a != page }">
							<li class="page-item"><a
								href="member_list.net?page=${a }&search_select=${search_select}&search_word=${search_word}"
								class="page-link">${a }</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item"><a class="page-link" href="#">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a
							href="member_list.net?page=${page+1}&search_select=${search_select}&search_word=${search_word}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

</c:if>
<!--  회원이 없는 경우 -->
<c:if test="${listcount == 0 }">
	<font size=5>등록된 회원이 없습니다.</font>
</c:if>

<!--  회원 상세정보 -->
<div class="modal" id="member_view_Modal" tabindex="-1" role="dialog" aria-labelledby="viewLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header" style="text-align: center">
				<h4 class="modal-title" id="viewLabel" style="color: black">회원
					상세 정보</h4>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form id="updateMember_form" name="joinform" method="post">
					<fieldset>
						<div class="form-group">
							<label for="updateMember_id">아이디</label> <input type="text"
								class="form-control" id="updateMember_id" placeholder="Enter id"
								name="id" readOnly>
						</div>
						<div class="form-group">
							<label for="updateMember_pass">비밀번호</label> <input
								type="password" class="form-control" id="updateMember_pass"
								placeholder="Enter password" name="password" required
								maxLength="20">
						</div>
						<div class="form-group">
							<label for="updateMember_name">이름</label> <input type="text"
								id="updateMember_name" class="form-control"
								name="name" placeholder="Enter name" required
								maxLength="15">
						</div>
						<div class="form-group">
							<label for="updateMember_age">나이</label> <input type="text"
								id="updateMember_age" class="form-control"
								name="age" value="" placeholder="Enter age"
								required>
						</div>
						<div class="form-group">
							<div>
								<label for="male">성별</label>
							</div>
							<input type="radio" id="Umale" name="gender"
								value="1"> <label for="male">남</label> <input
								type="radio" id="Ufemale" name="gender" value="2">
							<label for="female">여</label>
						</div>
						
						
						<div class="form-group">
							<label for="updateMember_email">이메일</label> <input
								type="email" id="updateMember_email" class="form-control"
								name="email" placeholder="Enter email"
								required>
						</div>
						
						

						<button class="update_button" id="updateMember_button"
							type="button">수정</button>
						<button class="update_button cancelbtn" data-dismiss="modal" type="button">돌아가기</button>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
</div>
</body>
</html>