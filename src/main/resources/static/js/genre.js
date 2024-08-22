"use strict";
import {byId, toon, verwijderChildElementenVan} from "./util.js";

//aanmaken navigatiebar genres
const response = await fetch("genres");
if(response.ok){
    const genres = await response.json();
    for (const genre of genres){
        const li = document.createElement("li");
        const a = document.createElement("a");
        a.href = "#";
        a.textContent = genre.naam;
        a.dataset.genreId = genre.id;
        li.appendChild(a);
        byId("genres").appendChild(li);
    }
    //toon de films per genre
    const links = document.querySelectorAll("#genres a")
    for(const link of links){
        link.onclick = async () =>{
            verwijderChildElementenVan(byId("films"));
            toon("films");
            const header = document.createElement("h2")
            header.textContent = link.textContent;
            byId("films").appendChild(header);
            const genreid = Number(link.getAttribute('data-genre-id'))
            findByGenreId(genreid)
        }
    }
}
else{
    toon("storing");
}

//aanmaken functie om info te tonen over de aangeklikte film
async function findByGenreId(genreid){
    const response = await fetch (`films/${genreid}`)
    if(response.ok){
        const films = await response.json();
        for (const film of films){
            const a = document.createElement("a");
            a.href = "film.html"
            a.dataset.filmid = Number(film);
            const img = document.createElement("img");
            img.src = `images/${film}.jpg`
            a.appendChild(img)
            byId("films").appendChild(a)
        }
        const filmLinks = document.querySelectorAll("#films a")
        for (const filmLink of filmLinks){
            filmLink.onclick = () =>{
                sessionStorage.setItem("filmid", Number(filmLink.getAttribute('data-filmid')))
            }
        }
    }
}







