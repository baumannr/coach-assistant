const exerciseType1 = document.querySelector("#exerciseType1");
const exerciseType2 = document.querySelector("#exerciseType2");
const repetitionsInputWrapper = document.querySelector("#repetitionsInputWrapper");
const lengthAndUnitInputWrapper = document.querySelector("#lengthAndUnitInputWrapper");
const inputRepetitions = document.querySelector("#repetitions");
const inputLength = document.querySelector("#length");
const inputLengthTimeUnit = document.querySelector("#lengthTimeUnit");

if (exerciseType1) {
    exerciseType1.addEventListener("change", toggleDisplayNone);
}

if (exerciseType2) {
    exerciseType2.addEventListener("change", toggleDisplayNone);
}

function toggleDisplayNone() {
    repetitionsInputWrapper.classList.toggle("d-none");
    lengthAndUnitInputWrapper.classList.toggle("d-none");
    inputRepetitions.disabled = exerciseType2.checked;
    inputLength.disabled = exerciseType1.checked;
    inputLengthTimeUnit.disabled = exerciseType1.checked;
}

$(function () {
    $("#sets").on("keypress keyup blur", function (event) {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
        if (event.which < 48 || event.which > 57) {
            event.preventDefault();
        }
    });
})

$(function () {
    $("#rest").on("keypress keyup blur", function (event) {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
        if (event.which < 48 || event.which > 57) {
            event.preventDefault();
        }
    });
})

$(function () {
    $("#repetitions").on("keypress keyup blur", function (event) {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
        if (event.which < 48 || event.which > 57) {
            event.preventDefault();
        }
    });
})

$(function () {
    $("#length").on("keypress keyup blur", function (event) {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
        if (event.which < 48 || event.which > 57) {
            event.preventDefault();
        }
    });
})