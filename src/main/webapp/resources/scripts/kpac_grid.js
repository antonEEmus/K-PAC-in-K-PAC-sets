for (let row of kpacs) {
    row['delete'] = `<img src="/resources/images/delete.ico" alt="Delete">`;
}

const grid = new dhx.Grid('grid-container', {
    columns: [
        {id: 'id', header: [{text: 'ID'}]},
        {id: 'title', width: 400, header: [{text: 'Title'}]},
        {id: 'description', width: 400, header: [{text: 'Description'}]},
        {id: 'creationDate', width: 130, header: [{text: 'Creation date'}]},
        {id: 'delete', header: [{text: 'Delete'}],
            htmlEnable: true, adjust: 'header', sortable: false}
    ],
    width: 1090,
    autoHeight: true,
    data: kpacs,
    css: 'grid'
});

for (let i = 0; i < kpacs.length; i++) {
    grid.addCellCss(grid.data.getId(i), 'delete', 'delete-icon');
}

grid.events.on('cellClick', (row, col) => {
    if (col.id === 'delete') {
        const request = new XMLHttpRequest();

        request.open('DELETE', '/kpacs/' + row.id);
        request.onload = () => {
            grid.events.detach('cellClick');
            window.location.reload();
        }

        request.send();
    }
});
