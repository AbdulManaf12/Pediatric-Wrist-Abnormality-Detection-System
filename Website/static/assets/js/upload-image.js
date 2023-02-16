/**
 * Start of this section works for loading an image
 */

const inputImageContainer = document.querySelector("#fileInputContainer");

inputImageContainer.addEventListener("click", function () {
  inputImage.click();
});

const inputImage = document.querySelector("#fileInput");
const preview = document.querySelector("#preview");
const container = document.querySelector("#fileInputContainer");

inputImage.addEventListener("change", function () {
  const file = this.files[0];

  if (file) {
    const reader = new FileReader();

    reader.addEventListener("load", function () {
      preview.innerHTML = `
         <img src="${this.result}" alt="Preview" id="input-image"/>
       `;
      inputImage.style.display = "none";
      container.style.display = "none";
    });

    reader.readAsDataURL(file);
  } else {
    preview.innerHTML = "";
  }
});

preview.addEventListener("click", function () {
  inputImage.value = "";
  inputImage.click();
});

/**
 * End of this section works for loading an image
 */
