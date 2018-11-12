$(function () {
	$('.question-text').hide(300);
	$(document).on('click', '.descr-button', function (e) {
		e.preventDefault()
		$(this).parents('.spoiler').toggleClass("active").find('.descr-text').slideToggle();
	});
});