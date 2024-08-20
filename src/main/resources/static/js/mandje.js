"use strict"
import {byId, setText, verwijderChildElementenVan} from "./util.js";
const mandje =JSON.parse(sessionStorage.getItem("mandje"))

const tbody = byId("films")
verwijderChildElementenVan(tbody)
let totaal = 0
for(const mandItem of mandje){
    const tr = tbody.insertRow()
    tr.insertCell().textContent = mandItem.titel
    tr.insertCell().textContent = mandItem.prijs
    totaal = totaal + Number(mandItem.prijs)
}
setText("totaal", totaal )











