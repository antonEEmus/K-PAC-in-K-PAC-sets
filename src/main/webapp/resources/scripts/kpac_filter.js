const applyFilterButton = document.getElementById('apply');

const title = document.getElementById('title');
const description = document.getElementById('description');
const dateFrom = document.getElementById('date-from');
const dateTo = document.getElementById('date-to');

applyFilterButton.onclick = () => {
    grid.data.filter((item) => {
        let filter = true;

        if (title.value) {
            filter &&= item.title.toLowerCase().includes(title.value.toLowerCase());
        }

        if (description.value) {
            filter &&= item.description.toLowerCase().includes(description.value.toLowerCase());
        }

        const date = item.creationDate.split('-').reverse();

        if (dateFrom.value) {
            const fromDate = dateFrom.value.split('-');
            filter &&=
                (+fromDate[0] <= +date[0] && +fromDate[1] <= +date[1] && +fromDate[2] <= +date[2]);
        }

        if (dateTo.value) {
            const toDate = dateTo.value.split('-');
            filter &&= (+date[0] <= +toDate[0] && +date[1] <= +toDate[1] && +date[2] <= +toDate[2]);
        }

        return filter;
    });
};

const resetFilterButton = document.getElementById('reset');

resetFilterButton.onclick = () => {
    title.value = '';
    description.value = '';
    dateFrom.value = '';
    dateTo.value = '';

    grid.data.filter();
}
