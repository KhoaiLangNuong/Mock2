<!--
 * updateListStudio.jsp
 *
 * Version 1.0
 *
 * Date: 29-07-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 29-07-2016       	NguyetNT6          Create
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>update</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/mycss/update.css">
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bussiness.t.js"></script>
<script type="text/javascript">
	 $(document).ready(function() {
		$("#update").on("click", function() {
			var action = $(".action");
			var sysfi_key = $(".sysfi_key");
			var sysfi_data = $(".sysfi_data");
			var arrayStudio = new Array();
			for (var i = 0; i < action.length; i++) {
				var studio = new Object();
				studio.action = $(action[i]).val();
				studio.sysfi_key = $(sysfi_key[i]).val();
				studio.sysfi_data = $(sysfi_data[i]).val();
				arrayStudio.push(studio);
			}
			var myJSON = JSON.stringify(arrayStudio);
			console.log("JSON" + myJSON);
			$.ajax({
				type : "POST",
				url : "update-list-studio.do",
				data : "submit=update&dataUpdate=" + myJSON,
				dataType : "json",
				success : function(response) {
					var result = response.result;
					$("#message").html(result);
					if ("success" == result) {
						window.location.href = "update-list-studio.do";
					}
					if("failed_validate"==result){
						var jsonArrayError= response.jsonArray;
						for(var i=0; i<jsonArrayError.length; i++){
							var jsonObjectError=jsonArrayError[i];
							if("false"==jsonObjectError.validate){
								$("#message").html(jsonObjectError.message);
								$("#message-err").fadeIn();
								var trs=$("#my-table tr");
								for(var j=0; j<trs.length; j++ ){
									$(trs[j]).removeClass("error");
								}
								$(trs[i+1]).addClass("error");
								break;
							}
						}
					}
				},
				error : function(error_message) {
					alert("error " + error_message);
				}
			});
		});
		$("#btn-prev").on("click",function(){
			var currentPage= $("#currentPage").val();
			var totalPage= $("#totalPage").val();
			if(currentPage==1){
				return;
			}
			$("#inputCurrentPage").val(""+(currentPage-1));
			$("#btnGoPageAt").click();
		});
		$("#btn-next").on("click",function(){
			var currentPage= $("#currentPage").val();
			var totalPage= $("#totalPage").val();
			if(currentPage==totalPage){
				return;
			}
			$("#inputCurrentPage").val(""+(parseInt(currentPage)+1));
			$("#btnGoPageAt").click();
		});

		$("#update").prop("disabled",true);
	});

