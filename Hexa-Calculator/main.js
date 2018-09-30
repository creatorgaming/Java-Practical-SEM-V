var btn = document.getElementsByClassName("btn");
var display = document.getElementById("display");
for (let index = 0; index < btn.length; index++) {
  const element = btn[index];
  element.addEventListener("click", (event) => {
    if (element.innerText == "C") {
      display.innerText = "";
    }else {
      display.innerText += element.innerText;
    }
  })
}