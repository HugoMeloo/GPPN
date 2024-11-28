let nav = 0;
let clicked = null;
let events = {}; // Será carregado do backend

// Variáveis do modal
const newEventModal = document.getElementById('newEventModal');
const deleteEventModal = document.getElementById('deleteEventModal');
const backDrop = document.getElementById('modalBackDrop');
const eventTitleInput = document.getElementById('eventTitleInput');
const eventTimeInput = document.getElementById('eventTimeInput');
const calendar = document.getElementById('calendar');
const weekdays = ['domingo', 'segunda-feira', 'terça-feira', 'quarta-feira', 'quinta-feira', 'sexta-feira', 'sábado'];

// Função para abrir o modal
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
        newEventModal.style.display = 'block';
    }

    backDrop.style.display = 'block';
}

// Função para fechar o modal
function closeModal() {
    eventTitleInput.value = '';
    eventTimeInput.value = '';
    newEventModal.style.display = 'none';
    deleteEventModal.style.display = 'none';
    backDrop.style.display = 'none';
    clicked = null;
    load();
}

// Salvar evento no backend
function saveEvent() {
    if (eventTitleInput.value) {
        const eventData = {
            title: eventTitleInput.value,
            time: eventTimeInput.value,
            date: clicked,
        };

        fetch('/saveEvent', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(eventData),
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Evento salvo com sucesso!');
                    load(); // Recarregar calendário
                } else {
                    alert('Erro ao salvar o evento.');
                }
                closeModal();
            })
            .catch(error => console.error('Erro ao salvar evento:', error));
    } else {
        eventTitleInput.classList.add('error');
    }
}

// Deletar evento
function deleteEvent() {
    const eventData = {
        title: events[clicked][0]?.title, // Exemplo: Deletar o primeiro evento (pode ser ajustado)
        date: clicked,
    };

    fetch('/deleteEvent', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(eventData),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Evento deletado com sucesso!');
                load(); // Recarregar calendário
            } else {
                alert('Erro ao deletar o evento.');
            }
            closeModal();
        })
        .catch(error => console.error('Erro ao deletar evento:', error));
}

// Função para carregar eventos do backend
function fetchEvents() {
    const date = new Date();
    date.setMonth(new Date().getMonth() + nav);

    const month = String(date.getMonth() + 1).padStart(2, '0'); // Mês (1-12)
    const year = date.getFullYear();

    return fetch(`/getEvents?month=${month}&year=${year}`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
    })
        .then(response => response.json())
        .then(data => {
            console.log('Eventos carregados do backend:', data);
            return data; // Retorna os eventos
        })
        .catch(error => {
            console.error('Erro ao buscar eventos do backend:', error);
            return {};
        });
}

// Carregar calendário
function load() {
    const date = new Date();
    date.setMonth(new Date().getMonth() + nav);

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

    const paddingDays = weekdays.indexOf(dateString.split(', ')[0]);

    // Mostrar mês e ano
    document.getElementById('monthDisplay').innerText = `${date.toLocaleDateString('pt-br', { month: 'long' })}, ${year}`;

    calendar.innerHTML = '';

    // Buscar eventos antes de renderizar o calendário
    fetchEvents().then(fetchedEvents => {
        events = fetchedEvents;

        // Criar os dias do calendário
        for (let i = 1; i <= paddingDays + daysMonth; i++) {
            const dayS = document.createElement('div');
            dayS.classList.add('day');

            const dayString = `${year}-${String(month + 1).padStart(2, '0')}-${String(i - paddingDays).padStart(2, '0')}`;

            if (i > paddingDays) {
                dayS.innerText = i - paddingDays;

                const eventDay = events[dayString];

                if (i - paddingDays === day && nav === 0) {
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
    });
}

// Configurar botões
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

// Inicializar
buttons();
load();