</script>
</head>
<body>
	<div class="container">
		<div class="row" id="div-background">
			<div class="col-lg-12" id="title">
				<div class="pull-left">
					<h4>AUTA311</h4>
				</div>
				<div class="col-lg-4"></div>
				<div class="col-lg-3">
					<h4 style="font-weight: bold;">メーカー名マスタ更新</h4>
				</div>
				<div class="pull-right">
					<h4>2016年07月29日</h4>
				</div>
			</div>
			<html:form action="/update-list-studio.do" method="post">
			<div class="col-lg-12 errorMessage">
				<div class="row t-err" id="message-err">
					<p>
						<img src="img/alert.png"
							style="position: relative; width: 15px; height: 15px; top: -2px;" >
						<span id="message"></span>
					</p>
				</div>
			</div>	
			<div class="col-lg-12 search">
				<input type="text"
					style="color: white; background-color: #1A75C6; border: 2px solid #8DBBD6;width: 120px;"
					value="メーカー・コード" disabled="disabled" > 
					<html:text property="contentSearch" style="border: 1px solid #8DBBD6;width: 45px;" maxlength="2"></html:text>
			</div>
			<div class="col-lg-12 btnGroup ">
				<div class="pull-right" style="margin-top: 15px;">
					<html:submit property="submit" styleClass="btna">検索(S)</html:submit>
					<input type="button" value="キャンセル( C )" class="btna">
					<html:link action="/add-list-studio.do"><input type="button" class="btna" value="新規登録(N)"></html:link>
				</div>
			</div>
			</html:form>
			<div class="col-lg-12" id="page">
				<div class="panel-heading">
					<div class="row">
						<html:hidden styleId="currentPage" property="currentPage" name="listUpdateStudioForm"/>
						<html:hidden styleId="totalPage" property="totalPage" name="listUpdateStudioForm"/>
						<html:form action="/update-list-studio" method="post">
							<div class="col-lg-12">
								<label for="label-center1"
									style="color: white; margin-right: 20px;"> <bean:write
										name="listUpdateStudioForm" property="currentPage" /> /<bean:write
										name="listUpdateStudioForm" property="totalPage" /> ぺ一ジ
								</label>
								<bean:define id="currentPage" property="currentPage" name="listUpdateStudioForm"></bean:define>
								<bean:define id="totalPage" property="totalPage" name="listUpdateStudioForm"></bean:define>
								<logic:equal value="1" name="currentPage">
									<img id="btn-prev" alt="anh" src="pictures/righthidden.png" height="25px" width="25px" style="margin-right: -7px; cursor: not-allowed;margin-top: 1px;">
								</logic:equal>
								<logic:notEqual value="1" name="currentPage">
									<img id="btn-prev" alt="anh" src="pictures/right.png" height="25px" width="25px" style="margin-right: -7px; cursor: pointer;margin-top: 1px;">
								</logic:notEqual>
								<logic:equal value="${ totalPage}" name="currentPage">
									<img id="btn-next" alt="anh" src="pictures/lefthidden.png" height="25px" width="25px" style="cursor: not-allowed">
								</logic:equal> 
								<logic:notEqual value="${ totalPage}" name="currentPage">
									<img id="btn-next" alt="anh" src="pictures/left.png" height="25px" width="25px" style="cursor: pointer">
								</logic:notEqual>
								<label for="label-center3" style="color: white; margin-left: 20px;">ぺ一ジ </label>
								<html:text property="currentPage" style="width:30px; margin-left:20px;" styleId="inputCurrentPage"></html:text>
								<html:submit styleId="btnGoPageAt" property="submitNumberPager" styleClass="btna"
									style="margin-left:20px;">表示</html:submit>
								<label for="label-center1"
									style="color: white; margin-left: 20px;"> 表示件数 </label>
								<html:select property="totalRecord" style="margin-left:20px;">
									<html:option value="5">5</html:option>
									<html:option value="10">10</html:option>
									<html:option value="-1">All</html:option>
								</html:select>
								<html:submit property="submit" styleClass="btna"
									style="margin-left:20px">表示</html:submit>
							</div>
						</html:form>
					</div>
				</div>
			</div>
			<table class="table" id="my-table">
				<thead>
					<tr style="background-color: #1A75C6">
						<th style="width: 8%">区</th>
						<th style="width: 20%">メーカー・コード</th>
						<th>メーカー名</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="studio" property="listStudio"
						name="listUpdateStudioForm">
						<tr>
							<td><html:text styleClass="action" property="action"
									name="studio" style="width:30px;" maxlength="1" ></html:text></td>
							<td><html:text styleClass="sysfi_key" property="sysfiKey"
									name="studio" readonly="true" style="width:50px; cursor:not-allowed ;background-color : #ddd" ></html:text></td>
							<td><html:text styleClass="sysfi_data" property="sysfiData"
									name="studio"></html:text></td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			<html:form action="/update-list-studio.do" >
				<div class="col-lg-12 btnGroup" style="height: 120px;">
					<div class="col-lg-2" style="margin-top: 15px;">区：(C.変更 D.削除)</div>
					<div class="pull-right" style="margin-top: 15px;">
						<input id="update" type="button" value="更新(U)" class="btna">
						<html:link action="/update-list-studio.do"><input type="button" value="クリアー(R)" class="btna"></html:link> 
						<html:submit styleClass="btna" property="submit" onclick="confirm('あなたがいないデータをExcelにエクスポートしますか')">エクスポート(E)</html:submit>	
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>