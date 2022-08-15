const form = document.getElementById('add-form');


form.addEventListener('submit', () => {
    const request = new XMLHttpRequest();

    request.open('POST', '/kpacs');
    request.setRequestHeader('Accept', 'application/json');
    request.setRequestHeader('Content-Type', 'application/json');

    request.send(JSON.stringify({
        title: form.elements['title'].value,
        description: form.elements['description'].value
    }));

    window.location.reload();
}, {once: true});
