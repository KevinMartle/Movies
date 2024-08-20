"use strict";
import {byId, setText, toon} from "./util.js";

const filmId = sessionStorage.getItem("filmid")
findById(filmId)







async function findById(id){
    const response =  await fetch (`films/film/${id}`)
    if(response.ok){
        const body = byId("body")
        const film = await response.json();
        document.getElementById("kop").textContent = film.titel;
        document.getElementById("image").src = `images/${id}.jpg`;
        document.getElementById("image").alt = film.titel
        document.getElementById("prijs").textContent = film.prijs;
        document.getElementById("voorraad").textContent = film.voorraad;
        document.getElementById("gereserveerd").textContent = film.gereserveerd;
        const beschikbaar = Number(film.voorraad)-Number(film.gereserveerd)
        document.getElementById("beschikbaar").textContent = beschikbaar
        document.getElementById("button").disabled = beschikbaar === 0;
        const button = byId("button")
        button.onclick = () =>{
            let mandItem = {titel : film.titel, prijs : Number(film.prijs)}
            let mandje = JSON.parse(sessionStorage.getItem("mandje"))
            if(mandje === null){
                mandje = []
            }
            if (!mandje.some(object => object.titel === mandItem.titel)) {
                        mandje.push(mandItem)
                        console.log(mandje)
                        sessionStorage.setItem("mandje", JSON.stringify(mandje))
                    }

            window.location = "mandje.html"

            }



    }

    else{
        if (response.status === 404){
            const responseBody = await response.json()
            setText("nietGevonden", responseBody.message)
            toon("nietGevonden")
        }
        else{
            toon("storing")
        }
    }

}




