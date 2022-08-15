const form = document.getElementById('add-form');

form.addEventListener('submit', () => {
    const checkboxes = document.getElementsByClassName('kpac-checkbox');
    const kpacIdsArray = [];

    for (let checkbox of checkboxes) {
        if (checkbox.checked) {
            kpacIdsArray.push(+checkbox.id);
        }
    }

    const dataset = JSON.stringify({
        title: form.elements['title'].value,
        kpacIds: kpacIdsArray
    });

    const request = new XMLHttpRequest();

    request.open('POST', '/sets');
    request.setRequestHeader('Accept', 'application/json');
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => window.location.reload();

    request.send(dataset);
}, {once: true});
