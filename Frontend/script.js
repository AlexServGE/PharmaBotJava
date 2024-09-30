"use strict";

const procurementsBoxElement = document.querySelector('.procurements_box');


async function getProcurements(resource) {
    const response = await fetch(resource, {
        mode: 'no-cors',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
    });
    console.log(response.ok);
    const responseObject = await response.json();
    console.log(responseObject);
    return responseObject;
}

document.addEventListener('DOMContentLoaded', async () => {
    const procurements = await getProcurements("http://127.0.0.1:8080/procurement/1");
    console.log(procurements);
    // procurements.forEach(procurement => {
    //     const procurementElement = document.createElement('div');
    //     procurementsBoxElement.insertAdjacentElement("beforeend", procurementElement);
    //     for (let key in procurement) {
    //         const procurementPropertyElement = document.createElement('p');
    //         console.log(procurement[key]);
    //         procurementPropertyElement.textContent = procurement[key];
    //         procurementsBoxElement.insertAdjacentElement("beforeend", procurementPropertyElement);
    //     }
    // })
});
