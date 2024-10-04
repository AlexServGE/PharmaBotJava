"use strict";


/**
 * 
 * @param {String} dateToRequest 
 * @returns 
 */
async function getProcurements(dateToRequestFrom, dateToRequestTo) {
    const response = await fetch(`http://127.0.0.1:8080/procurement/${dateToRequestFrom}-${dateToRequestTo}`);
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
const procurementsBoxElement = document.querySelector('.procurements__block');
const procurementsBoxElementVariable = document.createElement('div');
procurementsBoxElement.insertAdjacentElement("beforeend", procurementsBoxElementVariable);

document.addEventListener('DOMContentLoaded', async () => {
    const procurements = await getProcurements(dateToRequestFrom, dateToRequestTo);
    console.log(procurements);
    procurements.forEach(procurement => {
        const procurementElement = document.createElement('div');
        procurementElement.classList.add("procurements_box__procurement");
        procurementsBoxElementVariable.insertAdjacentElement("beforeend", procurementElement);
        for (let key in procurement) {
            if (key === "id" || key === "tenderLink"|| key === "medicineCategory") {
                continue;
            }
            if (key === "tenderId") {
                const procurementPropertyElement = document.createElement('a');
                procurementPropertyElement.classList.add("procurements_box__procurement__element");
                procurementPropertyElement.href = procurement.tenderLink;
                procurementPropertyElement.textContent = procurement[key];
                procurementElement.insertAdjacentElement("beforeend", procurementPropertyElement);
                continue;
            }
            const procurementPropertyElement = document.createElement('p');
            procurementPropertyElement.classList.add("procurements_box__procurement__element");
            procurementPropertyElement.textContent = procurement[key];
            procurementElement.insertAdjacentElement("beforeend", procurementPropertyElement);
        }
    })
});