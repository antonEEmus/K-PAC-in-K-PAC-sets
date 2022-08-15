for (let row of sets) {
    row['delete'] = `<img src="/resources/images/delete.ico" alt="Delete">`;
}

const grid = new dhx.Grid('grid-container', {
    columns: [
        {id: 'id', header: [{text: 'ID'}]},
        {id: 'title', width: 400, header: [{text: 'Title'}]},
        {id: 'delete', header: [{text: 'Delete'}],
            htmlEnable: true, adjust: 'header', sortable: false},
    ],
    width: 560,
    autoHeight: true,
    data: sets,
    css: 'grid'
});

for (let i = 0; i < sets.length; i++) {
    grid.addCellCss(grid.data.getId(i), 'delete', 'delete-icon');
}

grid.events.on('cellClick', (row, col) => {
    if (col.id === 'delete') {
        const request = new XMLHttpRequest();

        request.open('DELETE', '/sets/' + row.id);
        request.onload = () => {
            grid.events.detach('cellClick');
            window.location.reload();
        }

        request.send();
    }
});

grid.events.on('cellDblClick', (row) => {
    window.open('/set/' + row.id);
});
