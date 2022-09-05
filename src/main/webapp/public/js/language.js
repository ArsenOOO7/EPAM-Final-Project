const elements = document.getElementsByClassName("lang");
Array.from(elements).forEach((element) => element.addEventListener("click", (event) => {

    event.preventDefault();
    console.log(element.id)
    if(document.cookie.indexOf("language=" + element.id) < 0){
        // document.cookie = "lang=" + element.id + ";max-age=60*60*24*365";
        setCookie("language", element.id, 100);
        location.reload();
    }

}));

function setCookie(cName, cValue, expDays) {
    let date = new Date();
    date.setTime(date.getTime() + (expDays * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = cName + "=" + cValue + "; " + expires + "; path=/";
}
