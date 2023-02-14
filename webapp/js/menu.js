$(function() {
	//.click(function(){return false;})
	$('ul>li:eq(2)')
	.mouseenter(function() {
		$(this).find('ol').show(200);
	}).mouseleave(function() {
		$(this).find('ol').hide();
	})
});