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
	    	$('#message').text(' 正しい区を人力してください');
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
});



