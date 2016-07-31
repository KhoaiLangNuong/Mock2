//$(document).ready(function() {
//	$('.t-err').css('display','none');
//	//chi cho nhap ngay thang hop le
//	$('.date-input').inputmask("9999/99/99");
//	$('#item-seq').inputmask("9999");
//	$('.val-input').inputmask('[-]999999');
//
//	$('#item-depo').focus();
//});
//
////reset input search
//$('#reset-inputsearch').click(function(){
//	$('#item-depo').val('');
//	$('#item-ss').val('');
//	$('#item-seq').val('');
//	$('#item-depo').focus();
//	$('#cbb-producer').prop('selectedIndex',0);
//	$('.content').css('display','none'); 
//	$('.foot').css('display','none');
//});
//
////next and prev page
//$('#btn-next').click(function(){
//	var pageNo = parseInt($('#page-no').text(), 10);
//	var pageMax = parseInt($('#page-max').text(), 10);
//	
//	if( pageNo < pageMax ) {
//		pageNo ++;
//		$('#page-no').text(pageNo);
//		$('#page-no-input').val(pageNo);
//	}
//	
//	if(pageNo === pageMax) {
//		$('#btn-next').removeClass('btn-right-active');
//		$('#btn-next').addClass('btn-right');
//	}
//	
//	if(pageNo > 1) {
//		$('#btn-prev').removeClass('btn-left');
//		$('#btn-prev').addClass('btn-left-active');
//	}
//});
//$('#btn-prev').click(function(){
//	var pageNo = parseInt($('#page-no').text(), 10);
//	var pageMax = parseInt($('#page-max').text(), 10);
//	
//	if(pageNo > 1){
//		pageNo --;
//		$('#page-no').text(pageNo);
//		$('#page-no-input').val(pageNo);
//	}
//	
//	if(pageNo === 1) {
//		$('#btn-prev').removeClass('btn-left-active');
//		$('#btn-prev').addClass('btn-left');
//	}
//	
//	if(pageNo < pageMax){
//		$('#btn-next').removeClass('btn-right');
//		$('#btn-next').addClass('btn-right-active');
//	}
//});
//
////Nguyet
////page input number only
//$('#page-no-input').keydown(function (e) {
//    // Allow: backspace, delete, tab, escape, enter ( . with 190 )
//    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
//         // Allow: Ctrl+A, Command+A
//        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
//         // Allow: home, end, left, right, down, up
//        (e.keyCode >= 35 && e.keyCode <= 40)) {
//             // let it happen, don't do anything
//             return;
//    }
//    // Ensure that it is a number and stop the keypress
//    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
//        e.preventDefault();
//    }
//});
////nguyet
//// input only C.up or D.del
//$('.action-up').keyup(function(e) {
//	var data = $(this).val();
//	if(data === '' || data === 'D' || data === 'C'){
//		$(this).css('background','white');
//		$('#message-err').fadeOut(1500);
//	}
//	else {
//    	$(this).css('background','red');
//    	$('#message').text('[S3M0030E] 正しい区を人力してください');
//    	$('#message-err').fadeIn(1500);
//    }
//	
//	var i = 0;
//	var j = 0;
//	var upd = true;
//	var tds;
//	$('#my-table > tbody  > tr').each(function() {
//		if( i%2 === 0){
//			tds = $(this).find('.action-up').val();
//			if(tds !== '' && tds !== 'D' && tds !== 'C'){
//				upd = false;
//			}
//			if(tds === ''){
//				j++;
//			}
//		}
//		i++;
//	});
//
//	if(upd && i/2 !== j){
//		$('#btn-upd').removeClass('but-disable');
//		$('#btn-upd').addClass('but');
//	}
//	else {
//		$('#btn-upd').removeClass('but');
//		$('#btn-upd').addClass('but-disable');
//	}
//});
//
////search click
//$('#btn-search').click(function(){
//	var data = $('#item-seq').val().replace(/\_/g,"");
//	if( data === '0'){
//		$('#message-seq-span').text('[S3M0033E]：ＳＥＱ項目に0以外を入力してください。');
//    	$('#message-seq').fadeIn(1500);
//    	$('#item-seq').css('background','red');
//	}
//});
//$('#item-seq').keyup(function(e) {
//	var data = $(this).val().replace(/\_/g,"");
//	if( data !== '0'){
//    	$('#message-seq').fadeOut(1000);
//    	$('#item-seq').css('background','white');
//	}
//});
//
////btn-up click
//$('#btn-upd').click(function(){
//	//duyet qua tat cac row
//	var i=1;
//	var fun;
//	var $item;
//	var $item36, $item37, $item40, $item41, $item44, $item46, $item42, $item43;
//	$('#tblOne > tbody  > tr').each(function() {
//		if( i%2 !== 0){
//			//row content 1
//			fun = $(this).find('.input-up-del').val();
//			$item36 = $(this).find('.item36');
//			$item37 = $(this).find('.item37');
//		}
//		else {
//			$item40 = $(this).find('.item40');
//			$item41 = $(this).find('.item41');
//			$item42 = $(this).find('.item42');
//			
//			$item43 = $(this).find('.item43');
//			$item44 = $(this).find('.item44');
//			$item46 = $(this).find('.item46');
//			
//			//row content 2
//			$item = $.fn.checkValidRow($item36, $item37, $item40, $item41, $item42, $item43, $item44, $item46);
//			if(fun === 'C' && $item !== null){
//				//err 
//				$item.css('background','red');
//				$item.focus();
//				$('#message-valid-tbl').fadeIn(1500);
//				return false;
//			}
//		}
//		i++;
//	});
//});
//(function( $ ) {
//	
//    $.fn.checkValidRow = function($item36, $item37, $item40, $item41, $item42, $item43, $item44, $item46) {
//        return $.fn.checkValidItem($item36, 26) !== null ? $.fn.checkValidItem($item36, 26)
//        			: $.fn.checkValidItem($item37, 30) !== null ? $.fn.checkValidItem($item37, 30)
//        			: $.fn.checkValidItem($item40, 10) !== null ? $.fn.checkValidItem($item40, 10)
//        			: $.fn.checkValidItem($item41, 15) !== null ? $.fn.checkValidItem($item41, 15)
//        			: $.fn.checkValidItemDate($item42) !== null ? $.fn.checkValidItemDate($item42)
//        			: $.fn.checkValidItemNull($item43) !== null ? $.fn.checkValidItemNull($item43)
//        			: $.fn.checkValidItem($item44, 2) !== null ? $.fn.checkValidItem($item44, 2)
//        			: $.fn.checkValidItem($item46, 5);
//    };
// 
//}( jQuery ));
//
//(function( $ ) {
//	
//    $.fn.checkValidItem = function($item, length) {
//    	var $checked = null;
//    	if($item.val().length > length){
//        	//-> show message 
//			$('#message-valid-tbl').find('span').text('[S3M0033E]：max length。' + length);
//        	$checked = $item;
//        }
//        else if($item.val().replace(/\s/g,'') === ''){
//        	$('#message-valid-tbl').find('span').text('[S3M0033E]：not null。');
//        	//$item1.css('background','red');
//        	$checked = $item;
//        }
//    	return $checked;
//    };
//    
//}( jQuery ));
//
//(function( $ ) {
//	
//    $.fn.checkValidItemDate = function($item) {
//    	var $checked = null;
//    	if( !Inputmask.isValid($item.val(), { alias: "yyyy/mm/dd"}) ){
//    		$checked = $item;
//    		$('#message-valid-tbl').find('span').text('[S3M0033E]：Date not valid。');
//    	}
//    	return $checked;
//    };
//    
//}( jQuery ));
//
//(function( $ ) {
//	
//    $.fn.checkValidItemNull = function($item) {
//    	var $checked = null;
//    	if( $item.val() === ''  ){
//    		$checked = $item;
//    		$('#message-valid-tbl').find('span').text('[S3M0033E]：Not null。');
//    	}
//    	return $checked;
//    };
//    
//}( jQuery ));
////keyup
//$('.item36').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 26 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item37').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 30 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item40').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 10 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item41').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 15 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item44').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 2 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item46').keyup(function(e){
//	var val = $(this).val();
//	if(val.length <= 5 && val.replace(/\s/g,'') !== '') {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item42').keyup(function(e){
//	var val = $(this).val();
//	//var c = Inputmask.isValid("2303/19/73", { alias: "yyyy/mm/dd"});
//	if( Inputmask.isValid( val, { alias: "yyyy/mm/dd"}) ) {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});
//$('.item43').keyup(function(e){
//	var val = $(this).val();
//	//var c = Inputmask.isValid("2303/19/73", { alias: "yyyy/mm/dd"});
//	if( val !== '' ) {
//		$(this).css('background','white');
//		$('#message-valid-tbl').fadeOut(1000);
//	}
//});

