<!--
 * addListStudio.jsp
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
<title>add list studio</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/mycss/update.css">
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		var error=$("#error").val();
		if(!error==""){
			var jsonObjectError= JSON.parse(error);
			var validate=jsonObjectError.validate;
			var jsonArrayError=jsonObjectError.jsonArray;
			if("false"==validate){
				for(var i=0; i<jsonArrayError.length; i++){
					var jsonObjectErrorChild= jsonArrayError[i];
					if("false"==jsonObjectErrorChild.validate){
						console.log(""+jsonObjectErrorChild.message);
						$("#showErrorMessage").html(jsonObjectErrorChild.message);
						var trs=$("#my-table tr");
						$(trs[i+1]).addClass("error");
						break;
					}
				}
			}
		}
	});
	function clearData(){
		var inputData= $(".inputData");
		for(var i=0; i<inputData.length; i++){
			$(inputData[i]).val("");
		}
	}
	
</script>
</head>
<body>
	<div class="container">
		<div class="row" id="div-background">
			<div class="col-lg-12" id="title">
				<div class="pull-left">
					<h4>AUTA312</h4>
				</div>
				<div class="col-lg-4"></div>
				<div class="col-lg-3">
					<h4 style="font-weight: bold;">メーカー名マスタ登録</h4>
				</div>
				<div class="pull-right">
					<h4>YYYY年MM月DD日</h4>
				</div>
  
			</div>
			<div class="col-lg-12 errorMessage" style="padding-top: 15px; ">
			<div class="row t-err" id="message-err">
					<p>
						<img src="img/alert.png"
							style="position: relative; width: 15px; height: 15px; top: -2px;" >
						<span id="showErrorMessage"></span>
					</p>
				</div>
			</div>
			<html:form action="/add-list-studio" method="post">
			<table class="table" id="my-table">
				<thead>
					<tr style="background-color: #1A75C6">
						<th style="width: 8%"></th>
						<th style="width: 20%">メーカー・コード <span style="color: red;">(*)
						</span></th>
						<th>メーカー名</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><h5>1</h5></td>
						<td><html:text property="listKey[0]" name="studioForm" styleClass="inputData"></html:text>
						</td>
						<td><html:text property="listData[0]" name="studioForm" styleClass="inputData"></html:text>
						</td>
					</tr>
					<tr>
						<td><h5>2</h5></td>
						<td><html:text property="listKey[1]" name="studioForm" styleClass="inputData"></html:text>
						</td>
						<td><html:text property="listData[1]" name="studioForm" styleClass="inputData"></html:text>
						</td>

					</tr>
					<tr>
						<td><h5>3</h5></td>
						<td><html:text property="listKey[2]" name="studioForm" styleClass="inputData"></html:text>
						</td>
						<td><html:text property="listData[2]" name="studioForm" styleClass="inputData"></html:text>
						</td>

					</tr>
						<tr>
							<td><h5>4</h5></td>
							<td><html:text property="listKey[3]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[3]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>5</h5></td>
							<td><html:text property="listKey[4]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[4]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>6</h5></td>
							<td><html:text property="listKey[5]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[5]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>7</h5></td>
							<td><html:text property="listKey[6]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[6]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>8</h5></td>
							<td><html:text property="listKey[7]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[7]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>9</h5></td>
							<td><html:text property="listKey[8]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[8]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<tr>
							<td><h5>10</h5></td>
							<td><html:text property="listKey[9]" name="studioForm" styleClass="inputData"></html:text>
							</td>
							<td><html:text property="listData[9]" name="studioForm" styleClass="inputData"></html:text>
							</td>
						</tr>
						<html:hidden styleId="error" property="error"/>
					</tbody>
			</table>

			<div class="col-lg-12 btnGroup">
				<div class="pull-right" style="margin-top: 15px;">
					<html:submit styleClass="btna" property="submit" value="登録(U)"></html:submit>
					<html:button styleClass="btna" property="submit" value="クリアー(C)" onclick="clearData()"></html:button>
					<html:link action="/update-list-studio.do">
									<input class="btna" type="button" value="キャンセル(K)"> </html:link>
				</div>
			</div>
			</html:form>
		</div>
	</div>
</body>
</html>