"use strict";

//category radios
const categoryBlockEls = document.querySelectorAll('.category__block');

//federal region radios
const federalRegionBlockEls = document.querySelectorAll('.federal_region__block');

//search button
const searchBtnEl = document.querySelector('.button_search');

//button event listeners
let radioButtonCategoryElSelected;
categoryBlockEls.forEach(categoryBlockEl => {
    categoryBlockEl.addEventListener('click', (event) => {
        radioButtonCategoryElSelected = categoryBlockEl.querySelector(".category_button");
        radioButtonCategoryElSelected.checked = true;
        categoryBlockEls.forEach(curCategoryBlock => {
            if (curCategoryBlock !== categoryBlockEl) {
                curCategoryBlock.classList.add('radio_visibility_hidden');
            };
        });
    });
});

let radioButtonFederalRegionElSelected;
federalRegionBlockEls.forEach(federalRegionBlockEl => {
    federalRegionBlockEl.addEventListener('click', (event) => {
        radioButtonFederalRegionElSelected = federalRegionBlockEl.querySelector(".federal_region_button");
        radioButtonFederalRegionElSelected.checked = true;
        federalRegionBlockEls.forEach(curFederalRegionBlockEl => {
            if (curFederalRegionBlockEl !== federalRegionBlockEl) {
                curFederalRegionBlockEl.classList.add('radio_visibility_hidden');
            };
        });
    });
});


async function showProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected,dateFrom, dateTo) {
    const procurements = await getProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected,dateFrom, dateTo);
    procurementsBoxElementVariable.replaceChildren();
    procurements.forEach(procurement => {
        const procurementElement = document.createElement('div');
        procurementsBoxElementVariable.insertAdjacentElement("beforeend", procurementElement);
        for (let key in procurement) {
            const procurementPropertyElement = document.createElement('p');
            procurementPropertyElement.textContent = procurement[key];
            procurementsBoxElementVariable.insertAdjacentElement("beforeend", procurementPropertyElement);
        }
    })
};

searchBtnEl.addEventListener('click', async () => {
    if (radioButtonCategoryElSelected && radioButtonFederalRegionElSelected) {
        const categorySelected = radioButtonCategoryElSelected.dataset.category;
        const federalRegionSelected = radioButtonFederalRegionElSelected.dataset.federalregion;
        await showProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected, dateToRequestFrom, dateToRequestTo);
        categoryBlockEls.forEach(curCategoryBlock => {
            curCategoryBlock.classList.remove('radio_visibility_hidden');
            radioButtonCategoryElSelected = curCategoryBlock.querySelector(".category_button");
            radioButtonCategoryElSelected.checked = false;
        });
        federalRegionBlockEls.forEach(curFederalRegionBlockEl => {
            curFederalRegionBlockEl.classList.remove('radio_visibility_hidden');
            radioButtonFederalRegionElSelected = curFederalRegionBlockEl.querySelector(".federal_region_button");
            radioButtonFederalRegionElSelected.checked = false;
        });
    };
});