$(document).ready(function(){
	$('.action').keyup(function() {
		var data = $(this).val();
		console.log("aaaaaaaaaaaaaa");
		if(data === '' || data === 'D' || data === 'C'){
			$(this).css('background','white');
			$('#message-err').fadeOut(1500);
			$("#update").prop("disabled",false);
			if(data===''){
				$("#update").prop("disabled",true);
			}
		}
		else {
	    	$(this).css('background','red');
	    	$('#message').text('[S3M0030E] 正しい区を人力してください');
	    	$('#message-err').fadeIn(1500);
	    	$("#update").prop("disabled",true);
	    }
		
		var i = 0;
		var j = 0;
		var upd = true;
		var tds;
		$('#my-table > tbody  > tr').each(function() {
			if( i%2 === 0){
				tds = $(this).find('.action-up').val();
				if(tds !== '' && tds !== 'D' && tds !== 'C'){
					upd = false;
				}
				if(tds === ''){
					j++;
				}
			}
			i++;
		});

		if(upd && i/2 !== j){
			$('#btn-upd').removeClass('but-disable');
			$('#btn-upd').addClass('but');
		}
		else {
			$('#btn-upd').removeClass('but');
			$('#btn-upd').addClass('but-disable');
		}
	});
	/////////////////////////////////////////////////////////////////////////////////////////////////////
});



