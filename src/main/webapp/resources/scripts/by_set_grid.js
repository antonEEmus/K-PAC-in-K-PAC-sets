const grid = new dhx.Grid("grid-container", {
    columns: [
        {id: "id", header: [{text: "ID"}]},
        {id: "title", width: 400, header: [{text: "Title"}]},
        {id: "description", width: 400, header: [{text: "Description"}]}
    ],
    autoHeight: true,
    width: 900,
    data: setKpacs
});
