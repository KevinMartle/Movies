"use strict";
import {byId, toon, verwijderChildElementenVan} from "./util.js";


const response = await fetch("genres");
if(response.ok){
    const genres = await response.json();
    const lijst = byId("genres");
    for (const genre of genres){
        const li = document.createElement("li");
        const a = document.createElement("a");
        a.href = "#";
        a.textContent = genre.naam;
        a.dataset.genreId = genre.id;
        li.appendChild(a);
        lijst.appendChild(li);
    }
    const links = document.querySelectorAll("#genres a")
    for(const link of links){
        link.onclick = async () =>{
            const section = byId("films");
            verwijderChildElementenVan(section);
            toon("films");
            const header = document.createElement("h2")
            header.textContent = link.textContent;
            section.appendChild(header);
            const genreid = Number(link.getAttribute('data-genre-id'))
            findByGenreId(genreid)
        }
    }
}
else{
    toon("storing");
}

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
            const section = byId("films")
            section.appendChild(a)




        }
        const filmLinks = document.querySelectorAll("#films a")
        for (const filmLink of filmLinks){
            filmLink.onclick = () =>{
                sessionStorage.setItem("filmid", Number(filmLink.getAttribute('data-filmid')))
            }
        }
    }
}







