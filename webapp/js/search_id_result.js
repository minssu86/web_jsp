$(function(){
    let urlstr = window.location.href;
    let url = new URL(urlstr);
    let id = url.searchParams.get("id");
    let date = url.searchParams.get("date");
    if(id!='' && date!=''){
        $('#h3-show-id').text(id);
        $('#p-show-date').text(date);
    }
});