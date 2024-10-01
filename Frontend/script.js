"use strict";

const procurementsBoxElement = document.querySelector('.procurements_box');


async function getProcurements() {
    const response = await fetch("http://127.0.0.1:8080/procurement/");
    console.log(response.text);
    if (!response.ok) {
        console.log(
            "Network request for products.json failed with response " +
            response.status +
            ": " +
            response.statusText,
        );
        throw new Error(`Did not manage to get data from Server`);
    }

    const result = await response.json();
    return result;
}

document.addEventListener('DOMContentLoaded', async () => {
    const procurements = await getProcurements();
    procurements.forEach(procurement => {
        const procurementElement = document.createElement('div');
        procurementsBoxElement.insertAdjacentElement("beforeend", procurementElement);
        for (let key in procurement) {
            const procurementPropertyElement = document.createElement('p');
            console.log(procurement[key]);
            procurementPropertyElement.textContent = procurement[key];
            procurementsBoxElement.insertAdjacentElement("beforeend", procurementPropertyElement);
        }
    })
});