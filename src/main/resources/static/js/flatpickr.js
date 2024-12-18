// 日付の最大値を設定
let maxDate = new Date();
maxDate.setMonth(maxDate.getMonth() + 3);

// データ属性から regularHoliday を取得し、文字列を配列に変換
const regularHoliday = document.getElementById('fromReservationDate').dataset.regularHoliday.split(',').map(day => day.trim());

// 曜日を変換するためのマッピング
const dayMap = {
    "日曜日": 0,
    "月曜日": 1,
    "火曜日": 2,
    "水曜日": 3,
    "木曜日": 4,
    "金曜日": 5,
    "土曜日": 6
};

// 禁止曜日のインデックスをセットとして保持
const disabledDays = new Set(regularHoliday.map(day => dayMap[day]));

// flatpickrの設定
flatpickr("#fromReservationDate", {
    locale: "ja",
    minDate: "today",
    maxDate: maxDate,
    dateFormat: "Y-m-d",
    disable: [
        function(date) {
            return disabledDays.has(date.getDay());
        }
    ]
});
