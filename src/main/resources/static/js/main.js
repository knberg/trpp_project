document.querySelector('.open-popup').addEventListener('click', function(e) {
    e.preventDefault();
    document.querySelector('.popup-bg').style.display = 'block';
    document.querySelector('html').classList.add('no-scroll');
    });
    
document.querySelector('.close-popup').addEventListener('click', function() {
    document.querySelector('.popup-bg').style.display = 'none';
    document.querySelector('html').classList.remove('no-scroll');
});


let timeslots = [
    '09:00', '09:30',
    '10:00', '10:30',
    '11:00', '11:30',
    '12:00', '12:30',
    '13:00', '13:30',
    '14:00', '14:30',
    '15:00', '15:30',
    '16:00', '16:30',
    '17:00', '17:30',
    '18:00', '18:30',
];

// Создаем плитки для каждого доступного временного слота
let timeslotHtml = "";
timeslots.forEach((timeslot) => {
    timeslotHtml += `<label class="timeslot"><input type="radio" name="time" value="${timeslot}">${timeslot}</label>`;
});
document.getElementById("timeslots").innerHTML = timeslotHtml;
document.querySelector("#timeslots input").required = true;

// Обработчик клика на плитку времени
let selectedTimeslot = null;
const timeslotsDiv = document.getElementById("timeslots");

timeslotsDiv.addEventListener("click", (event) => {
    const timeslotElement = event.target.closest(".timeslot");

    if (timeslotElement) {
        const oldSelected = document.querySelector(".timeslot.selected");
        if (oldSelected) {
            oldSelected.classList.remove("selected");
        }
    }
    timeslotElement.classList.add("selected");
    selectedTimeslot = timeslotElement.textContent;
});

function updateTimeSlots() {
    // Получаем выбранного парикмахера и дату
    const master = document.getElementById("master").value;
    const date = document.getElementById("date").value;

    if (master && date) {
        // Отправляем Ajax-запрос на сервер для получения зарезервированных временных слотов
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `/timeslots?date=${date}&master=${master}`);
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {
                const reservedTimes = JSON.parse(xhr.responseText);
                disableReservedTimes(reservedTimes)
            }
        };
        xhr.send();
    }
}

function disableReservedTimes(reservedTimes) {
    const timeSlots = document.querySelectorAll('.timeslot');
    for (let i = 0; i < timeSlots.length; i++) {
        const time = timeSlots[i].textContent;
        if (reservedTimes.includes(time)) {
            timeSlots[i].classList.add('reserved');
            if (timeSlots[i].classList.contains("selected")) {
                timeSlots[i].classList.remove('selected');
                timeSlots[i].firstChild.checked = false;
            }
        } else {
            if ( timeSlots[i].classList.contains('reserved'))
                timeSlots[i].classList.remove('reserved');
        }
    }
}

const editFields = document.querySelectorAll(".edit-field");

const editBtn = document.getElementById("edit-btn");
const saveBtn = document.getElementById("save-btn");

editBtn.addEventListener("click", () => {
    editFields.forEach((field)=> {
        field.removeAttribute("readonly");
        field.style.color = "#fff";
    })
    editBtn.style.display = "none";
    saveBtn.style.display = "block";
});



