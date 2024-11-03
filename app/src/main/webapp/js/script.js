let nav = 0;
let clicked = null;
let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : {};

// variáveis do modal
const newEvent = document.getElementById('newEventModal');
const deleteEventModal = document.getElementById('deleteEventModal');
const backDrop = document.getElementById('modalBackDrop');
const eventTitleInput = document.getElementById('eventTitleInput');
const eventTimeInput = document.getElementById('eventTimeInput'); // Novo campo para horário
const calendar = document.getElementById('calendar');
const weekdays = ['domingo', 'segunda-feira', 'terça-feira', 'quarta-feira', 'quinta-feira', 'sexta-feira', 'sábado'];

// funções

function openModal(date) {
  clicked = date;
  const eventDay = events[clicked];

  // Limpa o texto anterior
  document.getElementById('eventText').innerText = '';

  if (eventDay && eventDay.length > 0) {
    eventDay.forEach(event => {
      const eventDiv = document.createElement('div');
      eventDiv.classList.add('event');
      eventDiv.innerText = `${event.title} - ${event.time}`; // Mostra o título e o horário
      document.getElementById('eventText').appendChild(eventDiv);
    });
    deleteEventModal.style.display = 'block';
  } else {
    newEvent.style.display = 'block';
  }

  backDrop.style.display = 'block';
}

function closeModal() {
  eventTitleInput.classList.remove('error');
  eventTimeInput.value = ''; // Limpa o campo de horário ao fechar o modal
  newEvent.style.display = 'none';
  backDrop.style.display = 'none';
  deleteEventModal.style.display = 'none';

  eventTitleInput.value = '';
  clicked = null;
  load();
}

function saveEvent() {
  if (eventTitleInput.value) {
    eventTitleInput.classList.remove('error');

    if (!events[clicked]) {
      events[clicked] = []; // Inicialize um array se não existir
    }

    // Adicione o novo evento ao array da data específica
    events[clicked].push({
      title: eventTitleInput.value,
      time: eventTimeInput.value // Adiciona o horário aqui
    });

    localStorage.setItem('events', JSON.stringify(events));
    closeModal();
  } else {
    eventTitleInput.classList.add('error');
  }
}

function deleteEvent() {
  events[clicked] = events[clicked].filter(event => event.title !== eventTitleInput.value);
  localStorage.setItem('events', JSON.stringify(events));
  closeModal();
}

// botões
function buttons() {
  document.getElementById('backButton').addEventListener('click', () => {
    nav--;
    load();
  });

  document.getElementById('nextButton').addEventListener('click', () => {
    nav++;
    load();
  });

  document.getElementById('saveButton').addEventListener('click', () => saveEvent());
  document.getElementById('cancelButton').addEventListener('click', () => closeModal());
  document.getElementById('deleteButton').addEventListener('click', () => deleteEvent());
  document.getElementById('closeButton').addEventListener('click', () => closeModal());
}

function load() {
  const date = new Date();

  // mudar título do mês
  if (nav !== 0) {
    date.setMonth(new Date().getMonth() + nav);
  }

  const day = date.getDate();
  const month = date.getMonth();
  const year = date.getFullYear();
  const daysMonth = new Date(year, month + 1, 0).getDate();
  const firstDayMonth = new Date(year, month, 1);
  const dateString = firstDayMonth.toLocaleDateString('pt-br', {
    weekday: 'long',
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
  });

  const paddinDays = weekdays.indexOf(dateString.split(', ')[0]);

  // mostrar mês e ano
  document.getElementById('monthDisplay').innerText = `${date.toLocaleDateString('pt-br', { month: 'long' })}, ${year}`;

  calendar.innerHTML = '';

  // criando uma div com os dias
  for (let i = 1; i <= paddinDays + daysMonth; i++) {
    const dayS = document.createElement('div');
    dayS.classList.add('day');

    const dayString = `${month + 1}/${i - paddinDays}/${year}`;

    // condicional para criar os dias de um mês
    if (i > paddinDays) {
      dayS.innerText = i - paddinDays;

      const eventDay = events[dayString];

      if (i - paddinDays === day && nav === 0) {
        dayS.id = 'currentDay';
      }

      if (eventDay) {
        eventDay.forEach(event => {
          const eventDiv = document.createElement('div');
          eventDiv.classList.add('event');
          eventDiv.innerText = `${event.title} - ${event.time}`; // Mostra o título e o horário
          dayS.appendChild(eventDiv);
        });
      }

      dayS.addEventListener('click', () => openModal(dayString));
    } else {
      dayS.classList.add('padding');
    }

    calendar.appendChild(dayS);
  }
}

buttons();
load();
