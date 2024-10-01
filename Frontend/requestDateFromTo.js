"use strict";

/**
 * 
 * @param {Date} date 
 * @returns {String}
 */
function getFormattedDate(date) {
    const year = date.getFullYear(); // Год
    const month = date.getMonth() + 1; // Месяц (от 0 до 11, поэтому +1)
    const day = date.getDate(); // День месяца
    const formattedDate = `${String(day).padStart(2, '0')}.${String(month).padStart(2, '0')}.${year}`;
    return formattedDate;
}

/**
 * 
 * @param {Date} date 
 * @param {Number} offset 
 * @returns {String}
 */
function getOffsetFormattedDate(date, offset) {
    const todayOffsetDate = new Date();
    todayOffsetDate.setDate(date.getDate() - offset);
    return getFormattedDate(todayOffsetDate);
}

const currentDate = new Date();
const currentDateFormatted = getFormattedDate(currentDate);
const offset1DateFormatted = getOffsetFormattedDate(currentDate, 1);
const offset2DateFormatted = getOffsetFormattedDate(currentDate, 2);
const offset3DateFormatted = getOffsetFormattedDate(currentDate, 3);
const offset4DateFormatted = getOffsetFormattedDate(currentDate, 4);

const dayWeek = currentDate.getDay();
let dateToRequestFrom = null;
let dateToRequestTo = null;

switch (dayWeek) {
    case 1:
        dateToRequestFrom = offset3DateFormatted;
        dateToRequestTo = offset1DateFormatted;
        break;
    case 2:
        dateToRequestFrom = offset1DateFormatted;
        dateToRequestTo = offset1DateFormatted;
        break;
    default:
        dateToRequestFrom = offset2DateFormatted;
        dateToRequestTo = offset1DateFormatted;
        break;
}