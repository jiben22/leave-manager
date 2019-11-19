$(function () {
    $('.datepickerCalendar').datetimepicker({
        locale:'fr',
        format:'DD/MM/YYYY',
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-chevron-up",
            down: "fa fa-chevron-down",
            previous: "fa fa-chevron-left",
            next: "fa fa-chevron-right",
            today: "fa fa-screenshot",
            clear: "fa fa-trash",
            close: "fa fa-remove"
        },
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });

    $('.datepickerUpdate').datetimepicker({
        locale:'fr',
        format:'DD/MM/YYYY',
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-chevron-up",
            down: "fa fa-chevron-down",
            previous: "fa fa-chevron-left",
            next: "fa fa-chevron-right",
            today: "fa fa-screenshot",
            clear: "fa fa-trash",
            close: "fa fa-remove"
        },
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });
    $(".datepickerUpdate").val('01/01/2020');
});

function initValueDatetimepickerCalendar(date) {
    day = ((date.toDate().getDate()>10) ? '':'0' ) + date.toDate().getDate();
    month = (((date.toDate().getMonth()+1)>10) ? '':'0' ) + (date.toDate().getMonth()+1);
    $(".datepickerCalendar").val(day +"/"+ month +"/"+ date.toDate().getFullYear());
}