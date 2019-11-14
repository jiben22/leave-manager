$(function () {

    var d = new Date();

    var month = d.getMonth()+1;
    var day = d.getDate();

    var output = (day<10 ? '0' : '') + day + '/' + (month<10 ? '0' : '') + month + '/' + d.getFullYear()

    $(".datepicker").val(output + " 00:00");
    $('.datepicker').datetimepicker({
        locale:'fr',
        format:'DD/MM/YYYY HH:00',
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            /*up: "fa fa-chevron-up",
            down: "fa fa-chevron-down",*/
            previous: "fa fa-chevron-left",
            next: "fa fa-chevron-right",
            today: "fa fa-screenshot",
            clear: "fa fa-trash",
            close: "fa fa-remove"
        },
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        },
        enabledHours: [0, 12]
    });
});