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


async function showProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected, dateFrom, dateTo) {
    const procurements = await getProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected, dateFrom, dateTo);
    procurementsBoxElementVariable.replaceChildren();
    procurements.forEach(procurement => {
        const procurementElement = document.createElement('div');
        procurementElement.classList.add("procurements_box__procurement");
        procurementsBoxElementVariable.insertAdjacentElement("beforeend", procurementElement);
        for (let key in procurement) {
            if (key === "id" || key === "tenderLink" || key === "medicineCategory") {
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
};

searchBtnEl.addEventListener('click', async () => {
    if (radioButtonCategoryElSelected && radioButtonFederalRegionElSelected) {
        const categorySelected = radioButtonCategoryElSelected.dataset.category;
        const federalRegionSelected = radioButtonFederalRegionElSelected.dataset.federalregion;
        await showProcurementsByCategoryAndFederalRegion(categorySelected, federalRegionSelected, dateToRequestFrom, dateToRequestTo);
        categoryBlockEls.forEach(curCategoryBlock => {
            curCategoryBlock.classList.remove('radio_visibility_hidden');
            curCategoryBlock.classList.remove('category__block__border');

            radioButtonCategoryElSelected = curCategoryBlock.querySelector(".category_button");
            radioButtonCategoryElSelected.checked = false;
        });
        federalRegionBlockEls.forEach(curFederalRegionBlockEl => {
            curFederalRegionBlockEl.classList.remove('radio_visibility_hidden');
            curFederalRegionBlockEl.classList.remove(
                'federal_region__block__border'
            );
            radioButtonFederalRegionElSelected = curFederalRegionBlockEl.querySelector(".federal_region_button");
            radioButtonFederalRegionElSelected.checked = false;
        });
    };
});



categoryBlockEls.forEach(categoryEl => {
    categoryEl.addEventListener('click', (event) => {
        categoryEl.classList.add("category__block__border");
    });
})


federalRegionBlockEls.forEach(federalRegionEl => {
    federalRegionEl.addEventListener('click', (event) => {
        federalRegionEl.classList.add('federal_region__block__border');
    });
})