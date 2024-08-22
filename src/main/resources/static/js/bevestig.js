"use strict"
import {byId, toon} from "./util.js";

byId("bevestig").disabled = false
byId("mandje").hidden = false
const mandje = JSON.parse(sessionStorage.getItem("mandje"))
const voorEnAchternaam = sessionStorage.getItem("voorEnAchternaam")
const klantId = Number(sessionStorage.getItem("klantId"))
byId("berichtgeving").textContent = `${Number(mandje.length)} film(s) voor ${voorEnAchternaam}`
byId("bevestig").onclick = async () => {
    for (const mandItem of mandje) {
        const reservatie = {"klantId": klantId, "filmId": mandItem.id}
        create(reservatie, mandItem.titel)
    }
    byId("bevestig").disabled = true
    byId("mandje").hidden = true
    sessionStorage.removeItem("mandje")
}



async function create(reservatie, titel){
    const response = await fetch("films/reservaties",
        {
            method : "POST",
            headers: {'Content-Type' : "application/json"},
            body: JSON.stringify(reservatie)
        })
    if(response.ok){
        toon("lijst")
        const li = document.createElement("li")
        li.textContent = `${titel} :OK`
        byId("lijst").appendChild(li)
    }
    else {
        if (response.status === 409) {
            toon("lijst")
            const li = document.createElement("li")
            li.textContent = `${titel} :Uitverkocht`
            byId("lijst").appendChild(li)
        }
        if(response.status === 404){
            toon("storing")
        }
    }

}