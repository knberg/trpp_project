// Создание карты с центром в Москве
var map = L.map('map', {
    zoomControl: false // убираем возможность масштабирования
  }).setView([55.7558, 37.6173], 10);

// Добавление слоя OpenStreetMap
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
minZoom: 10,
maxZoom: 10
}).addTo(map);

// Добавление маркера при клике на карту
map.on('click', function(e) {
var marker = L.marker(e.latlng).addTo(map);
});