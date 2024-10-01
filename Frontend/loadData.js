"use strict";

/**
 * 
 * @param {String} dateToRequest 
 * @returns 
 */
async function getProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected,dateToRequestFrom, dateToRequestTo) {
    const response = await fetch(`http://127.0.0.1:8080/procurement/${categorySelected}/${federalRegionSelected}/${dateToRequestFrom}-${dateToRequestTo}`);
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