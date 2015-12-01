$("#loginSubmit").unbind("click").bind("click", function()
{
	if($("#inputEmail").val() == "test")
	{
		window.location.href = "p_landing.html";
	}
	$("#alertmsg").show();
});