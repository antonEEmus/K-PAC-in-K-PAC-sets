const applyFilterButton = document.getElementById('apply');

const title = document.getElementById('title');
const description = document.getElementById('description');

applyFilterButton.onclick = () => {
    grid.data.filter((item) => {
        let filter = true;

        if (title.value) {
            filter &&= item.title.toLowerCase().includes(title.value.toLowerCase());
        }

        if (description.value) {
            filter &&= item.description.toLowerCase().includes(description.value.toLowerCase());
        }

        return filter;
    });
};

const resetFilterButton = document.getElementById('reset');

resetFilterButton.onclick = () => {
    title.value = '';
    description.value = '';

    grid.data.filter();
}
