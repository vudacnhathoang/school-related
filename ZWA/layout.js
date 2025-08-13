window.addEventListener("DOMContentLoaded",init);

function init()
{
    const heslo2 = document.querySelector("#heslo2");
    heslo2.addEventListener("keyup",kontrolahesla);

    const form = document.querySelector("#form");
    form.addEventListener("submit",kontrolahesla);
}

function kontrolahesla(event)
{
    const heslo1 = document.querySelector("#heslo1").value;
    const heslo2 = document.querySelector("#heslo2").value;
    const tlacitko =document.querySelector("#reg");
    if(heslo1.value !== heslo2.value)
    {
        event.preventDefault();
    }
}
